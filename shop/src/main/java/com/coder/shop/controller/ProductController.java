package com.coder.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.shop.dtos.Msg;
import com.coder.shop.dtos.ProductDto;
import com.coder.shop.models.Product;
import com.coder.shop.services.CategoryService;
import com.coder.shop.services.ChildcatService;
import com.coder.shop.services.ImageService;
import com.coder.shop.services.ProductService;
import com.coder.shop.services.SubcatService;
import com.coder.shop.services.TagService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin/products")
@AllArgsConstructor
public class ProductController {
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

    @Autowired
    private final ProductService productService;

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping
    public ResponseEntity<List<Product>> all() {
        List<Product> products = productService.all();
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("{id}")
    public ResponseEntity<Product> getSingle(@PathVariable Long id) {
        Product product = productService.get(id);
        return ResponseEntity.ok(product);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(catService.get(productDto.getCatId()));
        product.setSubcat(subcatService.get(productDto.getSubcatId()));
        product.setChildcat(childcatService.get(productDto.getChildcatId()));
        product.setTag(tagService.get(productDto.getTagId()));
        product.setImages(imageService.saveFile(productDto.getFile()));

        productService.add(product);

        return ResponseEntity.ok(new Msg("Product Save Successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("{id}")
    public ResponseEntity<Msg> putMethodName(@PathVariable Long id, ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(catService.get(productDto.getCatId()));
        product.setSubcat(subcatService.get(productDto.getSubcatId()));
        product.setChildcat(childcatService.get(productDto.getChildcatId()));
        product.setTag(tagService.get(productDto.getTagId()));
        product.setImages(imageService.saveFile(productDto.getFile()));

        productService.add(product);

        return ResponseEntity.ok(new Msg("Product Update Successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping
    public ResponseEntity<Msg> delete(@PathVariable Long id) {
        productService.drop(id);
        return ResponseEntity.ok(new Msg("Product Delete Successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> catProducts(@PathVariable int id) {
        List<Product> products = productService.byCatId(id);
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/subcat/{id}")
    public ResponseEntity<List<Product>> subcatProducts(@PathVariable int id) {
        List<Product> products = productService.byCatId(id);
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/childcat/{id}")
    public ResponseEntity<List<Product>> childCatProducts(@PathVariable int id) {
        List<Product> products = productService.byChildcatid(id);
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/tag/{id}")
    public ResponseEntity<List<Product>> tagProducts(@PathVariable int id) {
        List<Product> products = productService.byChildcatid(id);
        return ResponseEntity.ok(products);
    }
}
