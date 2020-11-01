import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'app/services/auth.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  returnUrl: string;
  loginForm: FormGroup;
  constructor(private authService: AuthService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) {
    if (this.authService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }
  focus;
  focus1;
  test : Date = new Date();
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }

    let user = this.loginForm.get('email').value;
    let pass = this.loginForm.get('password').value;

    this.authService.login(user,pass)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);

        },
        error => {

        }
      )

  }

}
