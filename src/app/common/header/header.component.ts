import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { DisplayNameService } from '../../_services/displayName/display-name.service';
import { EmpName } from '../../_modal/empName';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isLoggedIn: boolean;
  username: string;
  employeeDetails: EmpName;

  isHeaderEnabled = false;


  constructor(private route: ActivatedRoute,
    private router: Router,
    private displayNameService: DisplayNameService
  ) {
  }

  test() {
    this.username = localStorage.getItem('username');
    this.displayNameService.displayName(this.username)
      .subscribe(data => {
        this.employeeDetails = data;
      });
    if (sessionStorage.isLoggedIn) {
      this.isLoggedIn = sessionStorage.isLoggedIn;

    } else {
      this.isLoggedIn = false;
    }
  }
  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        let url = event.url;

        if (url.endsWith('/')) {
          url = url.slice(0, url.length - 1);
        }

        if (url === '/login' || url === '') {
          this.isHeaderEnabled = false;
        } else {
          this.isHeaderEnabled = true;
          this.test();
        }
      }
    });
  }

  logOut() {
    this.isHeaderEnabled = false;
    sessionStorage.removeItem('isLoggedIn');
    localStorage.removeItem('username');
    this.router.navigateByUrl('/login');
  }

  adminConfiguration() {
    this.router.navigateByUrl('admin');
  }

}
