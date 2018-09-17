import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent} from './app/pages/login/login.component';
import { DashboardComponent} from './app/pages/dashboard/dashboard.component';
import { AdminConfigComponent } from './app/pages/adminConfig/admin-config.component';
import { CreateGroupComponent } from './app/pages/createGroup/create-group.component';
// import { AuthGuard } from './app/_services/_guards/auth.guard';
import {TicketTypeDashboardComponent} from './app/pages/tickettypedashboard/tickettypedashboard.component';
import { CreateTicketTypeComponent} from './app/pages/create-ticket-type/create-ticket-type.component';
import { UpdateGroupComponent } from './app/pages/update-group/update-group.component';
import { WorkflowComponent } from './app/pages/workflow/workflow.component';
import { WorkflowCreateComponent } from './app/pages/workflow-create/workflow-create.component';
import { ListStatusComponent } from 'src/app/pages/list-status/list-status.component';
import { CreateStatusComponent } from 'src/app/pages/create-status/create-status.component';
import { UpdateStatusComponent } from 'src/app/pages/update-status/update-status.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent,  data: {title: 'Login'} },
  { path: 'dashboard', component: DashboardComponent, data: {title: 'Dashboard'}},
  { path: 'admin', component: AdminConfigComponent , data: {title: 'AdminConfiguration'}},
  { path: 'group', component: CreateGroupComponent , data: {title: 'CreateGroup'}},
  { path: 'createtickettype/:id', component: CreateTicketTypeComponent, data: {title: 'CreateTicketType'}},
  { path: 'dashboardtickettype', component: TicketTypeDashboardComponent, data: {title: 'TicketType'} },
  { path: 'group/edit/:id', component: UpdateGroupComponent , data: {title: 'UpdateGroup'} },
  { path: 'workflowDisplay', component: WorkflowComponent, data: {title: 'WorkFlow'} },
  { path: 'createWorkflowPage', component: WorkflowCreateComponent, data: {title: 'CreateWorkFlow'} },
  { path: 'liststatus', component: ListStatusComponent, data: {title: 'Status'}, },
  { path: 'createStatus', component: CreateStatusComponent, data: {title: 'CreateStatus'}, },
  { path: 'updateStatus', component: UpdateStatusComponent, data: {title: 'UpdateStatus'}, },
];

@NgModule({
  imports: [ CommonModule, RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
})
export class AppRoutingModule { }
