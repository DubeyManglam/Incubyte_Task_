package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.customizedExceptions.SweetAlreadyExistsException;
import com.incubyte.sweetshop.customizedExceptions.SweetNotFoundException;
import com.incubyte.sweetshop.model.Sweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SweetServiceTest {

//                              --------------ADD Sweets-------------

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
            assertEquals("Rabdi", sweetList.get(3).getName());
    }

    //for getting all sweets list
    @Test
    void getAllSweetsShouldReturnSize3andItemNameCorrectly(){
        List<Sweet> sweetList = sweetService.getAllSweets();
        assertEquals(3,sweetList.size());
        assertEquals("Kaju Katli", sweetList.get(0).getName());
    }

    //for getting exception on adding duplicate sweets
    @Test
    void shouldThrowExceptionOnAddingDuplicateSweets(){
        assertThrows(SweetAlreadyExistsException.class,()->sweetService.addSweet("Kaju Katli", "Nut-Based", 50, 20));
    }


    //                    --------------DELETE Sweets--------------

    @Test
    void shouldDeleteSweetSuccessfully() {
        Sweet deletedSweet = sweetService.deleteSweet(1000);  // assuming Kaju Katli has id 1000
        List<Sweet> sweetsList = sweetService.getAllSweets();
        assertEquals(2, sweetsList.size());  // 3 originally, now 2 after delete
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingSweet() {
        assertThrows(SweetNotFoundException.class, () -> sweetService.deleteSweet(999));
    }

    //                                   -----------Search By Name---------------
    @Test
    void shouldReturnSweetWhenSearchedByName() {
        List<Sweet> result = sweetService.searchSweetByName("Kaju Katli");
        assertEquals(1, result.size());
        assertEquals("Kaju Katli", result.get(0).getName());
    }

    @Test
    void shouldReturnSweetWhenSearchedByCaseInsensitiveNames() {
        List<Sweet> result = sweetService.searchSweetByName("gajar halwa");
        assertEquals(1, result.size());
        assertEquals("Gajar Halwa", result.get(0).getName());
    }

    @Test
    void shouldThrowExceptionIfSerchedSweetIsNotFound(){
        assertThrows(SweetNotFoundException.class,()->sweetService.searchSweetByName("rasgulla"));
    }

    @Test
    void shouldReturnSweetFromGivenCategory() {
        List<Sweet> sweets = sweetService.searchSweetByCategory("Milk-Based");
        assertEquals(1, sweets.size());
        assertEquals("Gulab Jamun", sweets.get(0).getName());
    }

    @Test
    void shouldReturnAllSweetsFromGivenCategory() {
        sweetService.addSweet("Barfi", "Milk-Based", 20, 40);
        List<Sweet> sweets = sweetService.searchSweetByCategory("Milk-Based");
        assertEquals(2, sweets.size());
        assertEquals("Gulab Jamun", sweets.get(0).getName());
        assertEquals("Barfi", sweets.get(1).getName());
    }

    @Test
    void shouldReturnSortedListInDescendingBySweetsByPrice() {
        List<String> expectedOrderOfSweets = List.of("Gulab Jamun", "Gajar Halwa", "Kaju Katli");
        List<String> resultedOrderOfSweets = sweetService.sortSweetsByPriceDescending()
                                                            .stream()
                                                            .map(Sweet::getName).collect(Collectors.toList());
        assertEquals(expectedOrderOfSweets,resultedOrderOfSweets);
    }
    
}
