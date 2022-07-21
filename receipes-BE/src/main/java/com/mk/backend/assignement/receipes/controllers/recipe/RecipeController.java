package com.mk.backend.assignement.receipes.controllers.recipe;

import com.mk.backend.assignement.receipes.service.dto.recipe.RecipeDto;
import com.mk.backend.assignement.receipes.service.dto.request.AddRecipeRequestDto;
import com.mk.backend.assignement.receipes.service.dto.request.FilterRecipeRequestDto;
import com.mk.backend.assignement.receipes.service.recipe.RecipeService;
import com.mk.backend.assignement.receipes.utils.navigation.Navigation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping(Navigation.RECIPE_API)
@Tag(name = "Recipe", description = "Endpoints for managing recipe")
@Slf4j
@Validated
public class RecipeController {

    private RecipeService recipeService ;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("add")
    @Operation(
            summary = "Add Recipe",
            description = "Add Recipe to Database.",
            tags = { "Recipe" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecipeDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<RecipeDto> addRecipe(@RequestBody @Valid AddRecipeRequestDto addRecipeRequestDto){
        log.info("Starting adding recipe {}" , addRecipeRequestDto);
        return ResponseEntity.ok(recipeService.addNewRecipe(addRecipeRequestDto));
    }

    @PutMapping("update")
    @Operation(
            summary = "Update Recipe",
            description = "Update Recipe data.",
            tags = { "Recipe" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecipeDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<RecipeDto> updateRecipe(@RequestBody @Validated RecipeDto recipeDto){
        log.info("Starting update operation for recipe : {}", recipeDto);
        return ResponseEntity.ok(recipeService.updateRecipe(recipeDto));
    }


    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete Recipe",
            description = "Delete Recipe from database.",
            tags = { "Recipe" },
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
    public ResponseEntity<String> deleteRecipe(@PathVariable
                                                   @Parameter(description = "Id of the Recipe to delete") @Validated @NotNull Long id)  {
        log.info("Starting delete operation for recipe id :{}",id);
        recipeService.deleteRecipe(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("search")
    @Operation(
            summary = "Search Recipe",
            description = "Search Recipe By name , servingNumber , instruction and ingredients.",
            tags = { "Recipe" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Page<RecipeDto>> searchRecipes(@RequestBody @Validated @NotNull  FilterRecipeRequestDto filterRecipeRequestDto){
        log.info("Starting searching query by {}", filterRecipeRequestDto);
        return ResponseEntity.ok(recipeService.filterRecipes(filterRecipeRequestDto));
    }




}
