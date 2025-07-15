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
        Sweet sweet = new Sweet(idCounter++, name, category, price, quantity);
        sweets.add(sweet);
        return sweet;
    }

    public List<Sweet> getAllSweets() {
        return new ArrayList<>(sweets);
    }
}
