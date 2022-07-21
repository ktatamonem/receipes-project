import { RecipeIngredient } from "./recipe-ingredient.model";

export class AddRecipeRequest{
    name:string; 
    vegan:boolean ; 
    servingNumber: number ; 
    instruction:string;
    ingredients:RecipeIngredient[]=[];
}