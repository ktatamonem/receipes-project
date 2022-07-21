package com.mk.backend.assignement.receipes.service.ingredient;

import com.mk.backend.assignement.receipes.service.dto.ingredient.IngredientDto;
import com.mk.backend.assignement.receipes.service.dto.request.AddIngredientRequestDto;
import com.mk.backend.assignement.receipes.service.exception.NoItemFoundException;

import java.util.List;

public interface IngredientService {

     /**
      * Add ingredient to database
      * @param addIngredientRequest
      * @return IngredientDto
      */
     IngredientDto addIngredient(AddIngredientRequestDto addIngredientRequest);

     /**
      * Update Ingredient Data
      * @param ingredient
      * @return IngredientDto
      */
     IngredientDto updateIngredient(IngredientDto  ingredient);

     /**
      * Delete Ingredient
      * @param id
      */
     void deleteIngredient(Long id) ;

     /**
      * Search for Ingredient by name
      * @param name
      * @return IngredientDto
      * @throws NoItemFoundException
      */
     List<IngredientDto> findIngredientByName(String name) throws NoItemFoundException;


    /**
     * retrieve all ingredients for front end purpose
     * @return
     */
     List<IngredientDto> retrieveAllIngredient();


}
