package com.coder.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.shop.dtos.Msg;
import com.coder.shop.dtos.OrderDto;
import com.coder.shop.models.Category;
import com.coder.shop.models.Childcat;
import com.coder.shop.models.Order;
import com.coder.shop.models.Product;
import com.coder.shop.models.Subcat;
import com.coder.shop.models.Tag;
import com.coder.shop.services.CategoryService;
import com.coder.shop.services.ChildcatService;
import com.coder.shop.services.OrderService;
import com.coder.shop.services.ProductService;
import com.coder.shop.services.SubcatService;
import com.coder.shop.services.TagService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

    @Autowired
    private final CategoryService catService;
    @Autowired
    private final SubcatService subcatService;
    @Autowired
    private final ChildcatService childcatService;
    @Autowired
    private final TagService tagService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final OrderService orderService;

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/cats")
    public ResponseEntity<List<Category>> getCats() {
        List<Category> cats = catService.all();
        return ResponseEntity.ok(cats);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/subcats")
    public ResponseEntity<List<Subcat>> getSubCats() {
        List<Subcat> subcats = subcatService.all();
        return ResponseEntity.ok(subcats);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/childcats")
    public ResponseEntity<List<Childcat>> getAllChildCats() {
        List<Childcat> childcats = childcatService.all();
        return ResponseEntity.ok(childcats);
    }
    
    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = tagService.all();
        return ResponseEntity.ok(tags);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.all();
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping("/orders")
    public ResponseEntity<Msg> addOrder(@RequestBody OrderDto orderDto) {
        Order order = new Order();
        order.setTotal(orderDto.getTotal());
        order.setOrderItem(orderDto.getOrderItems());
        orderService.add(order);
        return ResponseEntity.ok(new Msg("Order Add Successfully", HttpStatus.OK.value()));
    }
}
