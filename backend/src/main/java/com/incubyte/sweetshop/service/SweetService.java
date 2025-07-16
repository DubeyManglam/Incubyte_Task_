package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SweetService {
    private List<Sweet> sweets = new ArrayList<>();
    private long idCounter = 1000;

    public Sweet addSweet(String name, String category, double price, int quantity) {
        for (Sweet sweet : sweets) {
            if (sweet.getName().equalsIgnoreCase(name)) {
                throw new IllegalArgumentException("Sweet with this name already exists.");
            }
        }

        Sweet sweet = new Sweet(idCounter++, name, category, price, quantity);
        sweets.add(sweet);
        return sweet;
    }

    public List<Sweet> getAllSweets() {
        return new ArrayList<>(sweets);
    }

    public void clearAll() {
        sweets.clear();   // clear the list
        idCounter = 1000; // reset ID
    }
}
