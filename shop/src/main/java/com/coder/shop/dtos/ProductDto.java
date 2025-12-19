package com.coder.shop.dtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.coder.shop.models.Category;
import com.coder.shop.models.Childcat;
import com.coder.shop.models.Subcat;
import com.coder.shop.models.Tag;
import com.coder.shop.services.CategoryService;
import com.coder.shop.services.ChildcatService;
import com.coder.shop.services.ImageService;
import com.coder.shop.services.SubcatService;
import com.coder.shop.services.TagService;

import lombok.Data;

@Data
public class ProductDto {

    @Autowired
    private final CategoryService catService;

    @Autowired
    private final SubcatService subcatService;

    @Autowired
    private final ChildcatService childcatService;

    @Autowired
    private final TagService tagService;

    @Autowired
    private final ImageService imageService;

    private String name;
    private List<MultipartFile> file;
    private double price;
    private int catId;
    private int subcatId;
    private int childcatId;
    private int tagId;

    public Category getCat() {
        return catService.get(catId);
    }

    public Subcat getSubCat() {
        return subcatService.get(subcatId);
    }

    public Childcat getChildCat() {
        return childcatService.get(childcatId);
    }

    public Tag getTag() {
        return tagService.get(tagId);
    }

    public List<String> getImages() {
        return imageService.saveFile(file);
    }
}
