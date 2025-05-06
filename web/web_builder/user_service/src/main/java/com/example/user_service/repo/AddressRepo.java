package com.example.user_service.repo;

import com.example.user_service.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<AddressEntity,String> {
}
