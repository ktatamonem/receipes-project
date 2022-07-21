import { Ingredient } from "./ingredient.model";

export class FilterRecipe {
    recipeName:string; 
    vegan:boolean;
    servingNumber:number;
    includedIngredients :Ingredient[]=[];
    excludedIngredients: Ingredient[]=[];
    instruction:string;
    pageNum:number;
    pageSize:number;
}