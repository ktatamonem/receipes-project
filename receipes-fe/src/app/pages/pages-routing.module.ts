import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CreateIngredientComponent } from "./create-ingredient/create-ingredient.component";
import { CreateRecipeComponent } from "./create-recipe/create-recipe.component";
import { IngredientListComponent } from "./ingredient-list/ingredient-list.component";
import { RecipesListComponent } from "./recipes-list/recipes-list.component";


const routes: Routes = [

    {
      path: "ingredient/list",
      component: IngredientListComponent,    
    },{
      path: "recipe/list",
      component: RecipesListComponent,  
    },
    {
      path: "recipe/add",
      component: CreateRecipeComponent,
    }
   
  ];
  
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class PagesRoutingModule { }
  