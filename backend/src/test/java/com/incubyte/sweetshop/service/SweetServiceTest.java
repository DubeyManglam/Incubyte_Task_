package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SweetServiceTest {

    //dependency injection in spring boot
    @Autowired
    private SweetService sweetService;

    @Test
    void shouldAddSweetSuccessfully(){
            Sweet added = sweetService.addSweet("Kaju Katli", "Nut-Based", 50, 20);
            assertEquals("Kaju Katli", added.getName());
    }
}
