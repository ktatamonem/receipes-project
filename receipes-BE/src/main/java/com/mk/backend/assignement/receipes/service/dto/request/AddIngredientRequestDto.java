package com.mk.backend.assignement.receipes.service.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AddIngredientRequestDto {

    @NotNull(message = "name of the ingredient cannot be null")
    @NotBlank(message = "name of the ingredient cannot be blank")
    @Pattern(regexp="^[A-Za-z]*$",message = "name of ingredient should be only characters")
    private String name ;
}
