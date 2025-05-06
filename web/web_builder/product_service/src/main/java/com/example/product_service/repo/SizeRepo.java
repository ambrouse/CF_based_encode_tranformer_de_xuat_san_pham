package com.example.product_service.repo;

import com.example.product_service.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepo extends JpaRepository<SizeEntity,String> {
}
