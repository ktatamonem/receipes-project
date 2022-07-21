import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { FilterRecipe } from '../../model/filter-recipe.model';
import { Recipe } from '../../model/recipe.model';
import { RecipeService } from '../../services/recipe.service';

@Component({
  selector: 'app-recipes-list',
  templateUrl: './recipes-list.component.html',
  styleUrls: ['./recipes-list.component.scss']
})
export class RecipesListComponent implements OnInit {

  recipeList:Recipe[] =[]; 

  filterRecipe:FilterRecipe = new FilterRecipe() ; 

  constructor(
    private recipeService :RecipeService,
    private router :Router,
    private modalService:NgbModal,
    private toastr:ToastrService
  ) { }

  ngOnInit(): void {
    this.filterRecipe.pageNum=0 ;
    this.filterRecipe.pageSize=10;
    this.retrieveRecipesByFilter();
  }


  retrieveRecipesByFilter(){
    this.recipeService.listRecipes(this.filterRecipe).subscribe(data=>{
      console.log(data);
      this.recipeList = data.content ; 
    },(err)=>{
      console.log(err);
    })
  }

  goToCreate(){
    this.router.navigateByUrl("/pages/recipe/add")
  }

  filter(evt:any){
    this.filterRecipe = evt.data ;
    this.filterRecipe.pageNum=0 ; 
    this.filterRecipe.pageSize =10 ; 
    this.retrieveRecipesByFilter() ;
  }

  delete(recipe:Recipe , content:any){
    this.modalService.open(content).closed.subscribe(data=>{
      if(data === "Ok"){
        this.recipeService.deleteRecipe(recipe.id).subscribe(()=>{
           this.toastr.success("Recipe deleted successfully");
           this.recipeList = this.recipeList.filter(item=> item.id != recipe.id)
        },(err)=>{
          this.toastr.error("Unable to delete this recipe")

        }
        )
      }
    })
    
  }


  

}
