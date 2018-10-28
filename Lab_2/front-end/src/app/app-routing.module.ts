import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListBooksComponent} from './components/list-books/list-books.component';
import {ViewBookComponent} from './components/view-book/view-book.component';
// import {ListAuthorsComponent} from './components/list-authors/list-authors.component';
import {EditBookComponent} from './components/edit-book/edit-book.component';
import {ListUsersComponent} from './components/list-users/list-users.component';
import {EditUserComponent} from './components/edit-user/edit-user.component';
import {ListPartsComponent} from './components/list-parts/list-parts.component';
import {ViewUserComponent} from './components/view-user/view-user.component';
import {ViewPartComponent} from './components/view-part/view-part.component';
import {EditPartComponent} from './components/edit-part/edit-part.component';
import {ListComputersetsComponent} from './components/list-computersets/list-computersets.component';
import {ViewComputersetComponent} from './components/view-computerset/view-computerset.component';
import {EditComputersetComponent} from './components/edit-computerset/edit-computerset.component';

const routes: Routes = [
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
  /*
  {path: '', component: ListBooksComponent},
  {path: 'books/new', component: EditBookComponent},
  {path: 'books/:id', component: ViewBookComponent},
  {path: 'books/:id/edit', component: EditBookComponent},
  {path: 'authors', component: ListAuthorsComponent},
  */
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
