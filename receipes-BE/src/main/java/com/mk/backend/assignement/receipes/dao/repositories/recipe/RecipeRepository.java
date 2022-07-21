package com.mk.backend.assignement.receipes.dao.repositories.recipe;

import com.mk.backend.assignement.receipes.dao.entities.JpaRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<JpaRecipe , Long> , JpaSpecificationExecutor<JpaRecipe> {
}
