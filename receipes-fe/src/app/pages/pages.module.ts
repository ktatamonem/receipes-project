import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PagesRoutingModule } from './pages-routing.module';
import { FormsModule } from '@angular/forms';
import { IngredientListComponent } from './ingredient-list/ingredient-list.component';
import { CreateIngredientComponent } from './create-ingredient/create-ingredient.component';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { RecipesListComponent } from './recipes-list/recipes-list.component';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';
import { FilterRecipeComponent } from './filter-recipe/filter-recipe.component';



@NgModule({
  declarations: [
    IngredientListComponent,
    CreateIngredientComponent,
    RecipesListComponent,
    CreateRecipeComponent,
    FilterRecipeComponent
  ],
  imports: [
    CommonModule, 
    PagesRoutingModule,
    FormsModule
  ],
  providers: [
    NgbActiveModal
  ]
})
export class PagesModule { }
