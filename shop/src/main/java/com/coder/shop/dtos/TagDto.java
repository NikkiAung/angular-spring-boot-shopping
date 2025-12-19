package com.coder.shop.dtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.coder.shop.services.ImageService;

import lombok.Data;

@Data
public class TagDto {
    @Autowired
    private final ImageService imageService;
    private String name;
    private MultipartFile file;
    public String saveAndGetFileName() {
        String fileName = imageService.saveFile(file);
        return fileName;
    }
}
