import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FilterRecipe } from '../../model/filter-recipe.model';
import { Ingredient } from '../../model/ingredient.model';
import { IngredientService } from '../../services/ingredient.service';

@Component({
  selector: 'app-filter-recipe',
  templateUrl: './filter-recipe.component.html',
  styleUrls: ['./filter-recipe.component.scss']
})
export class FilterRecipeComponent implements OnInit {


  @Input()
  filter :FilterRecipe = new FilterRecipe() ; 

  @Output()
  submitFilter= new EventEmitter();

  ingredientList:Ingredient[];

  constructor(
    private ingredientService :IngredientService 
  ) { }

  ngOnInit(): void {
    this.ingredientService.retrieveIngredients().subscribe(data =>{
      this.ingredientList =data ;
    })
  }

  search(){
    this.submitFilter.emit({
      data:this.filter
    })
  }

}
