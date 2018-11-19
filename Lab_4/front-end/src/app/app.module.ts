import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from './app-routing.module';
import {ListBooksComponent} from './components/list-books/list-books.component';
import {ViewBookComponent} from './components/view-book/view-book.component';
import {HttpClientModule} from '@angular/common/http';
import {BooksService} from './services/books.service';
import {ListAuthorsComponent} from './components/list-authors/list-authors.component';
import {EditBookComponent} from './components/edit-book/edit-book.component';
import {FormsModule} from '@angular/forms';
import { ListUsersComponent } from './components/user/list-users/list-users.component';
import {UsersService} from './components/user/services/users.service';
import { ViewUserComponent } from './components/user/view-user/view-user.component';
import { EditUserComponent } from './components/user/edit-user/edit-user.component';
import { ListPartsComponent } from './components/part/list-parts/list-parts.component';
import {PartsService} from './components/part/services/parts.services';
import { ViewPartComponent } from './components/part/view-part/view-part.component';
import { EditPartComponent } from './components/part/edit-part/edit-part.component';
import { ListComputerSetComponent } from './components/computer-set/list-computer-set/list-computer-set.component';
import {ComputerSetService} from './components/computer-set/services/computer-set.service';
import { ViewComputerSetComponent } from './components/computer-set/view-computer-set/view-computer-set.component';
import { EditComputerSetComponent } from './components/computer-set/edit-computer-set/edit-computer-set.component';
import {SharedService} from './shared/services/shared.service';


@NgModule({
  declarations: [
    AppComponent,
    ListBooksComponent,
    ViewBookComponent,
    ListAuthorsComponent,
    EditBookComponent,
    ListUsersComponent,
    ViewUserComponent,
    EditUserComponent,
    ListPartsComponent,
    ViewPartComponent,
    EditPartComponent,
    ListComputerSetComponent,
    ViewComputerSetComponent,
    EditComputerSetComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,

    AppRoutingModule
  ],
  providers: [BooksService, UsersService, PartsService, ComputerSetService, SharedService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
