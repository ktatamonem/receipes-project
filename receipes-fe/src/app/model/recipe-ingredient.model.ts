import { Ingredient } from "./ingredient.model";
import { Recipe } from "./recipe.model";

export class RecipeIngredient {
    id:number ; 
    recipe:Recipe; 
    ingredient:Ingredient; 
    quantity:number;
}