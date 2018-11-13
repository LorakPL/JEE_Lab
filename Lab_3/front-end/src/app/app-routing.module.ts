import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListUsersComponent} from './components/user/list-users/list-users.component';
import {EditUserComponent} from './components/user/edit-user/edit-user.component';
import {ViewUserComponent} from './components/user/view-user/view-user.component';
import {ListPartsComponent} from './components/part/list-parts/list-parts.component';
import {ViewPartComponent} from './components/part/view-part/view-part.component';
import {EditPartComponent} from './components/part/edit-part/edit-part.component';
import {EditComputersetComponent} from './components/computerset/edit-computerset/edit-computerset.component';
import {ViewComputersetComponent} from './components/computerset/view-computerset/view-computerset.component';
import {ListComputersetsComponent} from './components/computerset/list-computersets/list-computersets.component';
import {PaginationComponent} from './components/computerset/pagination/pagination.component';
/*
import {ListUsersComponent} from './components/list-users/list-users.component';
import {EditUserComponent} from './components/edit-user/edit-user.component';
import {ListPartsComponent} from './components/list-parts/list-parts.component';
import {ViewUserComponent} from './components/view-user/view-user.component';
import {ViewPartComponent} from './components/view-part/view-part.component';
import {EditPartComponent} from './components/edit-part/edit-part.component';
import {ListComputersetsComponent} from './components/list-computersets/list-computersets.component';
import {ViewComputersetComponent} from './components/view-computerset/view-computerset.component';
import {EditComputersetComponent} from './components/edit-computerset/edit-computerset.component';
*/

const routes: Routes = [
  {path: '', component: ListUsersComponent},
  {path: 'usersLink/:link/edit', component: EditUserComponent},
  {path: 'usersLink/new', component: EditUserComponent},
  {path: 'usersLink/:link', component: ViewUserComponent},
  {path: 'partsLink', component: ListPartsComponent},
  {path: 'partsLink/:link', component: ViewPartComponent},
  {path: 'tmp/new', component: EditPartComponent},
  {path: 'partsLink/:link/edit', component: EditPartComponent},
  {path: 'computerSetsLink', component: ListComputersetsComponent},
  {path: 'computerSetsLink/:link', component: ViewComputersetComponent},
  {path: 'tmp2/new', component: EditComputersetComponent},
  {path: 'computerSetsLink/:link/edit', component: EditComputersetComponent},
  {path: 'pagination', component: PaginationComponent}

  /*
  {path: '', component: ListUsersComponent},
  {path: 'usersLink/new', component: EditUserComponent},
  {path: 'usersLink/:id/edit', component: EditUserComponent},
  {path: 'partsLink', component: ListPartsComponent},
  {path: 'usersLink/:id', component: ViewUserComponent},
  {path: 'partsLink/:id', component: ViewPartComponent},
  {path: 'tmp/new', component: EditPartComponent},
  {path: 'partsLink/:id/edit', component: EditPartComponent},
  {path: 'computerSetsLink', component: ListComputersetsComponent},
  {path: 'computerSetsLink/:id', component: ViewComputersetComponent},
  {path: 'tmp2/new', component: EditComputersetComponent},
  {path: 'computerSetsLink/:id/edit', component: EditComputersetComponent},
  */
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
