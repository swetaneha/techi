import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule , ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { FooterComponent } from './common/footer/footer.component';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AppRoutingModule } from '../app-routing.module';
import { AdminConfigComponent } from './pages/adminConfig/admin-config.component';
import { CreateGroupComponent } from './pages/createGroup/create-group.component';
import { HttpClientModule } from '@angular/common/http';
import {AuthenticationService} from './_services/authentication.service/authentication.service';
import {TitleService} from './_services/title.service/title.service';
import {AlertService} from './_services/alert.service/alert.service';
import { DisplayNameService } from './_services/displayName/display-name.service';
import { SidenavComponent } from './common/sidenav/sidenav.component';
import { CreateTicketTypeComponent } from './pages/create-ticket-type/create-ticket-type.component';
import { TicketTypeDashboardComponent } from './pages/tickettypedashboard/tickettypedashboard.component';
import { DashboardTicketService } from './_services/dashboard-ticket-type/dashboardticket.service';
import { SharedTicketTypeService } from './_services/shared-ticket-type/sharedtickettype.service';
import { UpdateGroupComponent } from './pages/update-group/update-group.component';
import { CreateGroupService } from './_services/createGroup/create-group.service';
import { GetAllEmployessService } from './_services/getAllEmployees/get-all-employees.service';
import { GetGroupsService } from './_services/getGroups/get-groups.service';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown/angular2-multiselect-dropdown';
import { WorkflowCreateComponent } from './pages/workflow-create/workflow-create.component';
import { WorkflowComponent } from './pages/workflow/workflow.component';
import { SharedWorkflowService } from './_services/shared-workflow/shared-workflow.service';
import { WorkflowService } from './_services/workFlow/work-flow.service';
import { NgDragDropModule } from 'ng-drag-drop';
import { NgxSpinnerModule } from 'ngx-spinner';
import { CreateStatusComponent } from './pages/create-status/create-status.component';
import { ListStatusComponent } from './pages/list-status/list-status.component';
import { UpdateStatusComponent } from './pages/update-status/update-status.component';
import { CreateServiceService } from 'src/app/_services/createStatus/create-status.service';
import { UpdateStatusService } from 'src/app/_services/updateStatus/update-status.service';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    DashboardComponent,
    AdminConfigComponent,
    CreateGroupComponent,
    SidenavComponent,
    CreateTicketTypeComponent,
    TicketTypeDashboardComponent,
    UpdateGroupComponent,
    WorkflowCreateComponent,
    WorkflowComponent,
    CreateStatusComponent,
    ListStatusComponent,
    UpdateStatusComponent

   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularMultiSelectModule,
    HttpClientModule,
    NgxSpinnerModule,
    FormsModule,
    ReactiveFormsModule,
    NgDragDropModule.forRoot(),
  ],
  providers: [AuthenticationService, TitleService, AlertService, DisplayNameService, UpdateStatusService, CreateServiceService, SharedWorkflowService, WorkflowService, HeaderComponent,DashboardTicketService,SharedTicketTypeService, GetGroupsService, CreateGroupService,GetAllEmployessService],
  bootstrap: [AppComponent]
})
export class AppModule { }
