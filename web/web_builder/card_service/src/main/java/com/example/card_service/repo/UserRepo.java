package com.example.card_service.repo;

import com.example.card_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,String> {
}
