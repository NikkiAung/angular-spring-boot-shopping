package com.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.shop.models.Tag;
import com.coder.shop.repos.TagRepo;
import com.coder.shop.services.TagService;
import com.coder.shop.exceptions.TagNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TagImpl implements TagService{

    @Autowired
    private final TagRepo tagRepo;

    @Override
    public void add(Tag tag) {
        tagRepo.save(tag);
    }

    @Override
    public List<Tag> all() {
        return tagRepo.findAll();
    }

    @Override
    public Tag get(int id) {
        return tagRepo.findById(id).orElseThrow(()-> new TagNotFoundException("No tag with that id"));
    }

    @Override
    public void update(int id, Tag tag) {
        Tag dbTag = get(id);
        if(dbTag != null) {
            dbTag.setName(tag.getName());
            dbTag.setImage(tag.getImage());
            tagRepo.save(dbTag);
        }
    }

    @Override
    public void drop(int id) {
        Tag dbTag = get(id);
        if(dbTag != null) {
            tagRepo.deleteById(id);
        }
    }

    @Override
    public void nameChange(int id, String name) {
        Tag dbTag = get(id);
        if(dbTag != null) {
            dbTag.setName(name);
            tagRepo.save(dbTag);
        }
    }

    @Override
    public void imageChange(int id, String image) {
        Tag dbTag = get(id);
        if(dbTag != null) {
            dbTag.setImage(image);
            tagRepo.save(dbTag);
        }
    }

}
