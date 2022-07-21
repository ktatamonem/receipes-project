import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AddIngredientRequest } from '../../model/create.ingredient.request.model';
import { Ingredient } from '../../model/ingredient.model';
import { IngredientService } from '../../services/ingredient.service';

@Component({
  selector: 'app-create-ingredient',
  templateUrl: './create-ingredient.component.html',
  styleUrls: ['./create-ingredient.component.scss']
})
export class CreateIngredientComponent implements OnInit {

   createIngredientModel :AddIngredientRequest  = new AddIngredientRequest(); 

   @Input()
   ingredient:Ingredient ;
   
   @Input()
   editMode:boolean;

  constructor(private ingredientService:IngredientService 
    , public activeModal: NgbActiveModal
    , private toastr :ToastrService
    ) { }

  ngOnInit(): void {
    
  }

  create(){
    if(this.createIngredientModel.name){
      this.ingredientService.createIngredient(this.createIngredientModel).subscribe(data =>{
           this.toastr.success(" Ingredient added successfully "); 
           this.activeModal.dismiss();
      },(err)=>{
        this.toastr.error(err.error.name);
      })
    }

  }

  edit(){
    if(this.ingredient.name){
      this.ingredientService.updateIngredient(this.ingredient).subscribe(data =>{
        this.toastr.success(" Ingredient updated successfully "); 
        this.activeModal.dismiss();   
         },(err)=>{
          this.toastr.error(err.error.name);
        })
    }

  }

}
