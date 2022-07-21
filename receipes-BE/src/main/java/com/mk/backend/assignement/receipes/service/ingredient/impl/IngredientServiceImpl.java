package com.mk.backend.assignement.receipes.service.ingredient.impl;

import com.mk.backend.assignement.receipes.service.dto.ingredient.IngredientDto;
import com.mk.backend.assignement.receipes.service.dto.mapper.ingredient.IngredientMapper;
import com.mk.backend.assignement.receipes.service.dto.request.AddIngredientRequestDto;
import com.mk.backend.assignement.receipes.dao.entities.JpaIngredient;
import com.mk.backend.assignement.receipes.dao.repositories.ingredient.IngredientRepository;
import com.mk.backend.assignement.receipes.service.exception.NoItemFoundException;
import com.mk.backend.assignement.receipes.service.ingredient.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    private IngredientMapper ingredientMapper;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public IngredientDto addIngredient(AddIngredientRequestDto addIngredientRequest) {
        log.debug("Starting adding ingredient {}",addIngredientRequest);
        if(addIngredientRequest != null && addIngredientRequest.getName() != null && !addIngredientRequest.getName().isBlank()){
            JpaIngredient jpaIngredient = new JpaIngredient();
            jpaIngredient.setName(addIngredientRequest.getName());
            return ingredientMapper.fromEntityToDto(ingredientRepository.save(jpaIngredient));
        }else {
            throw new IllegalArgumentException("check argument");
        }

    }

    @Override
    public IngredientDto updateIngredient(IngredientDto ingredient) {
        log.debug("Starting updating ingredient {}",ingredient);
        if (ingredient != null){
           return ingredientMapper.fromEntityToDto(
                   ingredientRepository.save(ingredientMapper.fromDtoToEntity(ingredient)));

        }
        throw new IllegalArgumentException("Ingredient null to update");
    }

    @Override
    public void deleteIngredient(Long id)  {
      log.debug("Deleting ingredient {}", id);
          if(ingredientRepository.existsById(id)){
              ingredientRepository.deleteById(id);
          }else {
              throw new EntityNotFoundException("Id of ingredient does not exist");
          }

    }

    @Override
    public List<IngredientDto> findIngredientByName(String name) throws NoItemFoundException {
        log.debug("Search Ingredient by name :{}", name);
        List<JpaIngredient> ingredientList = ingredientRepository.findByNameContainsIgnoreCase(name);
        if(ingredientList.isEmpty()){
            throw new NoItemFoundException("No Ingredient Found");
        }
        return ingredientMapper.fromEntityListToDtoList(ingredientList);
    }
}
