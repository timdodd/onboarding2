import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {UserDetailsComponent} from './components/user-details/user-details.component';
import {ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import { UserDetailsEditComponent } from './components/user-details-edit/user-details-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserDetailsComponent,
    UserDetailsEditComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
