import { Component, OnInit } from '@angular/core';
import { Assign } from 'src/app/model/assign.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';
import { AssignService } from 'src/app/services/assign.service';
import { AppUser } from 'src/app/model/user.model';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-assignquiz',
  templateUrl: './assignquiz.component.html',
  styleUrls: ['./assignquiz.component.scss']
})
export class AssignquizComponent implements OnInit {

  currentUser: AppUser;
  userSubscription: Subscription;

  currentTable: Assign[];
  tableSubscription: Subscription;

  model = new AppUser(0, '', '', '');
  submitted = false;

  constructor(private authService: AuthService, private httpClient: HttpClient, private router: Router,
              private assignService: AssignService) { }

  onSubmit() {
    let user = new AppUser(0, this.model.username, this.model.password, '');
    this.assignService.createAssign(user);
  }

  ngOnInit() {
    this.userSubscription = this.authService.$currentUser.subscribe(user => {
      this.currentUser = user;
    });
    if (this.currentUser.role == '1') {
      this.router.navigateByUrl('/student');
    }
    this.assignService.getAssign();
    this.tableSubscription = this.assignService.$currentAssign.subscribe(assign => {
      this.currentTable = assign;
    });
  }

}
