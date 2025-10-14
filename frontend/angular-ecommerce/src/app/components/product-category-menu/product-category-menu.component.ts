import { Component, OnInit } from '@angular/core';
import { ProductCategory } from 'src/app/common/product-category';
import { ProductService } from 'src/app/services/product.service';
import { UserRoleService } from 'src/app/services/user-role.service';

@Component({
  selector: 'app-product-category-menu',
  templateUrl: './product-category-menu.component.html',
  styleUrls: ['./product-category-menu.component.css']
})
export class ProductCategoryMenuComponent implements OnInit {

  productCategories: ProductCategory[];
  allCategories: ProductCategory[] = [];
  currentUserRole: string = null;
  
  // Role-based category mapping - completely separated, no common categories
  private roleCategoryMapping = {
    'GROOM': [2, 4, 6], // Groom Attire + Wedding Jewelry + Wedding Decorations
    'BRIDE': [3, 5, 7, 8, 9] // Bridal Wear + Wedding Shoes + Wedding Candy + Red Envelopes + Wedding Supplies
  };
  
  constructor(private productService: ProductService, private userRoleService: UserRoleService) { }

  ngOnInit() {
    // Subscribe to user role changes
    this.userRoleService.userRole$.subscribe(role => {
      this.currentUserRole = role;
      this.filterCategoriesByRole();
    });

    this.listProductCategories();
  }

  listProductCategories() {
    this.productService.getProductCategories().subscribe(
      data => {
        console.log('Product Categories=' + JSON.stringify(data));
        this.allCategories = data;
        this.filterCategoriesByRole();
      }
    );
  }

  // Filter categories based on user role
  filterCategoriesByRole() {
    if (!this.currentUserRole || !this.allCategories) {
      // If no role selected, show all categories
      this.productCategories = this.allCategories || [];
    } else {
      // Filter categories based on role mapping
      const allowedCategoryIds = this.roleCategoryMapping[this.currentUserRole] || [];
      this.productCategories = this.allCategories.filter(category => 
        allowedCategoryIds.includes(category.id)
      );
    }
  }

}
