package com.mk.backend.assignement.receipes.service.dto.mapper;

import com.mk.backend.assignement.receipes.dao.entities.JpaIngredient;
import com.mk.backend.assignement.receipes.service.dto.ingredient.IngredientDto;
import com.mk.backend.assignement.receipes.service.dto.mapper.exception.MapperNullObjectException;
import com.mk.backend.assignement.receipes.service.dto.mapper.ingredient.IngredientMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class IngredientMapperTest {

    private IngredientMapper ingredientMapper;


    @BeforeAll
    public  void init(){
        this.ingredientMapper=new IngredientMapper();
    }


    @Test
     void test_from_Dto_To_Entity(){
        IngredientDto ingredientDto = new IngredientDto(1L , "Tomato");
        JpaIngredient result =this.ingredientMapper.fromDtoToEntity(ingredientDto);
        Assertions.assertEquals(1L , result.getId());
        Assertions.assertEquals("Tomato",result.getName());
    }

    @Test
     void test_from_Dto_To_Entity_fail(){
        Assertions.assertThrows(MapperNullObjectException.class,() -> {
            JpaIngredient result =this.ingredientMapper.fromDtoToEntity(null);

        });
    }


    @Test
     void test_from_entity_to_dto(){

        JpaIngredient jpaIngredient = new JpaIngredient(1L , "Tomato");
        IngredientDto result =this.ingredientMapper.fromEntityToDto(jpaIngredient);
        Assertions.assertEquals(1L , result.getId());
        Assertions.assertEquals("Tomato",result.getName());
    }

    @Test
     void test_from_entity_to_dto_fail(){
        Assertions.assertThrows(MapperNullObjectException.class,() -> {
            IngredientDto result =this.ingredientMapper.fromEntityToDto(null);
        });
    }

    @Test
     void test_from_entityList_to_dtoList(){
        List<JpaIngredient> entityList = Collections.singletonList(new JpaIngredient(1L , "Potato"));
        List<IngredientDto> ingredientDtos = ingredientMapper.fromEntityListToDtoList(entityList);
        Assertions.assertEquals(1 , ingredientDtos.size());
        Assertions.assertEquals("Potato",ingredientDtos.get(0).getName());
    }

    @Test
     void test_from_entityList_to_dtoList_fail(){
        Assertions.assertThrows(MapperNullObjectException.class,() -> {
           this.ingredientMapper.fromEntityListToDtoList(null);
        });
    }

    @Test
     void test_from_dtoList_to_entityList(){
        List<IngredientDto> ingredientDtos = Collections.singletonList(new IngredientDto(1L , "Potato"));
        List<JpaIngredient> entityList = ingredientMapper.fromDtoListToEntityList(ingredientDtos);
        Assertions.assertEquals(1 , entityList.size());
        Assertions.assertEquals("Potato",entityList.get(0).getName());
    }

    @Test
     void test_from_dtoList_to_entityList_fail(){
        Assertions.assertThrows(MapperNullObjectException.class,() -> {
            this.ingredientMapper.fromDtoListToEntityList(null);
        });
    }

    @Test
     void test_map_page(){
        Page<JpaIngredient> entityPage = new PageImpl<>(Collections.singletonList(new JpaIngredient(1L , "Potato")));

        Page<IngredientDto> dtoPage = ingredientMapper.mapPage(entityPage);
        Assertions.assertEquals(1 , dtoPage.getTotalElements());
        Assertions.assertEquals("Potato",dtoPage.getContent().get(0).getName());
    }

    @Test
     void test_map_page_fail(){
        Assertions.assertThrows(MapperNullObjectException.class,() -> {
            this.ingredientMapper.mapPage(null);
        });
    }



}
