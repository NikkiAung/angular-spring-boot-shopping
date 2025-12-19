package com.coder.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coder.shop.models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
