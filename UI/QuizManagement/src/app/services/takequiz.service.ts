import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReplaySubject, Subject } from 'rxjs';
import { Question } from '../model/question.model';
import { AppUser } from '../model/user.model';
import { Router } from '@angular/router';
import { Assign } from 'src/app/model/assign.model';

@Injectable({
  providedIn: 'root'
})
export class TakequizService {

  private currentQuestionStream = new ReplaySubject<Question[]>(1);
  $currentQuestion = this.currentQuestionStream.asObservable();

  private currentAssignStream = new ReplaySubject<Assign[]>(1);
  $currentAssign = this.currentAssignStream.asObservable();

  constructor(private httpClient: HttpClient, private router: Router) { }

  getQuestion(user: AppUser, Id) {
    if (user.role == '1') {
      this.httpClient.get<Question[]>(`http://localhost:8080/QuizManagement/quiz/take/${user.username}/${Id}`, {
        withCredentials: true
      })
        .subscribe(data => {
          this.currentQuestionStream.next(data);
        }, err => {
        });
    } else {
      this.router.navigateByUrl('/student');
    }
  }

  getIds(user: AppUser) {
    this.httpClient.get<Assign[]>(`http://localhost:8080/QuizManagement/quiz/id/${user.username}`, {
      withCredentials: true
    })
      .subscribe(data => {
        this.currentAssignStream.next(data);
      }, err => {
      });
  }

  submitQuestion(user: AppUser, answer: string, qid: string) {
    const ob = new AppUser(user.user_id, answer, qid, '');
    this.httpClient.post<AppUser[]>('http://localhost:8080/QuizManagement/quiz/submit', ob, {
      withCredentials: true
    })
      .subscribe(data => {
        //this.currentAssignStream.next(data);
      }, err => {
    });
  }

  gradeQuiz(grade: Assign) {
    this.httpClient.post<Assign[]>('http://localhost:8080/QuizManagement/quiz/grade', grade, {
      withCredentials: true
    })
      .subscribe(data => {
        //this.currentAssignStream.next(data);
      }, err => {
    });
  }

}
