import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListUsersComponent} from './components/user/list-users/list-users.component';
import {EditUserComponent} from './components/user/edit-user/edit-user.component';
import {ViewUserComponent} from './components/user/view-user/view-user.component';
import {ListPartsComponent} from './components/part/list-parts/list-parts.component';
import {ViewPartComponent} from './components/part/view-part/view-part.component';
import {EditPartComponent} from './components/part/edit-part/edit-part.component';
import {ListComputerSetComponent} from './components/computer-set/list-computer-set/list-computer-set.component';
import {ViewComputerSetComponent} from './components/computer-set/view-computer-set/view-computer-set.component';
import {EditComputerSetComponent} from './components/computer-set/edit-computer-set/edit-computer-set.component';

const routes: Routes = [
  {path: '', component: ListUsersComponent},
  {path: 'usersLink/new', component: EditUserComponent},
  {path: 'usersLink/:id/edit', component: EditUserComponent},
  {path: 'usersLink/:id', component: ViewUserComponent},
  {path: 'partsLink', component: ListPartsComponent},
  {path: 'partsLink/:id', component: ViewPartComponent},
  {path: 'tmp/new', component: EditPartComponent},
  {path: 'partsLink/:id/edit', component: EditPartComponent},
  {path: 'computerSetsLink', component: ListComputerSetComponent},
  {path: 'computerSetsLink/:id', component: ViewComputerSetComponent},
  {path: 'tmp2/new', component: EditComputerSetComponent},
  {path: 'computerSetsLink/:id/edit', component: EditComputerSetComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
