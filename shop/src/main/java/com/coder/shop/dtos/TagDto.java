package com.coder.shop.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TagDto {
    private String name;
    private MultipartFile file;
}
