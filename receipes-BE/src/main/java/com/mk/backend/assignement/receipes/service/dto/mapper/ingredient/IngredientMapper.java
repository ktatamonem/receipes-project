package com.mk.backend.assignement.receipes.service.dto.mapper.ingredient;

import com.mk.backend.assignement.receipes.service.dto.ingredient.IngredientDto;
import com.mk.backend.assignement.receipes.service.dto.mapper.BaseMapper;
import com.mk.backend.assignement.receipes.service.dto.mapper.exception.MapperNullObjectException;
import com.mk.backend.assignement.receipes.dao.entities.JpaIngredient;
import com.mk.backend.assignement.receipes.utils.message.ExceptionMessage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * IngredientMapper helps to map Entity to DTO
 * in both sides
 */
@Component
public class IngredientMapper implements BaseMapper<IngredientDto , JpaIngredient> {

    @Override
    public JpaIngredient fromDtoToEntity(IngredientDto dto) {
       if (dto != null){
           return new JpaIngredient(dto.getId() , dto.getName());
       }else {
           throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
       }
    }

    @Override
    public IngredientDto fromEntityToDto(JpaIngredient entity) {
        if( entity!= null){
            return new IngredientDto(entity.getId() , entity.getName());
        }else {
            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
        }
    }

    @Override
    public List<JpaIngredient> fromDtoListToEntityList(List<IngredientDto> dtoList) {
        List<JpaIngredient> result = new ArrayList<>();
        if(dtoList != null){
            dtoList.forEach(item ->
                    result.add(fromDtoToEntity(item)));
            return result;
        }else{
            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
        }

    }

    @Override
    public List<IngredientDto> fromEntityListToDtoList(List<JpaIngredient> entityList) {
        List<IngredientDto> result = new ArrayList<>();
        if(entityList != null){
            entityList.forEach(item ->
                    result.add(fromEntityToDto(item)));
            return result;
        }else {
            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
        }

    }

    @Override
    public Page<IngredientDto> mapPage(Page<JpaIngredient> entityPage) {
        if (entityPage != null){
            return entityPage.map(this::fromEntityToDto);
        }else{
            throw new MapperNullObjectException(ExceptionMessage.CANNOT_MAP_NULL_MSG);
        }
    }
}
