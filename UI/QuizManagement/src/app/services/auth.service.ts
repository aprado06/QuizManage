import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AppUser } from '../model/user.model';
import { ReplaySubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentUserStream = new ReplaySubject<AppUser>(1);
  $currentUser = this.currentUserStream.asObservable();

  private loginErrorStream = new Subject<string>();
  $loginError = this.loginErrorStream.asObservable();


  constructor(private httpClient: HttpClient, private router: Router) {
    this.httpClient.get<AppUser>('http://localhost:8080/QuizManagement/auth/session-user', {
      withCredentials: true
    }).subscribe(
      data => {
        if (data === null) {
          this.router.navigateByUrl('/login');
        } else {
          this.currentUserStream.next(data);
        }
      },
      err => {
      }
    );
  }

  login(credentials) {
    this.httpClient.post<AppUser>('http://localhost:8080/QuizManagement/auth/login', credentials, {
      withCredentials: true
    }).subscribe(
      data => {
        this.router.navigateByUrl('/student');
        this.currentUserStream.next(data);
      },
      err => {
        this.loginErrorStream.next('Failed to Login');
      }
    );
  }

  logout() {
    this.currentUserStream.next(null);
    this.httpClient.post('http://localhost:8080/QuizManagement/auth/logout', {
      withCredentials: true
    }).subscribe(
      data => {
      },
      err => {
      }
    );
  }

}
