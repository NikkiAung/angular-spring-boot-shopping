package com.coder.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.shop.dtos.Msg;
import com.coder.shop.dtos.ProductDto;
import com.coder.shop.models.Product;
import com.coder.shop.services.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin/products")
@AllArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> all() {
        List<Product> products = productService.all();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getSingle(@PathVariable Long id) {
        Product product = productService.get(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Msg> addProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCat());
        product.setSubcat(productDto.getSubCat());
        product.setChildcat(productDto.getChildCat());
        product.setTag(productDto.getTag());
        product.setImages(productDto.getImages());

        productService.add(product);

        return ResponseEntity.ok(new Msg("Product Save Successfully", HttpStatus.OK.value()));
    }

    @PutMapping("{id}")
    public ResponseEntity<Msg> putMethodName(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCat());
        product.setSubcat(productDto.getSubCat());
        product.setChildcat(productDto.getChildCat());
        product.setTag(productDto.getTag());
        product.setImages(productDto.getImages());

        productService.add(product);

        return ResponseEntity.ok(new Msg("Product Update Successfully", HttpStatus.OK.value()));
    }

    @DeleteMapping
    public ResponseEntity<Msg> delete(@PathVariable Long id) {
        productService.drop(id);
        return ResponseEntity.ok(new Msg("Product Delete Successfully", HttpStatus.OK.value()));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> catProducts(@PathVariable int id) {
        List<Product> products = productService.byCatId(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/subcat/{id}")
    public ResponseEntity<List<Product>> subcatProducts(@PathVariable int id) {
        List<Product> products = productService.byCatId(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/childcat/{id}")
    public ResponseEntity<List<Product>> childCatProducts(@PathVariable int id) {
        List<Product> products = productService.byChildcatid(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<List<Product>> tagProducts(@PathVariable int id) {
        List<Product> products = productService.byChildcatid(id);
        return ResponseEntity.ok(products);
    }
}
