package com.mk.backend.assignement.receipes.dao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="recipe_ingredients")
public class JpaRecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private JpaRecipe recipe ;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private JpaIngredient ingredient ;

    @Column(name = "quantity")
    private Long quantity ;

}
