import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {UserCreateComponent} from './components/user-create/user-create.component';
import {ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import { UserEditComponent } from './components/user-edit/user-edit.component';
import { UserPhoneListComponent } from './components/user-phone-list/user-phone-list.component';
import { UserPhoneCreateComponent } from './components/user-phone-create/user-phone-create.component';
import { UserPhoneEditComponent } from './components/user-phone-edit/user-phone-edit.component';
import { UserPhoneVerificationModalComponent } from './components/user-phone-verification-modal/user-phone-verification-modal.component';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserCreateComponent,
    UserEditComponent,
    UserPhoneListComponent,
    UserPhoneCreateComponent,
    UserPhoneEditComponent,
    UserPhoneVerificationModalComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
