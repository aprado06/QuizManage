import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AuthService } from './services/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { StudentComponent } from './components/student/student.component';
import { StudentService } from './services/student.service';
import { NavComponent } from './components/nav/nav.component';
import { TakequizComponent } from './components/takequiz/takequiz.component';
import { CreatequizComponent } from './components/createquiz/createquiz.component';
import { AssignquizComponent } from './components/assignquiz/assignquiz.component';
import { TakequizService } from './services/takequiz.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    StudentComponent,
    NavComponent,
    TakequizComponent,
    CreatequizComponent,
    AssignquizComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [
    AuthService,
    StudentService,
    TakequizService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
