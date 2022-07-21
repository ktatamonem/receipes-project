package com.mk.backend.assignement.receipes.dao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "recipe")
public class JpaRecipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "recipe_name")
    private String name;

    private boolean vegan;

    @Column(name = "serving_number")
    private Long servingNumber;

    @Column(name = "instruction")
    private String instruction;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<JpaRecipeIngredient> ingredients;
}
