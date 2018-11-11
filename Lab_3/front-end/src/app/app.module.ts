import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { ListUsersComponent } from './components/list-users/list-users.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { ViewUserComponent } from './components/view-user/view-user.component';
import { ViewService } from './services/view.service';
import { ListPartsComponent } from './components/list-parts/list-parts.component';
import { ViewPartComponent } from './components/view-part/view-part.component';
import { EditPartComponent } from './components/edit-part/edit-part.component';
import { ListComputersetsComponent } from './components/list-computersets/list-computersets.component';
import { ViewComputersetComponent } from './components/view-computerset/view-computerset.component';
import { EditComputersetComponent } from './components/edit-computerset/edit-computerset.component';


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
    EditComputersetComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,

    AppRoutingModule
  ],
  providers: [ViewService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
