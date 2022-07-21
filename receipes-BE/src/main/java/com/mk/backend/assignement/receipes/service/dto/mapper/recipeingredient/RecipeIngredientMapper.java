package com.mk.backend.assignement.receipes.service.dto.mapper.recipeingredient;

import com.mk.backend.assignement.receipes.dao.entities.JpaRecipe;
import com.mk.backend.assignement.receipes.dao.entities.JpaRecipeIngredient;
import com.mk.backend.assignement.receipes.service.dto.mapper.BaseMapper;
import com.mk.backend.assignement.receipes.service.dto.mapper.exception.MapperNullObjectException;
import com.mk.backend.assignement.receipes.service.dto.mapper.ingredient.IngredientMapper;
import com.mk.backend.assignement.receipes.service.dto.recipe.RecipeDto;
import com.mk.backend.assignement.receipes.service.dto.recipeingredient.RecipeIngredientDto;
import com.mk.backend.assignement.receipes.utils.message.ExceptionMessage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * RecipeIngredientMapper Helps to map Entity to Dto
 * in both sides
 */
@Component
public class RecipeIngredientMapper implements BaseMapper<RecipeIngredientDto , JpaRecipeIngredient> {


    private IngredientMapper ingredientMapper;

    public RecipeIngredientMapper(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public JpaRecipeIngredient fromDtoToEntity(RecipeIngredientDto dto) {
      if(dto != null){
          JpaRecipeIngredient entity = new JpaRecipeIngredient() ;
          entity.setId(dto.getId());
          entity.setIngredient(ingredientMapper.fromDtoToEntity(dto.getIngredient()));
          if(dto.getRecipe() !=  null ){
              JpaRecipe recipe = new JpaRecipe();
              recipe.setId(dto.getRecipe().getId());
              entity.setRecipe(recipe);
          }

          entity.setQuantity(dto.getQuantity());
          return entity;
      }else {
          throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);

      }

    }

    @Override
    public RecipeIngredientDto fromEntityToDto(JpaRecipeIngredient entity) {
       if(entity != null){
           RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto() ;
           recipeIngredientDto.setId(entity.getId());
           recipeIngredientDto.setIngredient(ingredientMapper.fromEntityToDto(entity.getIngredient()));
           if(entity.getRecipe() != null){
               RecipeDto recipeDto = new RecipeDto();
               recipeDto.setId(entity.getRecipe().getId());
               recipeDto.setVegan(entity.getRecipe().isVegan());
               recipeDto.setInstruction(entity.getRecipe().getInstruction());
               recipeDto.setServingNumber(entity.getRecipe().getServingNumber());
               recipeDto.setName(entity.getRecipe().getName());
               recipeIngredientDto.setRecipe(recipeDto);
           }

           recipeIngredientDto.setQuantity(entity.getQuantity());
           return recipeIngredientDto;
       }else{
           throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
       }

    }

    @Override
    public List<JpaRecipeIngredient> fromDtoListToEntityList(List<RecipeIngredientDto> dtoList) {
       if(dtoList != null){
           List<JpaRecipeIngredient> result = new ArrayList<>();
           dtoList.forEach(item -> result.add(fromDtoToEntity(item)));
           return result;
       }else{
           throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
       }


    }

    @Override
    public List<RecipeIngredientDto> fromEntityListToDtoList(List<JpaRecipeIngredient> entityList) {
       if(entityList != null){
           List<RecipeIngredientDto> result = new ArrayList<>();
           entityList.forEach(item ->
                   result.add(fromEntityToDto(item)));
           return result;
       }else {
           throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
       }

    }

    @Override
    public Page<RecipeIngredientDto> mapPage(Page<JpaRecipeIngredient> entityPage) {
       if(entityPage != null){
           return entityPage.map(this::fromEntityToDto);
       }else{
           throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
       }
    }
}
