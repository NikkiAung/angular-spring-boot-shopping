package com.coder.shop.dtos;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class ProductDto {

    private String name;
    private List<MultipartFile> file;
    private double price;
    private int catId;
    private int subcatId;
    private int childcatId;
    private int tagId;

}
