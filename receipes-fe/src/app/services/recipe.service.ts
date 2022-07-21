import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddRecipeRequest } from '../model/add-recipe-request.model';
import { FilterRecipe } from '../model/filter-recipe.model';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(
    private httpClient:HttpClient
  ) { }


  listRecipes(filter :FilterRecipe):Observable<any>{
      return this.httpClient.post<any>("/api/recipe/search",filter); 
  }  


  createRecipe(request: AddRecipeRequest):Observable<any>{
    return this.httpClient.post<any>("/api/recipe/add", request);
  }

  deleteRecipe(id:number):Observable<any>{
    return this.httpClient.delete("/api/recipe/"+id);
  }



}
