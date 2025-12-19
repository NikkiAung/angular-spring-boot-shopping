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

import com.coder.shop.dtos.ChildcatDto;
import com.coder.shop.dtos.Msg;
import com.coder.shop.models.Childcat;
import com.coder.shop.services.ChildcatService;
import com.coder.shop.services.ImageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin/childcats")
@AllArgsConstructor
public class ChildcatController {

    @Autowired
    private final ChildcatService childcatService;

    @Autowired
    private final ImageService imageService;

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("{id}")
    public ResponseEntity<Childcat> getSingle(@PathVariable("id") int id) {
        Childcat cat = childcatService.get(id);
        return ResponseEntity.ok(cat);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addCat(ChildcatDto childcatDto) {
        Childcat childcat = new Childcat();
        childcat.setName(childcatDto.getName());
        childcat.setImage(imageService.saveFile(childcatDto.getFile()));
        childcatService.add(childcat);
        return ResponseEntity.ok(new Msg("Childcat Save Success", HttpStatus.OK.value()));
    }
    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("{id}")
    public ResponseEntity<Msg> patchCat(@PathVariable int id,ChildcatDto childcatDto) {
        Childcat childcat = new Childcat();
        childcat.setName(childcatDto.getName());
        childcat.setImage(imageService.saveFile(childcatDto.getFile()));
        childcatService.update(id, childcat);
        return ResponseEntity.ok(new Msg("Childcat Updated",HttpStatus.OK.value()));
    }    

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("name/{id}")
    public ResponseEntity<Msg> patchName(@PathVariable int id, @RequestParam String name) {
        childcatService.nameChange(id, name);
        return ResponseEntity.ok(new Msg("Name Change Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("images/{id}")
    public ResponseEntity<Msg> patchName(@PathVariable int id, @RequestPart MultipartFile files) {
        String fileName = imageService.saveFile(files);
        childcatService.imageChange(id, fileName);
        return ResponseEntity.ok(new Msg("Images Change Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping("{id}")
    public ResponseEntity<Msg> dropCat(@PathVariable int id) {
        childcatService.drop(id);
        return ResponseEntity.ok(new Msg("Childcat Dropped!", HttpStatus.OK.value()));
    }
}