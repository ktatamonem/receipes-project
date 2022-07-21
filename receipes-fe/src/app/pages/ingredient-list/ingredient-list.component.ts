import { Component, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Ingredient } from '../../model/ingredient.model';
import { IngredientService } from '../../services/ingredient.service';
import { CreateIngredientComponent } from '../create-ingredient/create-ingredient.component';

@Component({
  selector: 'app-ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: ['./ingredient-list.component.scss']
})
export class IngredientListComponent implements OnInit {


  ingredientList:Ingredient[]=[] ; 


  constructor(
    private ingredientService:IngredientService,
    private modalService :NgbModal,
    private toastr :ToastrService

  ) { }

  ngOnInit(): void {
   this.retrieveIngredientData();
  }

  retrieveIngredientData(){
    this.ingredientService.retrieveIngredients().subscribe(data =>{
      this.ingredientList = data ;
    })
  }

  create(){
    this.modalService.open(CreateIngredientComponent).dismissed.subscribe(data =>{
      this.retrieveIngredientData();
    });
  }

  edit(ingredient:Ingredient){
    const modal = this.modalService.open(CreateIngredientComponent);
    modal.dismissed.subscribe(data=>{
      this.retrieveIngredientData();
    })
    modal.componentInstance.ingredient= ingredient; 
    modal.componentInstance.editMode =true ; 
  }

  delete(ingredient:Ingredient , content:any){
    this.modalService.open(content).closed.subscribe(data=>{
      if(data === "Ok"){
        this.ingredientService.deleteIngredient(ingredient.id).subscribe(()=>{
           this.toastr.success("Ingredient deleted successfully");
           this.ingredientList = this.ingredientList.filter(item=> item.id != ingredient.id)
        },(err)=>{
          this.toastr.error("Unable to delete this ingredient")

        }
        )
      }
    })
    
  }

}
