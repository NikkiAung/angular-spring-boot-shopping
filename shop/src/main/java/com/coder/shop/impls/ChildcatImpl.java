package com.coder.shop.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coder.shop.exceptions.ChildcatNotFoundException;
import com.coder.shop.models.Childcat;
import com.coder.shop.repos.ChildcatRepo;
import com.coder.shop.services.ChildcatService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChildcatImpl implements ChildcatService {
    private final ChildcatRepo childcatRepo;

    @Override
    public void add(Childcat childcat) { 
        childcatRepo.save(childcat);
    }

    @Override
    public List<Childcat> all() {
        return childcatRepo.findAll();
    }

    @Override
    public Childcat get(int id) {
        return childcatRepo.findById(id)
                .orElseThrow(() -> new ChildcatNotFoundException("No childcat with that id"));
    }

    @Override
    public void update(int id, Childcat childcat) {
        Childcat db = getRequired(id);
        if (childcat.getName() != null) {
            db.setName(childcat.getName());
        }
        if (childcat.getImage() != null) {
            db.setImage(childcat.getImage());
        }
        if (childcat.getSubcat() != null) {
            db.setSubcat(childcat.getSubcat());
        }
        childcatRepo.save(db);
    }

    @Override
    public void nameChange(int id, String name) {
        Childcat db = getRequired(id);
        db.setName(name);
        childcatRepo.save(db);
    }

    @Override
    public void imageChange(int id, String image) {
        Childcat db = getRequired(id);
        db.setImage(image);
        childcatRepo.save(db);
    }

    @Override
    public void drop(int id) {
        Childcat db = getRequired(id);
        childcatRepo.delete(db);
    }

    private Childcat getRequired(int id) {
        return childcatRepo.findById(id)
                .orElseThrow(() -> new ChildcatNotFoundException("No childcat with that id"));
    }
}
