package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SweetServiceTest {

    //dependency injection in spring boot
    @Autowired
    private SweetService sweetService;

    @BeforeEach
    void addSomeDefaultDataOfSweets(){
        sweetService.clearAll(); //To clear the list before every test
        sweetService.addSweet("Kaju Katli", "Nut-Based", 50, 20);
        sweetService.addSweet("Gajar Halwa", "Vegetable-Based", 30, 15);
        sweetService.addSweet("Gulab Jamun", "Milk-Based", 10, 50);
    }

//    for adding sweet into list
    @Test
    void shouldAddSweetSuccessfully(){
            Sweet sweetAdded = sweetService.addSweet("Rabdi", "Milk-Based", 40, 10);
            List<Sweet> sweetList = sweetService.getAllSweets();
            assertEquals(4,sweetList.size());
            assertEquals("Rabdi", sweetList.get(3).getName());
    }

    //for getting all sweets list
    @Test
    void getAllSweetsShouldReturnSize1andItemNameCorrectly(){
        List<Sweet> getSweetsList = sweetService.getAllSweets();
        assertEquals(3,getSweetsList.size());
        assertEquals("Kaju Katli", getSweetsList.get(0).getName());
    }

}
