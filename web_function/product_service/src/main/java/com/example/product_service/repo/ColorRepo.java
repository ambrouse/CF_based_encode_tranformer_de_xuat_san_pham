package com.example.product_service.repo;

import com.example.product_service.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepo extends JpaRepository<ColorEntity,String> {
}
