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

    //add sweet endpoint
    @PostMapping("/add")
    public Sweet addSweet(@RequestBody Sweet sweet) {
        return sweetService.addSweet(
                sweet.getName(),
                sweet.getCategory(),
                sweet.getPrice(),
                sweet.getQuantityInStock()
        );
    }

    //get all sweets endpoint
    @GetMapping("/getAllSweets")
    public List<Sweet> getAllSweets() {
        return sweetService.getAllSweets();
    }

    //delete sweet endpoint
    @DeleteMapping("/delete/{id}")
    public Sweet deleteSweet(@PathVariable int id) {
        return sweetService.deleteSweet(id);
    }

    //search by name endpoint
    @GetMapping("/search/byName/{name}")
    public List<Sweet> searchByName(@PathVariable String name) {
        return sweetService.searchSweetByName(name);
    }

    //search by category endpoint
    @GetMapping("/search/byCategory/{category}")
    public List<Sweet> searchByCategory(@PathVariable String category) {
        return sweetService.searchSweetByCategory(category);
    }

    //search by price(min,max) endpoint
    @GetMapping("/search/by-price")
    public List<Sweet> searchByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return sweetService.searchSweetsByPriceRange(minPrice, maxPrice);
    }

    //sort by price (ascending)
    @GetMapping("/sort/price-asc")
    public List<Sweet> sortByPriceAsc() {
        return sweetService.sortSweetsByPriceAscending();
    }

    //sort by price (descending)
    @GetMapping("/sort/price-desc")
    public List<Sweet> sortByPriceDesc() {
        return sweetService.sortSweetsByPriceDescending();
    }

    //sort by quantity (ascending)
    @GetMapping("/sort/quantity-asc")
    public List<Sweet> sortByQuantityAsc() {
        return sweetService.sortSweetsByQuantityAscending();
    }

    //sort by quantity (descending)
    @GetMapping("/sort/quantity-desc")
    public List<Sweet> sortByQuantityDesc() {
        return sweetService.sortSweetsByQuantityDescending();
    }

    //purchase sweet
    @PostMapping("/purchase/{id}")
    public Sweet purchaseSweet(@PathVariable long id, @RequestParam int quantity) {
        return sweetService.purchaseSweet(id, quantity);
    }

    //restock sweet
    @PostMapping("/restock/{id}")
    public Sweet restockSweet(@PathVariable long id, @RequestParam int quantity) {
        return sweetService.restockSweet(id, quantity);
    }
}
