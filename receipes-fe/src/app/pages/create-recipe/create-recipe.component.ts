import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AddRecipeRequest } from '../../model/add-recipe-request.model';
import { Ingredient } from '../../model/ingredient.model';
import { RecipeIngredient } from '../../model/recipe-ingredient.model';
import { IngredientService } from '../../services/ingredient.service';
import { RecipeService } from '../../services/recipe.service';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.scss']
})
export class CreateRecipeComponent implements OnInit {

  request: AddRecipeRequest = new AddRecipeRequest() ; 

  ingredientList : Ingredient[] = []; 

  recipeIngredient :RecipeIngredient = new RecipeIngredient() ; 

  constructor(
    private ingredientService : IngredientService,
    private recipeService :RecipeService ,
    private router:Router,
    private toastr :ToastrService
  ) { }

  ngOnInit(): void {
    this.ingredientService.retrieveIngredients().subscribe(data =>{
      this.ingredientList = data
    })
  }

  addIngredient(){
    if(this.recipeIngredient.ingredient && this.recipeIngredient.quantity > 0){
      this.request.ingredients.push(this.recipeIngredient);
      this.ingredientList = this.ingredientList.filter(item => item.id != this.recipeIngredient.ingredient.id);
      this.recipeIngredient = new RecipeIngredient();
    }else{ 
      this.toastr.error('Please correct Ingredient data')
    }
  }

  addRecipe(){
    
    if(this.request.ingredients.length>0 && this.request.servingNumber > 0 && this.request.name.length > 0 
      && this.request.instruction.length > 0 ){
        this.recipeService.createRecipe(this.request).subscribe(data =>{
          this.toastr.success("Recipe Created Successfully ");
          this.router.navigateByUrl("/pages/recipe/list");
        },(err)=>{
          this.toastr.error(err.error);
        })
      }


  }

  reset(){
    this.request= new AddRecipeRequest() ;
  }

}
