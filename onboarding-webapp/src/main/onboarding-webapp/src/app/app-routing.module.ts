import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserListComponent} from './components/user-list/user-list.component';
import {UserDetailsComponent} from './components/user-details/user-details.component';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {UserDetailsEditComponent} from './components/user-details-edit/user-details-edit.component';

const routes: Routes = [
  {
    path: '',
    component: UserListComponent,
  },
  {
    path: 'createUser',
    component: UserDetailsComponent,
  },
  {
    path: 'editUser',
    component: UserDetailsEditComponent,
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
