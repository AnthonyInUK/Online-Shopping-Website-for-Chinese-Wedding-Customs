import { Component, OnInit } from '@angular/core';
import { UserRoleService } from './services/user-role.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'angular-ecommerce';
  currentUserRole: string = null;

  constructor(private userRoleService: UserRoleService) { }

  ngOnInit(): void {
    // Subscribe to user role changes
    this.userRoleService.userRole$.subscribe(role => {
      this.currentUserRole = role;
    });
  }

  getRoleText(): string {
    if (this.currentUserRole === 'GROOM') {
      return '新郎 Groom';
    } else if (this.currentUserRole === 'BRIDE') {
      return '新娘 Bride';
    }
    return '';
  }

  changeRole() {
    this.userRoleService.clearUserRole();
  }
}
