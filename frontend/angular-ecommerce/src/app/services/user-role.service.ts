import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UserRoleService {

    // User role: 'GROOM' | 'BRIDE' | null
    private userRoleSubject: BehaviorSubject<string> = new BehaviorSubject<string>(null);
    public userRole$ = this.userRoleSubject.asObservable();

    constructor() {
        // Restore user role from localStorage
        const savedRole = localStorage.getItem('userRole');
        if (savedRole) {
            this.userRoleSubject.next(savedRole);
        }
    }

    // Set user role
    setUserRole(role: string) {
        this.userRoleSubject.next(role);
        localStorage.setItem('userRole', role);
    }

    // Get current user role
    getUserRole(): string {
        return this.userRoleSubject.value;
    }

    // Clear user role
    clearUserRole() {
        this.userRoleSubject.next(null);
        localStorage.removeItem('userRole');
    }

    // Check if role has been selected
    hasSelectedRole(): boolean {
        return this.userRoleSubject.value !== null;
    }
}

