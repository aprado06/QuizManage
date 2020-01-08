import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReplaySubject, Subject } from 'rxjs';
import { NewQuestion } from '../model/newquestion.model';

@Injectable({
  providedIn: 'root'
})
export class CreatequizService {

  private currentQuestionStream = new ReplaySubject<NewQuestion[]>(1);
  $currentQuestion = this.currentQuestionStream.asObservable();

  constructor(private httpClient: HttpClient) { }

  createQuestion(question: NewQuestion) {

    this.httpClient.post<NewQuestion[]>('http://localhost:8080/QuizManagement/quiz/create', question, {
      withCredentials: true
    })
      .subscribe(data => {
        this.currentQuestionStream.next(data);
      }, err => {
    });
  }

}
