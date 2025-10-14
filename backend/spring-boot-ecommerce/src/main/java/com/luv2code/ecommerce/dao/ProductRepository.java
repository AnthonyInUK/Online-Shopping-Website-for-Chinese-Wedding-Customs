package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);

    // Query by target user (Groom/Bride/Common)
    Page<Product> findByTargetUser(@RequestParam("targetUser") String targetUser, Pageable pageable);

    // Query by category and target user
    Page<Product> findByCategoryIdAndTargetUser(@RequestParam("id") Long id, @RequestParam("targetUser") String targetUser, Pageable pageable);

    // Query by target user and necessity
    Page<Product> findByTargetUserAndIsRequired(@RequestParam("targetUser") String targetUser, @RequestParam("isRequired") boolean isRequired, Pageable pageable);

    // Query products for target user (including COMMON products)
    Page<Product> findByTargetUserOrTargetUser(@RequestParam("targetUser") String targetUser, @RequestParam("common") String common, Pageable pageable);

}
