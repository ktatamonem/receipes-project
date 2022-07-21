package com.mk.backend.assignement.receipes.service.dto.request;

import com.mk.backend.assignement.receipes.service.dto.ingredient.IngredientDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
public class FilterRecipeRequestDto {

    @Pattern(regexp="^[A-Za-z]*$",message = "name of recipe should be only characters")
    private String recipeName;

    private Boolean vegan ;

    private Long servingNumber;

    private List<IngredientDto> includedIngredients ;

    private List<IngredientDto> excludedIngredients;

    private String instruction ;

    @PositiveOrZero
    @NotNull
    private Integer pageNum;

    @PositiveOrZero
    @NotNull
    private Integer pageSize ;


}
