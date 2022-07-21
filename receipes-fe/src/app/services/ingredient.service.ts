import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddIngredientRequest } from '../model/create.ingredient.request.model';
import { Ingredient } from '../model/ingredient.model';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  constructor(private http : HttpClient) { }

  public retrieveIngredients():Observable<any> {
    return this.http.get("/api/ingredient/list");
  }

  public searchIngredients(name:String):Observable<any> {
    return this.http.get("/api/ingredient/search/"+name);
  }


  public createIngredient(createIngredient : AddIngredientRequest):Observable<any>{
    return this.http.post<any>("/api/ingredient/add",createIngredient); 
  }

  public updateIngredient(ingredient : Ingredient):Observable<any>{
    return this.http.put<any>("/api/ingredient/update",ingredient); 
  }

  public deleteIngredient(id:number):Observable<any>{
    return this.http.delete("/api/ingredient/"+id);
  }



}
