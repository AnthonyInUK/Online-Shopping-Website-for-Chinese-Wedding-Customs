import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/common/product';
import { ActivatedRoute, Router } from '@angular/router';
import { timeoutWith } from 'rxjs/operators';
import { CartItem } from 'src/app/common/cart-item';
import { CartService } from 'src/app/services/cart.service';
import { UserRoleService } from 'src/app/services/user-role.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];
  allProducts: Product[] = [];  // Store all products
  currentCategoryId: number = 1;
  previousCategoryId: number = 1;
  searchMode: boolean = false;
  currentUserRole: string = null;  // Current user role

  // new properties for pagination
  thePageNumber: number = 1;
  thePageSize: number = 5;
  theTotalElements: number = 0;

  previousKeyword: string = null;

  constructor(private productService: ProductService,
    private cartService: CartService,
    private userRoleService: UserRoleService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    // Subscribe to user role changes
    this.userRoleService.userRole$.subscribe(role => {
      this.currentUserRole = role;

      if (role) {
        // When role changes, navigate to the first category for that role
        this.navigateToFirstCategoryForRole(role);
      } else {
        // If no role selected, show all products
        this.listProducts();
      }
    });

    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }

  // Navigate to first category based on role
  navigateToFirstCategoryForRole(role: string) {
    let firstCategoryId: number;

    if (role === 'GROOM') {
      firstCategoryId = 2; // Groom Attire
    } else if (role === 'BRIDE') {
      firstCategoryId = 3; // Bridal Wear
    } else {
      firstCategoryId = 1; // Default category
    }

    console.log('Navigating to category:', firstCategoryId, 'for role:', role);

    // Navigate to the product page for this category
    console.log('Navigating to URL:', `/category/${firstCategoryId}`);
    this.router.navigateByUrl(`/category/${firstCategoryId}`).then(() => {
      console.log('Navigation completed, loading products...');
      // Reload product data after navigation
      this.listProducts();
    });
  }

  listProducts() {
    console.log('listProducts called, current role:', this.currentUserRole);

    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if (this.searchMode) {
      this.handleSearchProducts();
    }
    else {
      this.handleListProducts();
    }

  }

  handleSearchProducts() {

    const theKeyword: string = this.route.snapshot.paramMap.get('keyword');

    // if we have a different keyword than previous
    // then set thePageNumber to 1

    if (this.previousKeyword != theKeyword) {
      this.thePageNumber = 1;
    }

    this.previousKeyword = theKeyword;

    console.log(`keyword=${theKeyword}, thePageNumber=${this.thePageNumber}`);

    // now search for the products using keyword
    this.productService.searchProductsPaginate(this.thePageNumber - 1,
      this.thePageSize,
      theKeyword).subscribe(this.processResult());

  }

  handleListProducts() {
    console.log('handleListProducts called');
    console.log('Current URL:', window.location.href);

    // check if "id" parameter is available
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if (hasCategoryId) {
      // get the "id" param string. convert string to a number using the "+" symbol
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id');
      console.log('Category ID from route:', this.currentCategoryId);
    }
    else {
      // not category id available ... default to category id 1
      this.currentCategoryId = 1;
      console.log('No category ID, using default:', this.currentCategoryId);
    }

    //
    // Check if we have a different category than previous
    // Note: Angular will reuse a component if it is currently being viewed
    //

    // if we have a different category id than previous
    // then set thePageNumber back to 1
    if (this.previousCategoryId != this.currentCategoryId) {
      this.thePageNumber = 1;
    }

    this.previousCategoryId = this.currentCategoryId;

    console.log(`currentCategoryId=${this.currentCategoryId}, thePageNumber=${this.thePageNumber}`);

    // now get the products for the given category id
    this.productService.getProductListPaginate(this.thePageNumber - 1,
      this.thePageSize,
      this.currentCategoryId)
      .subscribe(this.processResult());
  }

  processResult() {
    return data => {
      this.allProducts = data._embedded.products;
      // Apply role filtering
      this.filterProductsByRole();
      this.thePageNumber = data.page.number + 1;
      this.thePageSize = data.page.size;
      this.theTotalElements = data.page.totalElements;
    };
  }

  // Filter products based on user role
  filterProductsByRole() {
    console.log('Filtering products for role:', this.currentUserRole);
    console.log('All products before filtering:', this.allProducts.map(p => ({ name: p.name, targetUser: p.targetUser })));

    if (!this.currentUserRole) {
      // If no role selected, show all products
      this.products = this.allProducts;
    } else {
      // Show current role products and common products
      this.products = this.allProducts.filter(product =>
        product.targetUser === this.currentUserRole || product.targetUser === 'COMMON'
      );
    }

    console.log('Filtered products count:', this.products.length);
    console.log('Current role:', this.currentUserRole);
    console.log('All products count:', this.allProducts.length);
    console.log('Filtered products:', this.products.map(p => ({ name: p.name, targetUser: p.targetUser })));
  }

  updatePageSize(pageSize: number) {
    this.thePageSize = pageSize;
    this.thePageNumber = 1;
    this.listProducts();
  }

  addToCart(theProduct: Product) {

    console.log(`Adding to cart: ${theProduct.name}, ${theProduct.unitPrice}`);

    // TODO ... do the real work
    const theCartItem = new CartItem(theProduct);

    this.cartService.addToCart(theCartItem);
  }

}
