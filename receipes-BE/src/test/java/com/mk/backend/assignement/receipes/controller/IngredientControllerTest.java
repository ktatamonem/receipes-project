package com.mk.backend.assignement.receipes.controller;

import com.mk.backend.assignement.receipes.service.dto.ingredient.IngredientDto;
import com.mk.backend.assignement.receipes.service.dto.request.AddIngredientRequestDto;
import com.mk.backend.assignement.receipes.utils.navigation.Navigation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class IngredientControllerTest {

    @Autowired
    private TestRestTemplate restTemplate ;

    @Test
    void add_ingredient_success(){
        String url = Navigation.INGREDIENT_API+"/add";
        AddIngredientRequestDto request = new AddIngredientRequestDto();
        request.setName("Tomato");
        ResponseEntity<IngredientDto> responseEntity = restTemplate.postForEntity(url,request,IngredientDto.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void add_ingredient_fail(){
        String url = Navigation.INGREDIENT_API+"/add";
        AddIngredientRequestDto request = new AddIngredientRequestDto();
        request.setName("");
        ResponseEntity responseEntity = restTemplate.postForEntity(url,request, Object.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals("name of the ingredient cannot be blank",((HashMap<String, String>)responseEntity.getBody()).get("name"));
    }

    @Test
    void update_ingredient_success(){
        String url = Navigation.INGREDIENT_API+"/update";
        IngredientDto request = new IngredientDto(1L , "Tomato");
        HttpEntity<IngredientDto> httpEntity = new HttpEntity<>(request);
        ResponseEntity<IngredientDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,httpEntity,IngredientDto.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(1L , responseEntity.getBody().getId());
    }

    @Test
    void update_ingredient_fail(){
        String url = Navigation.INGREDIENT_API+"/update";
        IngredientDto request = new IngredientDto();
        HttpEntity<IngredientDto> httpEntity = new HttpEntity<>(request);
        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.PUT,httpEntity,Object.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals("must not be null",((HashMap<String, String>)responseEntity.getBody()).get("id"));
    }



    @Test
    void list_ingredient_success(){
        String url = Navigation.INGREDIENT_API+"/search/Tomato";
        HttpEntity httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,Object.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void list_ingredient_not_found(){
        String url = Navigation.INGREDIENT_API+"/search/test";
        HttpEntity httpEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,Object.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void delete_ingredient_success(){
        String url = Navigation.INGREDIENT_API+"/"+1;
        restTemplate.delete(url);
    }


}
