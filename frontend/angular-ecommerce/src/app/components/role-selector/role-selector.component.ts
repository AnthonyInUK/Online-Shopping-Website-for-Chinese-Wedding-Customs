import { Component, OnInit } from '@angular/core';
import { UserRoleService } from 'src/app/services/user-role.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-role-selector',
    templateUrl: './role-selector.component.html',
    styleUrls: ['./role-selector.component.css']
})
export class RoleSelectorComponent implements OnInit {

    selectedRole: string = null;
    showSelector: boolean = true;

    constructor(private userRoleService: UserRoleService, private router: Router) { }

    ngOnInit(): void {
        // Check if role has already been selected
        this.userRoleService.userRole$.subscribe(role => {
            this.selectedRole = role;
            this.showSelector = !role;
        });
    }

    selectRole(role: string) {
        this.selectedRole = role;
        this.userRoleService.setUserRole(role);
        this.showSelector = false;

        // Navigate to the first category for this role
        let firstCategoryId: number;
        if (role === 'GROOM') {
            firstCategoryId = 2; // Groom Attire
        } else if (role === 'BRIDE') {
            firstCategoryId = 3; // Bridal Wear
        } else {
            firstCategoryId = 1; // Default category
        }

        // Navigate to the product page for this category
        console.log('Navigating to URL:', `/category/${firstCategoryId}`);
        this.router.navigateByUrl(`/category/${firstCategoryId}`).then(() => {
            console.log('Navigation completed to category:', firstCategoryId);
        });
    }

    changeRole() {
        this.userRoleService.clearUserRole();
        this.showSelector = true;
    }

    getRoleText(): string {
        if (this.selectedRole === 'GROOM') {
            return '新郎';
        } else if (this.selectedRole === 'BRIDE') {
            return '新娘';
        }
        return '';
    }
}

