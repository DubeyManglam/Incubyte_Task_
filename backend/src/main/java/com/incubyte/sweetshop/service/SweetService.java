package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.customizedExceptions.SweetAlreadyExistsException;
import com.incubyte.sweetshop.customizedExceptions.SweetNotFoundException;
import com.incubyte.sweetshop.model.Sweet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SweetService {
    private List<Sweet> sweets = new ArrayList<>();
    private long idCounter = 1000;

    public Sweet addSweet(String name, String category, double price, int quantity) {
        for (Sweet sweet : sweets) {
            if (sweet.getName().equalsIgnoreCase(name)) {
                throw new SweetAlreadyExistsException("Sweet of this name already exists");
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

    //Search by name functionality and if sweet is not found then throws exception

    public List<Sweet> searchSweetByName(String name) {
        List<Sweet> result = sweets.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new SweetNotFoundException("No sweet found with the name: " + name);
        }
        return result;
    }

    //search sweets by category
    public List<Sweet> searchSweetByCategory(String category) {
        return sweets.stream()
                .filter(s -> s.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

}
