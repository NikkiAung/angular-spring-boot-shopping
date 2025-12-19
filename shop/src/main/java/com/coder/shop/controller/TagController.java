package com.coder.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coder.shop.dtos.Msg;
import com.coder.shop.dtos.TagDto;
import com.coder.shop.models.Tag;
import com.coder.shop.services.ImageService;
import com.coder.shop.services.TagService;

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
@RequestMapping("/admin/tags")
@AllArgsConstructor
public class TagController {

    @Autowired
    private final TagService tagService;

    @Autowired
    private final ImageService imageService;

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addCat(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setName(tagDto.getName());
        tag.setImage(imageService.saveFile(tagDto.getFile()));
        tagService.add(tag);
        return ResponseEntity.ok(new Msg("Tag Save Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("{id}")
    public ResponseEntity<Tag> getSingle(@PathVariable int id) {
        Tag tag = tagService.get(id);
        return ResponseEntity.ok(tag);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("{id}")
    public ResponseEntity<Msg> putCat(@PathVariable int id, TagDto tagDto) {
        Tag tag = new Tag();
        tag.setName(tagDto.getName());
        tag.setImage(imageService.saveFile(tagDto.getFile()));
        tagService.update(id, tag);
        return ResponseEntity.ok(new Msg("Tag Updated",HttpStatus.OK.value()));
    }    

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("{id}")
    public ResponseEntity<Msg> patchCat(@PathVariable int id, TagDto tagDto) {
        Tag tag = new Tag();
        tag.setName(tagDto.getName());
        tag.setImage(imageService.saveFile(tagDto.getFile()));
        tagService.update(id, tag);
        return ResponseEntity.ok(new Msg("Tag Updated",HttpStatus.OK.value()));
    }    

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("name/{id}")
    public ResponseEntity<Msg> patchName(@PathVariable int id, @RequestParam String name) {
        tagService.nameChange(id, name);
        return ResponseEntity.ok(new Msg("Name Change Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PatchMapping("images/{id}")
    public ResponseEntity<Msg> patchName(@PathVariable int id, @RequestPart MultipartFile files) {
        String fileName = imageService.saveFile(files);
        tagService.imageChange(id, fileName);
        return ResponseEntity.ok(new Msg("Images Change Success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping("{id}")
    public ResponseEntity<Msg> dropCat(@PathVariable int id) {
        tagService.drop(id);
        return ResponseEntity.ok(new Msg("Tag Dropped!", HttpStatus.OK.value()));
    }
}
