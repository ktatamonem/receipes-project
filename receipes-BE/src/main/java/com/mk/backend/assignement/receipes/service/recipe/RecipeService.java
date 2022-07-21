package com.mk.backend.assignement.receipes.service.recipe;

import com.mk.backend.assignement.receipes.service.dto.recipe.RecipeDto;
import com.mk.backend.assignement.receipes.service.dto.request.AddRecipeRequestDto;
import com.mk.backend.assignement.receipes.service.dto.request.FilterRecipeRequestDto;
import org.springframework.data.domain.Page;

public interface RecipeService {

    /**
     * save recipe data
     * @param addRecipeRequestDto
     * @return RecipeDto
     */
    RecipeDto addNewRecipe(AddRecipeRequestDto addRecipeRequestDto);

    /**
     * Update recipe data
     * @param recipeDto
     * @return RecipeDto
     */
    RecipeDto updateRecipe(RecipeDto recipeDto);

    /**
     * Delete recipe by id
     * @param id
     */
    void deleteRecipe(Long id);

    /**
     * Filter recipes using filter data
     * @param filterRecipeRequestDto
     * @return Page
     */
    Page<RecipeDto> filterRecipes(FilterRecipeRequestDto filterRecipeRequestDto);


}
