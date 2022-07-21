package com.mk.backend.assignement.receipes.service.dto.recipeingredient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mk.backend.assignement.receipes.service.dto.ingredient.IngredientDto;
import com.mk.backend.assignement.receipes.service.dto.recipe.RecipeDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@JsonIgnoreProperties(value={ "recipe", "id" }, allowGetters= true)
public class RecipeIngredientDto {

    private Long id  ;


    private RecipeDto recipe ;

    @NotNull
    @Valid
    private IngredientDto ingredient ;

    @NotNull
    @Positive
    private Long quantity ;
}
