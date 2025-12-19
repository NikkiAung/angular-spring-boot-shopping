package com.coder.shop.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import com.coder.shop.exceptions.SubcatNotFoundException;
import com.coder.shop.models.Subcat;
import com.coder.shop.repos.SubcatRepo;
import com.coder.shop.services.SubcatService;

@Service
@AllArgsConstructor
public class SubcatImpl implements SubcatService{

    private final SubcatRepo subcatRepo;

    @Override
    public void add(Subcat subcat) {
        subcatRepo.save(subcat);
    }

    @Override
    public List<Subcat> all() {
        return subcatRepo.findAll();
    }

    @Override
    public void update(int id, Subcat subcat) {
        Subcat db = get(id);
        if (subcat.getName() != null) {
            db.setName(subcat.getName());
        }
        if (subcat.getImage() != null) {
            db.setImage(subcat.getImage());
        }
        if (subcat.getChildcat() != null) {
            db.setChildcat(subcat.getChildcat());
        }
        subcatRepo.save(db);
    }

    @Override
    public void changeName(int id, String name) {
        Subcat db = get(id);
        db.setName(name);
        subcatRepo.save(db);
    }

    @Override
    public void imageChange(int id, String image) {
        Subcat db = get(id);
        db.setImage(image);
        subcatRepo.save(db);
    }

    @Override
    public void drop(int id) {
        Subcat db = get(id);
        subcatRepo.delete(db);
    }

    @Override
    public Subcat get(int id) {
        return subcatRepo.findById(id)
                .orElseThrow(() -> new SubcatNotFoundException("No subcat With that id"));
    }
    
}
