import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { ListUsersComponent } from './components/user/list-users/list-users.component';
import { EditUserComponent } from './components/user/edit-user/edit-user.component';
import { UserService } from './components/user/services/user.service';
import { ViewUserComponent } from './components/user/view-user/view-user.component';
import { ListPartsComponent } from './components/part/list-parts/list-parts.component';
import { PartService } from './components/part/services/part.service';
import { ViewPartComponent } from './components/part/view-part/view-part.component';
import { EditPartComponent } from './components/part/edit-part/edit-part.component';
import { ComputersetService } from './components/computerset/services/computerset.service';
import { ListComputersetsComponent } from './components/computerset/list-computersets/list-computersets.component';
import { ViewComputersetComponent } from './components/computerset/view-computerset/view-computerset.component';
import { EditComputersetComponent } from './components/computerset/edit-computerset/edit-computerset.component';
import {SharedService} from './shared/services/shared.service';
import { PaginationComponent } from './components/computerset/pagination/pagination.component';

@NgModule({
  declarations: [
    AppComponent,
    ListUsersComponent,
    EditUserComponent,
    ViewUserComponent,
    ListPartsComponent,
    ViewPartComponent,
    EditPartComponent,
    ListComputersetsComponent,
    ViewComputersetComponent,
    EditComputersetComponent,
    PaginationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [UserService, PartService, ComputersetService, SharedService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
