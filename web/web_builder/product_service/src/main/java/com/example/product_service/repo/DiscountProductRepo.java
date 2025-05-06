package com.example.product_service.repo;

import com.example.product_service.entity.DiscountProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountProductRepo extends JpaRepository<DiscountProductEntity,String> {
}
