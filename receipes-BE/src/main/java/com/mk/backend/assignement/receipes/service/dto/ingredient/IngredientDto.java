package com.mk.backend.assignement.receipes.service.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto implements Serializable {

    @NotNull
    @Positive
    private Long id ;

    @NotNull
    @NotBlank
    @Pattern(regexp="^[A-Za-z]*$",message = "name of ingredient should be only characters")
    private String name ;


}
