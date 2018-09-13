import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService} from '../../_services/authentication.service/authentication.service';
import { Injectable } from '@angular/core';
import { AlertService} from '../../_services/alert.service/alert.service';
import { DisplayNameService } from '../../_services/displayName/display-name.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})


export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;
  returnUrl: string;
  response: {};
  message: string;

  constructor(

    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService,
    private displayNameService: DisplayNameService
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });

  }
  get loginFormFields() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
        return;
    }

    this.authenticationService.login(this.loginFormFields.username.value, this.loginFormFields.password.value)
    .subscribe((data) => {
      this.response = data;
      sessionStorage.setItem('userInfo', JSON.stringify(data));
      sessionStorage.setItem('isLoggedIn', 'true');
      localStorage.setItem('username', data['username']);
      this.displayNameService.setUsername(data['username']);
      this.router.navigateByUrl('/dashboard');
      },
    error => {
    this.message = 'Username or Password Invalid';
    } ) ;

}
}
