package com.mk.backend.assignement.receipes.service.ingredient;

import com.mk.backend.assignement.receipes.dao.entities.JpaIngredient;
import com.mk.backend.assignement.receipes.dao.repositories.ingredient.IngredientRepository;
import com.mk.backend.assignement.receipes.service.dto.ingredient.IngredientDto;
import com.mk.backend.assignement.receipes.service.dto.mapper.ingredient.IngredientMapper;
import com.mk.backend.assignement.receipes.service.dto.request.AddIngredientRequestDto;
import com.mk.backend.assignement.receipes.service.exception.NoItemFoundException;
import com.mk.backend.assignement.receipes.service.ingredient.impl.IngredientServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;

import java.util.Collections;
import java.util.List;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class IngredientServiceTest {


    private IngredientService ingredientService ;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private IngredientMapper ingredientMapper;

    @BeforeAll
    public void init(){
        this.ingredientService = new IngredientServiceImpl(ingredientRepository,ingredientMapper);
        when(ingredientRepository.save(any(JpaIngredient.class))).then(returnsFirstArg());

    }

    @Test
     void test_add_ingredient_success(){
        AddIngredientRequestDto ingredientRequestDto = new AddIngredientRequestDto();
        ingredientRequestDto.setName("Tomato");
        ingredientService.addIngredient(ingredientRequestDto);
        verify(ingredientRepository,times(1)).save(any(JpaIngredient.class));
    }

    @Test
     void test_add_ingredient_exception(){
        AddIngredientRequestDto dto = new AddIngredientRequestDto();
        Assertions.assertThrows(IllegalArgumentException.class,() -> {

            ingredientService.addIngredient(dto);

        });
    }

    @Test
     void test_update_ingredient_success(){
        when(ingredientMapper.fromDtoToEntity(any(IngredientDto.class))).thenReturn(new JpaIngredient());
        IngredientDto ingredientDto = new IngredientDto(1L,"Tomato");
        ingredientService.updateIngredient(ingredientDto);
        verify(ingredientRepository,times(1)).save(any(JpaIngredient.class));
    }

    @Test
     void test_update_ingredient_fail(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> {

            ingredientService.updateIngredient(null);

        });
    }

    @Test
     void test_delete_ingredient_success(){
        when(ingredientRepository.existsById(any(Long.class))).thenReturn(Boolean.TRUE);
        ingredientService.deleteIngredient(1L);
        verify(ingredientRepository,times(1)).deleteById(any(Long.class));
    }

    @Test
     void test_delete_ingredient_fail(){
        when(ingredientRepository.existsById(any(Long.class))).thenReturn(Boolean.FALSE);
        Assertions.assertThrows(EntityNotFoundException.class,() -> {
            ingredientService.deleteIngredient(2L);
        });
    }

    @Test
     void test_find_ingredient_by_name_sucess() throws NoItemFoundException {
        when(ingredientRepository.findByNameContainsIgnoreCase(any(String.class))).
                thenReturn(Collections.singletonList(new JpaIngredient()));
        when(ingredientMapper.fromEntityListToDtoList(anyList())).
                thenReturn(Collections.singletonList(new IngredientDto(1L , "Tomato")));

        List<IngredientDto> res = ingredientService.findIngredientByName("tomato");
        Assertions.assertEquals(1L, res.size());
    }

    @Test
     void test_find_ingredient_by_name_fail() throws NoItemFoundException {
        when(ingredientRepository.findByNameContainsIgnoreCase(any(String.class))).
                thenReturn(Collections.emptyList());
        Assertions.assertThrows(NoItemFoundException.class,() -> {
            ingredientService.findIngredientByName("tomato");
        });

    }





}
