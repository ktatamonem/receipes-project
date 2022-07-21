import { RecipeIngredient } from "./recipe-ingredient.model";

export class  Recipe  {  
    id :  number;  
    name : string  ;  
    servingNumber: number;
    instruction:string;
    vegan:boolean;
    ingredients:RecipeIngredient[];
    

}