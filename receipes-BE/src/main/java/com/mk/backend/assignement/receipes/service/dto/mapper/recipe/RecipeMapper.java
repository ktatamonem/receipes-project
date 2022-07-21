package com.mk.backend.assignement.receipes.service.dto.mapper.recipe;

import com.mk.backend.assignement.receipes.service.dto.mapper.BaseMapper;
import com.mk.backend.assignement.receipes.service.dto.mapper.exception.MapperNullObjectException;
import com.mk.backend.assignement.receipes.service.dto.mapper.recipeingredient.RecipeIngredientMapper;
import com.mk.backend.assignement.receipes.service.dto.recipe.RecipeDto;
import com.mk.backend.assignement.receipes.dao.entities.JpaRecipe;
import com.mk.backend.assignement.receipes.utils.message.ExceptionMessage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Recipe Mapper Helps to map Entity To DTO
 * in both sides
 */
@Component
public class RecipeMapper implements BaseMapper<RecipeDto , JpaRecipe> {


    private RecipeIngredientMapper recipeIngredientMapper ;

    public RecipeMapper(RecipeIngredientMapper recipeIngredientMapper) {
        this.recipeIngredientMapper = recipeIngredientMapper;
    }

    @Override
    public JpaRecipe fromDtoToEntity(RecipeDto dto) {
        if(dto != null) {
            JpaRecipe entity = new JpaRecipe();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setVegan(dto.isVegan());
            entity.setInstruction(dto.getInstruction());
            entity.setServingNumber(dto.getServingNumber());
            entity.setIngredients(recipeIngredientMapper.fromDtoListToEntityList(dto.getIngredients()));
            return entity;
        }else {
            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
        }

    }

    @Override
    public RecipeDto fromEntityToDto(JpaRecipe entity) {
        if(entity != null){
            RecipeDto dto  = new RecipeDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setVegan(entity.isVegan());
            dto.setInstruction(entity.getInstruction());
            dto.setServingNumber(entity.getServingNumber());
            dto.setIngredients(recipeIngredientMapper.fromEntityListToDtoList(entity.getIngredients()));
            return dto;
        }else{
            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
        }
    }

    @Override
    public List<JpaRecipe> fromDtoListToEntityList(List<RecipeDto> dtoList) {
       if(dtoList != null){
           List<JpaRecipe> result = new ArrayList<>();
           dtoList.forEach(item ->
                   result.add(fromDtoToEntity(item)));
           return result;
       }else {
           throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
       }
    }

    @Override
    public List<RecipeDto> fromEntityListToDtoList(List<JpaRecipe> entityList) {
        if(entityList != null){
            List<RecipeDto> result = new ArrayList<>();
            entityList.forEach(item ->
                    result.add(fromEntityToDto(item)));
            return result;
        }else {
            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);

        }

    }

    @Override
    public Page<RecipeDto> mapPage(Page<JpaRecipe> entityPage) {
        if(entityPage != null){
            return entityPage.map(this::fromEntityToDto);
        }else {
            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
        }
    }
}
