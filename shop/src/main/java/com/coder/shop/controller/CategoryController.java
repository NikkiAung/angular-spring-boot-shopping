package com.coder.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coder.shop.dtos.CategoryDto;
import com.coder.shop.dtos.Msg;
import com.coder.shop.models.Category;
import com.coder.shop.services.CategoryService;
import com.coder.shop.services.ImageService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/admin/cats")
@AllArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryService catService;

    @Autowired
    private final ImageService imageService;

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addCat(CategoryDto catDto) {
        Category category = new Category();
        category.setName(catDto.getName());
        category.setImage(imageService.saveFile(catDto.getFile()));
        catService.add(category);
        return ResponseEntity.ok(new Msg("Category Save Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("{id}")
    public ResponseEntity<Category> getSingle(@PathVariable int id) {
        Category cat = catService.get(id);
        return ResponseEntity.ok(cat);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("{id}")
    public ResponseEntity<Msg> patchCat(@PathVariable int id, CategoryDto catDto) {
        
        Category existingCategory = catService.get(id);
        Category category = new Category();
        category.setName(catDto.getName());
        
        // Only update image if a new file is provided
        if (catDto.getFile() != null && !catDto.getFile().isEmpty()) {
            category.setImage(imageService.saveFile(catDto.getFile()));
        } else {
            category.setImage(existingCategory.getImage());
        }
        
        catService.patch(id, category);
        return ResponseEntity.ok(new Msg("Category Updated",HttpStatus.OK.value()));
    }    

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("name/{id}")
    public ResponseEntity<Msg> patchName(@PathVariable int id, @RequestParam("name") String name) {
        catService.changeName(id, name);
        return ResponseEntity.ok(new Msg("Name Change Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("images/{id}")
    public ResponseEntity<Msg> patchName(@PathVariable int id, @RequestPart MultipartFile files) {
        String fileName = imageService.saveFile(files);
        catService.changeImage(id, fileName);
        return ResponseEntity.ok(new Msg("Images Change Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping("{id}")
    public ResponseEntity<Msg> dropCat(@PathVariable int id) {
        catService.drop(id);
        return ResponseEntity.ok(new Msg("Category Dropped!", HttpStatus.OK.value()));
    }
}
