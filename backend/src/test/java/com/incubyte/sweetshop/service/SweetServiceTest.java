package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.model.Sweet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SweetServiceTest {

    //dependency injection in spring boot
    @Autowired
    private SweetService sweetService;

    @Test
    void shouldAddSweetSuccessfully(){
            Sweet sweet = new Sweet(1001, "Kaju Katli", "Nut-Based", 50, 20);
            Sweet added = sweetService.addSweet(sweet);
            assertEquals("Kaju Katli", added.getName());
    }
}
