import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { StudentComponent } from './components/student/student.component';
import { TakequizComponent } from './components/takequiz/takequiz.component';
import { CreatequizComponent } from './components/createquiz/createquiz.component';
import { AssignquizComponent } from './components/assignquiz/assignquiz.component';


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'student',
    component: StudentComponent
  },
  {
    path: 'takequiz',
    component: TakequizComponent
  },
  {
    path: 'createquiz',
    component: CreatequizComponent
  },
  {
    path: 'assignquiz',
    component: AssignquizComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
