package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.customExceptions.InsufficientStockException;
import com.incubyte.sweetshop.customExceptions.SweetAlreadyExistsException;
import com.incubyte.sweetshop.customExceptions.SweetNotFoundException;
import com.incubyte.sweetshop.customExceptions.SweetAlreadyExistsException;
import com.incubyte.sweetshop.customExceptions.SweetNotFoundException;
import com.incubyte.sweetshop.model.Sweet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SweetService {
    private List<Sweet> sweets = new ArrayList<>();
    private long idCounter = 1000;

    //                        -------------Add Delete view sweets--------------
    //Add sweet
    public Sweet addSweet(String name, String category, double price, int quantity) {
        for (Sweet sweet : sweets) {
            if (sweet.getName().equalsIgnoreCase(name)) {
                throw new SweetAlreadyExistsException("Sweet of this name already exists");
            }
        }
        Sweet sweet = new Sweet(idCounter++, name, category, price, quantity);
        sweets.add(sweet);
        System.out.println("Sweet added successfully: " + sweet);
        return sweet;
    }

    //for viewing all sweets
    public List<Sweet> getAllSweets() {
        return new ArrayList<>(sweets);
    }

    public void clearAll() {
        sweets.clear();   // clear the list
        idCounter = 1000; // reset ID
    }

    //delete sweets with id and throw exception if sweet doesn't exist
    public Sweet deleteSweet(int id) {
        Iterator<Sweet> iterator = sweets.iterator();
        while (iterator.hasNext()) {
            Sweet sweet = iterator.next();
            if (sweet.getId() == id) {
                iterator.remove();
                return sweet;
            }
        }
        throw new SweetNotFoundException("Sweet with ID "+ id + " not found");
    }

    //delete sweets with name and throw exception if sweet doesn't exist
    public Sweet deleteSweetByName(String name) {
        Iterator<Sweet> iterator = sweets.iterator();
        while (iterator.hasNext()) {
            Sweet sweet = iterator.next();
            if (sweet.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                return sweet;
            }
        }
        throw new SweetNotFoundException("Sweet with name '" + name + "' not found");
    }

    //                        -------------Search and Sort--------------
    //Search by name functionality and if sweet is not found then throws exception
    public List<Sweet> searchSweetByName(String name) {
        List<Sweet> result = sweets.stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new SweetNotFoundException("No sweet found with the name: " + name);
        }
        return result;
    }

    //search sweets by category
    public List<Sweet> searchSweetByCategory(String category) {
        return sweets.stream()
                .filter(s -> s.getCategory().toLowerCase().contains(category.toLowerCase()))
                .collect(Collectors.toList());
    }

    //search by price range(minimum and maximum prices)
    public List<Sweet> searchSweetsByPriceRange(Double minPrice, Double maxPrice) {
        return sweets.stream()
                .filter(s -> s.getPrice() >= minPrice && s.getPrice() <= maxPrice)
                .toList();
    }

    //sort by price in descending order
    public List<Sweet> sortSweetsByPriceDescending() {
        return sweets.stream()
                .sorted(Comparator.comparingDouble(Sweet::getPrice).reversed())
                .toList();
    }

    //sort by price in ascending order
    public List<Sweet> sortSweetsByPriceAscending() {
        return sweets.stream()
                .sorted(Comparator.comparingDouble(Sweet::getPrice))
                .toList();
    }

    //sort by quantity
    public List<Sweet> sortSweetsByQuantityAscending() {
        return sweets.stream()
                .sorted(Comparator.comparingInt(Sweet::getQuantityInStock))
                .toList();
    }

    public List<Sweet> sortSweetsByQuantityDescending() {
        return sweets.stream()
                .sorted(Comparator.comparingInt(Sweet::getQuantityInStock).reversed())
                .toList();
    }

    //                        -------------Inventory Management--------------
    //purchase sweet with given quantity
    public Sweet purchaseSweet(long id, int quantity) {
        for (Sweet sweet : sweets) {
            if (sweet.getId() == id) {
                if (sweet.getQuantityInStock() >= quantity) {
                    sweet.setQuantityInStock(sweet.getQuantityInStock() - quantity);
                    System.out.println("Successfully purchased " + quantity + " of " + sweet.getName());
                    return sweet;
                }else{
                    throw new InsufficientStockException("Not enough stock for sweet ID: "+ id);
                }
            }
        }
        throw new SweetNotFoundException("Sweet with ID " + id + " not found");
    }

    //restock sweet
    public Sweet restockSweet(long id, int quantity) {
        for (Sweet sweet : sweets) {
            if (sweet.getId() == id) {
                sweet.setQuantityInStock(sweet.getQuantityInStock() + quantity);
                System.out.println("Restocked " + quantity + " units of '" + sweet.getName() +
                        "'. New stock: " + sweet.getQuantityInStock());
                return sweet;
            }
        }
        throw new SweetNotFoundException("Sweet with ID " + id + " not found");
    }
}
