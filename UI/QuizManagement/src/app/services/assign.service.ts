import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReplaySubject, Subject } from 'rxjs';
import { Assign } from '../model/assign.model';
import { AppUser } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AssignService {

  private currentAssignStream = new ReplaySubject<Assign[]>(1);
  $currentAssign = this.currentAssignStream.asObservable();

  constructor(private httpClient: HttpClient) { }

  getAssign() {
    this.httpClient.get<Assign[]>('http://localhost:8080/QuizManagement/quiz/assign', {
        withCredentials: true
      })
        .subscribe(data => {
          this.currentAssignStream.next(data);
        }, err => {
        });
  }

  createAssign(user: AppUser) {

    this.httpClient.post<AppUser[]>('http://localhost:8080/QuizManagement/quiz/assign', user, {
      withCredentials: true
    })
      .subscribe(data => {
        //this.currentAssignStream.next(data);
      }, err => {
    });
  }
}
