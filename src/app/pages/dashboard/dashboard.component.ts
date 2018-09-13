import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HeaderComponent} from '../../common/header/header.component';
import { EmpName} from '../../_modal/empName';
import { DisplayNameService } from '../../_services/displayName/display-name.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  private empFirstName: EmpName;
  constructor(private route: ActivatedRoute,
    private router: Router,
    private displayNameService: DisplayNameService,
   private _HeaderComponent: HeaderComponent
  ) { }

  ngOnInit() {

    if (!sessionStorage.isLoggedIn  ) {
      this.router.navigateByUrl('/login');
    }
  }
//    displayName(userName) {
//      this.displayNameService.displayName(userName).subscribe((data) => {
//      console.log(data);
//      this.empFirstName = data ;
//   });
// }
}
