import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/model/student.model';
import { AuthService } from 'src/app/services/auth.service';
import { StudentService } from 'src/app/services/student.service';
import { AppUser } from 'src/app/model/user.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})

export class StudentComponent implements OnInit, OnDestroy {

  currentUser: AppUser;
  userSubscription: Subscription;

  currentTable: Student[];
  tableSubscription: Subscription;

  constructor(private authService: AuthService, private studentService: StudentService, private router: Router) { }

  ngOnInit() {
    this.userSubscription = this.authService.$currentUser.subscribe(user => {
      this.currentUser = user;
    });
    if (!this.currentUser) {
      this.router.navigateByUrl('/login');
    }

    this.studentService.getStud(this.currentUser);

    this.tableSubscription = this.studentService.$currentStudent.subscribe(student => {
      this.currentTable = student;
    });

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
