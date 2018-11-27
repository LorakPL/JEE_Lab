import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './components/app/app.component';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { ListUsersComponent } from './components/user/list-users/list-users.component';
import {CustomersService} from './components/user/services/customers.service';
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
import { LoginComponent } from './components/authentication/login/login.component';
import {LoginService} from './components/authentication/services/login.service';
import {AuthenticationService} from './components/authentication/services/authentication.service';
import { TestComponent } from './components/test/test.component';
import {JwtInterceptor} from './components/authentication/helpers/jwtInterceptor';
import {ErrorInterceptor} from './components/authentication/helpers/errorInterceptor';
import {AuthGuard} from './components/authentication/guards/authGuard';


@NgModule({
  declarations: [
    AppComponent,
    ListUsersComponent,
    ViewUserComponent,
    EditUserComponent,
    ListPartsComponent,
    ViewPartComponent,
    EditPartComponent,
    ListComputerSetComponent,
    ViewComputerSetComponent,
    EditComputerSetComponent,
    LoginComponent,
    TestComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,

    AppRoutingModule
  ],
  providers: [AuthGuard, CustomersService, PartsService, ComputerSetService, SharedService, LoginService, AuthenticationService,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
