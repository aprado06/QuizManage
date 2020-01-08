import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { AppUser } from 'src/app/model/user.model';
import { Subscription } from 'rxjs';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit, OnDestroy {

  currentUser: AppUser;
  userSubscription: Subscription;
  show = false;
  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.userSubscription = this.authService.$currentUser.subscribe(user => {
      this.currentUser = user;
    });

  }

  ngOnDestroy() {
    this.userSubscription.unsubscribe();
  }

}
