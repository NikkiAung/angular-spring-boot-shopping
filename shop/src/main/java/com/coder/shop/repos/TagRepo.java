package com.coder.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coder.shop.models.Tag;

@Repository
public interface TagRepo extends JpaRepository<Tag,Integer>{
    
}
