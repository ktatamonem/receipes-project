import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private toastr :ToastrService


  ) { 
   
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });
  }
  get f() { return this.loginForm.controls; }


  onSubmit() {
    // stop here if form is invalid
    if(this.loginForm.value.username === "demo" && this.loginForm.value.password ==="demo"){
      this.router.navigate(['/']);
    }else {
      this.toastr.error("Wrong username or password , please check your credentials")
    }

  }

}
