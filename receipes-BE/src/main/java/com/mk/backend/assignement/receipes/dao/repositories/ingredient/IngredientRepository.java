package com.mk.backend.assignement.receipes.dao.repositories.ingredient;

import com.mk.backend.assignement.receipes.dao.entities.JpaIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<JpaIngredient, Long> {

   List<JpaIngredient> findByNameContainsIgnoreCase(String name );
}
