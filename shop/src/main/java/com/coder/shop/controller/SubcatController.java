package com.coder.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coder.shop.dtos.Msg;
import com.coder.shop.dtos.SubcatDto;
import com.coder.shop.models.Subcat;
import com.coder.shop.services.ImageService;
import com.coder.shop.services.SubcatService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin/subcats")
@AllArgsConstructor
public class SubcatController {

    @Autowired
    private final SubcatService subcatService;

    @Autowired
    private final ImageService imageService;

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("{id}")
    public ResponseEntity<Subcat> getSingle(@PathVariable("id") int id) {
        Subcat cat = subcatService.get(id);
        return ResponseEntity.ok(cat);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addCat(SubcatDto subcatDto) {
        Subcat subcat = new Subcat();
        subcat.setName(subcatDto.getName());
        subcat.setImage(imageService.saveFile(subcatDto.getFile()));
        subcatService.add(subcat);
        return ResponseEntity.ok(new Msg("Subcat Save Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("{id}")
    public ResponseEntity<Msg> patchCat(@PathVariable int id, SubcatDto subcatDto) {
        Subcat subcat = new Subcat();
        subcat.setName(subcatDto.getName());
        subcat.setImage(imageService.saveFile(subcatDto.getFile()));
        subcatService.update(id, subcat);
        return ResponseEntity.ok(new Msg("Subcat Updated",HttpStatus.OK.value()));
    }    

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("name/{id}")
    public ResponseEntity<Msg> patchName(@PathVariable int id, @RequestParam("name") String name) {
        subcatService.changeName(id, name);
        return ResponseEntity.ok(new Msg("Name Change Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("images/{id}")
    public ResponseEntity<Msg> patchName(@PathVariable int id, @RequestPart MultipartFile files) {
        String fileName = imageService.saveFile(files);
        subcatService.imageChange(id, fileName);
        return ResponseEntity.ok(new Msg("Images Change Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping("{id}")
    public ResponseEntity<Msg> dropCat(@PathVariable int id) {
        subcatService.drop(id);
        return ResponseEntity.ok(new Msg("Category Dropped!", HttpStatus.OK.value()));
    }
}
