package com.example.auth_service.repo;

import com.example.auth_service.entity.FeatureProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureProductRepo extends JpaRepository<FeatureProductEntity,String> {
}
