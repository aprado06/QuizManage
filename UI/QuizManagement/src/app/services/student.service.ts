import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReplaySubject, Subject } from 'rxjs';
import { Student } from '../model/student.model';
import { AppUser } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private currentStudentStream = new ReplaySubject<Student[]>(1);
  $currentStudent = this.currentStudentStream.asObservable();

  constructor(private httpClient: HttpClient) { }

  getStud(user: AppUser) {

    if (user.role == '0') {
      this.httpClient.get<Student[]>('http://localhost:8080/QuizManagement/main', {
        withCredentials: true
      })
        .subscribe(data => {
          this.currentStudentStream.next(data);
        }, err => {
        });
    } else {
      this.httpClient.get<Student[]>(`http://localhost:8080/QuizManagement/main?username=${user.username}`, {
        withCredentials: true
      })
        .subscribe(data => {
          this.currentStudentStream.next(data);
        }, err => {
        });
    }
  }
}
