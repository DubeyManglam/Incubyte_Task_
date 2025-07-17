package com.incubyte.sweetshop.controller;

import com.incubyte.sweetshop.model.Sweet;
import com.incubyte.sweetshop.service.SweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sweetshop/api/sweet")
public class SweetController {

    @Autowired
    private SweetService sweetService;

    @PostMapping("/add")
    public Sweet addSweet(@RequestBody Sweet sweet) {
        return sweetService.addSweet(
                sweet.getName(),
                sweet.getCategory(),
                sweet.getPrice(),
                sweet.getQuantityInStock()
        );
    }

    @GetMapping("/getAllSweets")
    public List<Sweet> getAllSweets() {
        return sweetService.getAllSweets();
    }

    @DeleteMapping("/delete/{id}")
    public Sweet deleteSweet(@PathVariable int id) {
        return sweetService.deleteSweet(id);
    }

    @GetMapping("/search/byName/{name}")
    public List<Sweet> searchByName(@PathVariable String name) {
        return sweetService.searchSweetByName(name);
    }

    @GetMapping("/search/byCategory/{category}")
    public List<Sweet> searchByCategory(@PathVariable String category) {
        return sweetService.searchSweetByCategory(category);
    }

    @GetMapping("/search/by-price")
    public List<Sweet> searchByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return sweetService.searchSweetsByPriceRange(minPrice, maxPrice);
    }

    
}
