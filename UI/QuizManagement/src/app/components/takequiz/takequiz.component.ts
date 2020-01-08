import { Component, OnInit, OnDestroy, ElementRef, ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { Question } from 'src/app/model/question.model';
import { AuthService } from 'src/app/services/auth.service';
import { TakequizService } from 'src/app/services/takequiz.service';
import { AppUser } from 'src/app/model/user.model';
import { Subscription } from 'rxjs';
import { Assign } from 'src/app/model/assign.model';

@Component({
  selector: 'app-takequiz',
  templateUrl: './takequiz.component.html',
  styleUrls: ['./takequiz.component.scss']
})
export class TakequizComponent implements OnInit, OnDestroy {

  currentUser: AppUser;
  userSubscription: Subscription;

  currentTable: Question[];
  tableSubscription: Subscription;

  otherTable: Assign[];
  otherSubscription: Subscription;

  model = new Assign('', '');
  show: boolean = false;

  constructor(private authService: AuthService, private takequizService: TakequizService,
              private router: Router) { }

  ngOnInit() {
    this.userSubscription = this.authService.$currentUser.subscribe(user => {
      this.currentUser = user;
    });
    if (!this.currentUser) {
      this.router.navigateByUrl('/login');
    }

    this.takequizService.getIds(this.currentUser);

    this.otherSubscription = this.takequizService.$currentAssign.subscribe(assign => {
      this.otherTable = assign;
    });

  }

  onSubmit() {
    this.takequizService.getQuestion(this.currentUser, this.model.quiz_id);
    this.tableSubscription = this.takequizService.$currentQuestion.subscribe(question => {
      this.currentTable = question;
    });
    this.show = true;
  }

  onItemChange(event){
    let arr = (event.target.value).split(/\s+/);
    this.takequizService.submitQuestion(this.currentUser, arr[0], arr[1]);
  }

  submit2() {
    let quiz = new Assign(this.currentUser.user_id.toString(), this.model.quiz_id, 0);
    this.takequizService.gradeQuiz(quiz);
    this.router.navigateByUrl('/#');
  }

  ngOnDestroy() {
    if (this.userSubscription !== undefined) {
      this.userSubscription.unsubscribe();
    }
    if (this.tableSubscription !== undefined) {
      this.tableSubscription.unsubscribe();
    }
  }

}
