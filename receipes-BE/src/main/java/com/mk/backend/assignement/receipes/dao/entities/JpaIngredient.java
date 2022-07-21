package com.mk.backend.assignement.receipes.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredient")
public class JpaIngredient implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name;



}
