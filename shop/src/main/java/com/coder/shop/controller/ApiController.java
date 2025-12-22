package com.coder.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.shop.dtos.LoginRequest;
import com.coder.shop.dtos.LoginResponse;
import com.coder.shop.dtos.Msg;
import com.coder.shop.dtos.OrderDto;
import com.coder.shop.dtos.RegisterRequest;
import com.coder.shop.models.AppUser;
import com.coder.shop.models.Category;
import com.coder.shop.models.Childcat;
import com.coder.shop.models.Order;
import com.coder.shop.models.Product;
import com.coder.shop.models.Subcat;
import com.coder.shop.models.Tag;
import com.coder.shop.security.CustomUserDetailsService;
import com.coder.shop.security.JwtUtil;
import com.coder.shop.services.CategoryService;
import com.coder.shop.services.ChildcatService;
import com.coder.shop.services.OrderService;
import com.coder.shop.services.ProductService;
import com.coder.shop.services.SubcatService;
import com.coder.shop.services.TagService;
import com.coder.shop.services.UserService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private final UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

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


    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getName());
            String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getUsername(), "Login Success"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Msg("Invalid username or password", HttpStatus.UNAUTHORIZED.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Msg("Login Fail " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }


    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping("/register")
    public ResponseEntity<?> login(@RequestBody RegisterRequest registerRequest) {
        try {
            AppUser existingUser = userService.findByName(registerRequest.getName());
            if(existingUser != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new Msg("Username already in use!", HttpStatus.CONFLICT.value())); 
            }

            AppUser newUser = new AppUser();
            newUser.setName(registerRequest.getName());
            newUser.setName(registerRequest.getPhone());
            newUser.setName(passwordEncoder.encode(registerRequest.getPassword()));

            userService.register(newUser);

            return ResponseEntity.ok(new Msg("User registration successfuly", HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Msg("Registration Fail " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
}
