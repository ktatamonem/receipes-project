package com.mk.backend.assignement.receipes.service.recipe.impl;

import com.mk.backend.assignement.receipes.dao.entities.JpaRecipe;
import com.mk.backend.assignement.receipes.dao.entities.JpaRecipeIngredient;
import com.mk.backend.assignement.receipes.dao.repositories.recipe.RecipeRepository;
import com.mk.backend.assignement.receipes.service.dto.mapper.ingredient.IngredientMapper;
import com.mk.backend.assignement.receipes.service.dto.mapper.recipe.RecipeMapper;
import com.mk.backend.assignement.receipes.service.dto.mapper.recipeingredient.RecipeIngredientMapper;
import com.mk.backend.assignement.receipes.service.dto.recipe.RecipeDto;
import com.mk.backend.assignement.receipes.service.dto.request.AddRecipeRequestDto;
import com.mk.backend.assignement.receipes.service.dto.request.FilterRecipeRequestDto;
import com.mk.backend.assignement.receipes.service.recipe.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author moneimketata
 */
@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    private RecipeMapper recipeMapper;

    private RecipeIngredientMapper recipeIngredientMapper;

    private IngredientMapper ingredientMapper;

    private Validator validator;

    /**
     * paramterized constructor
     *
     * @param recipeRepository
     * @param recipeMapper
     * @param recipeIngredientMapper
     */
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper,
                             RecipeIngredientMapper recipeIngredientMapper , IngredientMapper ingredientMapper, Validator validator) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
        this.recipeIngredientMapper = recipeIngredientMapper;
        this.ingredientMapper = ingredientMapper ;
        this.validator = validator;
    }

    @Override
    public RecipeDto addNewRecipe(AddRecipeRequestDto addRecipeRequestDto) {
        log.debug("Starting adding new recipe : {}", addRecipeRequestDto);
        // check AddRecipeRequestDto constraints
        Set<ConstraintViolation<AddRecipeRequestDto>> violations = validator.validate(addRecipeRequestDto);
        if(violations.isEmpty()){
            JpaRecipe recipe = new JpaRecipe();
            recipe.setName(addRecipeRequestDto.getName());
            recipe.setVegan(addRecipeRequestDto.isVegan());
            recipe.setServingNumber(addRecipeRequestDto.getServingNumber());
            recipe.setInstruction(addRecipeRequestDto.getInstruction());
            recipe.setIngredients(recipeIngredientMapper.fromDtoListToEntityList(addRecipeRequestDto.getIngredients()));
            recipe.getIngredients().forEach(item -> item.setRecipe(recipe));
            return recipeMapper.fromEntityToDto(recipeRepository.save(recipe));
        }else {
            throw new ConstraintViolationException("Object does not accept constraints {} " , violations);
        }


    }

    @Override
    public RecipeDto updateRecipe(RecipeDto recipeDto) {
        log.debug("Starting updating recipe : {}", recipeDto);

        if (recipeDto.getId() != null && recipeRepository.existsById(recipeDto.getId())) {

           return recipeMapper.fromEntityToDto(recipeRepository.save(recipeMapper.fromDtoToEntity(recipeDto)));
        } else {
            throw new EntityNotFoundException("Id of recipe not found");
        }
    }

    @Override
    public void deleteRecipe(Long id) {
        log.debug("Starting deleting recipe id: {}", id);
        if (id!= null && recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Id of recipe not found");
        }
    }

    @Override
    public Page<RecipeDto> filterRecipes(FilterRecipeRequestDto filterRecipeRequestDto) {
        return recipeMapper.mapPage(recipeRepository.findAll((Specification<JpaRecipe>) (root, query, criteriaBuilder) ->
                preparePredicateRecipes(filterRecipeRequestDto, root, criteriaBuilder),
                PageRequest.of(filterRecipeRequestDto.getPageNum(),
                        filterRecipeRequestDto.getPageSize())));
    }

    public Predicate preparePredicateRecipes(FilterRecipeRequestDto filterRecipeRequestDto, Root<JpaRecipe> root ,CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        // add check for recipeName to the predicate list
        if (filterRecipeRequestDto.getRecipeName() != null && !filterRecipeRequestDto.getRecipeName().isBlank()) {
            predicateList.add(criteriaBuilder.like(root.get("name"), "%" + filterRecipeRequestDto.getRecipeName()+ "%"));
        }
       // add check for vegan to the predicate list
        if (filterRecipeRequestDto.getVegan() != null) {
            predicateList.add(criteriaBuilder.equal(root.get("vegan"), filterRecipeRequestDto.getVegan()));
        }
        // add check for the instructions to the predicate list
        if (filterRecipeRequestDto.getInstruction() != null && !filterRecipeRequestDto.getInstruction().isBlank()) {
            predicateList.add(criteriaBuilder.like(root.get("instruction"), "%" + filterRecipeRequestDto.getInstruction() + "%"));
        }
        // add check for the servingNumber to the predicate list
        if(filterRecipeRequestDto.getServingNumber() != null && filterRecipeRequestDto.getServingNumber() > 0){
            predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("servingNumber"), filterRecipeRequestDto.getServingNumber()));
        }
        // add In clause for the desired ingredients
        if (filterRecipeRequestDto.getIncludedIngredients() != null && !filterRecipeRequestDto.getIncludedIngredients().isEmpty()) {
            Join<JpaRecipe, JpaRecipeIngredient> joinIngredientRecipe = root.join("ingredients");
            predicateList.add(joinIngredientRecipe.get("ingredient").in(ingredientMapper.fromDtoListToEntityList(
                    filterRecipeRequestDto.getIncludedIngredients())));
        }
        // add NOT IN clause for the undesired ingredients
        //TODO to refactor find another solution
        if (filterRecipeRequestDto.getExcludedIngredients() != null && !filterRecipeRequestDto.getExcludedIngredients().isEmpty()) {
            Join<JpaRecipe, JpaRecipeIngredient> joinIngredientRecipe = root.join("ingredients",JoinType.LEFT);
            joinIngredientRecipe.on(joinIngredientRecipe.get("ingredient").in(ingredientMapper.fromDtoListToEntityList(
                    filterRecipeRequestDto.getExcludedIngredients())));
            predicateList.add(criteriaBuilder.isNull(joinIngredientRecipe.get("ingredient")));

        }

        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
