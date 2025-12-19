package com.coder.shop.dtos;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ChildcatDto {
    private String name;
    private MultipartFile file;
    private int subcatId;
}
