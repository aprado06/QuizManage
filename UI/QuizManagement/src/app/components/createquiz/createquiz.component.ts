import { Component, OnInit } from '@angular/core';
import { NewQuestion } from 'src/app/model/newquestion.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';
import { AppUser } from 'src/app/model/user.model';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { CreatequizService } from 'src/app/services/createquiz.service';

@Component({
  selector: 'app-createquiz',
  templateUrl: './createquiz.component.html',
  styleUrls: ['./createquiz.component.scss']
})
export class CreatequizComponent implements OnInit {

  currentUser: AppUser;
  userSubscription: Subscription;

  question: NewQuestion[] = [];
  model = new NewQuestion('', '', '', '', '', '', '');
  constructor(private authService: AuthService, private httpClient: HttpClient, private router: Router, 
              private createQues: CreatequizService) { }

  ngOnInit() {
    this.userSubscription = this.authService.$currentUser.subscribe(user => {
      this.currentUser = user;
    });
    if (!this.currentUser) {
      this.router.navigateByUrl('/login');
    } else if (this.currentUser.role == '1') {
      this.router.navigateByUrl('/student');
    }
  }

  onSubmit() {
    let ques = new NewQuestion(this.model.question_id, this.model.question, this.model.answer1, this.model.answer2,
                               this.model.answer3, this.model.answer4, this.model.correct);
    this.createQues.createQuestion(ques);
  }

  save(question: NewQuestion) {
    this.httpClient.post<NewQuestion>('http://localhost:8080/QuizManagement/quiz/create', question, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }
}
