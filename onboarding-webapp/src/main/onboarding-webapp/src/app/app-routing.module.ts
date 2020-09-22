import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserListComponent} from './components/user-list/user-list.component';
import {UserCreateComponent} from './components/user-create/user-create.component';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {UserEditComponent} from './components/user-edit/user-edit.component';
import {UserPhoneListComponent} from './components/user-phone-list/user-phone-list.component';
import {UserPhoneCreateComponent} from './components/user-phone-create/user-phone-create.component';
import {UserPhoneEditComponent} from "./components/user-phone-edit/user-phone-edit.component";
import {UserPhoneVerificationModalComponent} from "./components/user-phone-verification-modal/user-phone-verification-modal.component";

const routes: Routes = [
  {
    path: '',
    component: UserListComponent,
  },
  {
    path: 'createUser',
    component: UserCreateComponent,
  },
  {
    path: 'editUser',
    component: UserEditComponent,
  },
  {
    path: 'userPhones',
    component: UserPhoneListComponent
  },
  {
    path: 'createPhone',
    component: UserPhoneCreateComponent
  },
  {
    path: 'editPhone',
    component: UserPhoneEditComponent
  },
  {
    path: 'verifyPhone',
    component: UserPhoneVerificationModalComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy},
  ]
})
export class AppRoutingModule {
}
