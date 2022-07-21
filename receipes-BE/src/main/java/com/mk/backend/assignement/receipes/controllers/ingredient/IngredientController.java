package com.mk.backend.assignement.receipes.controllers.ingredient;

import com.mk.backend.assignement.receipes.service.dto.ingredient.IngredientDto;
import com.mk.backend.assignement.receipes.service.dto.request.AddIngredientRequestDto;
import com.mk.backend.assignement.receipes.service.exception.NoItemFoundException;
import com.mk.backend.assignement.receipes.service.ingredient.IngredientService;
import com.mk.backend.assignement.receipes.utils.navigation.Navigation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping(Navigation.INGREDIENT_API)
@Tag(name = "Ingredient", description = "Endpoints for managing ingredients")
@Slf4j
public class IngredientController {

    private IngredientService ingredientService ;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("add")
    @Operation(
            summary = "Add Ingredient",
            description = "Add Ingredient to Database.",
            tags = { "Ingredient" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = IngredientDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<IngredientDto> addIngredient(@RequestBody @Validated AddIngredientRequestDto addIngredientRequestDto){
        log.info("Starting adding ingredient {}" , addIngredientRequestDto);
        return ResponseEntity.ok(ingredientService.addIngredient(addIngredientRequestDto));
    }

    @PutMapping("update")
    @Operation(
            summary = "Update Ingredient",
            description = "Update Ingredient name.",
            tags = { "Ingredient" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = IngredientDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<IngredientDto> updateIngredient(@RequestBody @Parameter(description = "Ingredient model to update")
                                                              @Validated IngredientDto ingredientDto){
        log.info("Starting update operation for ingredient : {}", ingredientDto);
        return ResponseEntity.ok(ingredientService.updateIngredient(ingredientDto));
    }


    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete Ingredient",
            description = "Delete Ingredient from database.",
            tags = { "Ingredient" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Not Found" , responseCode = "404" , content=@Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<String> deleteIngredient(@PathVariable
                                           @Parameter(description = "Id of the ingredient to delete") @NotNull Long id)  {
        log.info("Starting delete operation for ingredient id :{}",id);
        ingredientService.deleteIngredient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("search/{name}")
    public ResponseEntity<List<IngredientDto>> findIngredientByName(@PathVariable @Valid
                                                                        @NotNull
                                                                        @Pattern(regexp="^[A-Za-z]*$",message = "name of ingredient should be only characters")
                                                                        @NotBlank String name ) throws NoItemFoundException {
        log.info("Start searching for ingredient by name : {}", name);
        return ResponseEntity.ok(ingredientService.findIngredientByName(name));
    }








}
