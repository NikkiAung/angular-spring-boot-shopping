package com.coder.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coder.shop.models.AppUser;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long>{
    public AppUser findByName(String name);
}
