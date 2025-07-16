package com.incubyte.sweetshop.service;

import com.incubyte.sweetshop.customExceptions.InsufficientStockException;
import com.incubyte.sweetshop.customExceptions.SweetAlreadyExistsException;
import com.incubyte.sweetshop.customExceptions.SweetNotFoundException;
import com.incubyte.sweetshop.model.Sweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SweetServiceTest {

     //                          ---------------ADD Sweets-------------

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

    //for adding sweets into list
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

    //                      --------------DELETE Sweet By Id--------------

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

    //                      --------------DELETE Sweet By Name--------------
    @Test
    void shouldDeleteSweetByNameSuccessfully() {
        Sweet deletedSweet = sweetService.deleteSweetByName("Kaju Katli");
        assertEquals("Kaju Katli", deletedSweet.getName());
        assertEquals(2, sweetService.getAllSweets().size());
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingSweetByName() {
        assertThrows(SweetNotFoundException.class, () -> sweetService.deleteSweetByName("Peda"));
    }

    //                        -------------Search By Name---------------
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
    void shouldThrowExceptionIfSearchedSweetIsNotFound(){
        assertThrows(SweetNotFoundException.class,()->sweetService.searchSweetByName("rasgulla"));
    }

    //                        -------------Search By Category---------------
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

    //                        -------------Search By Price Range---------------
    @Test
    void shouldReturnSweetsWithinGivenPriceRange() {
        List<Sweet> sweetsInRange = sweetService.searchSweetsByPriceRange(10.0, 40.0);
        assertEquals(2, sweetsInRange.size());
        assertTrue(sweetsInRange.stream().anyMatch(s -> s.getName().equals("Gulab Jamun")));
        assertTrue(sweetsInRange.stream().anyMatch(s -> s.getName().equals("Gajar Halwa")));
    }

    //                        -------------Sort By Price in ascending and descending---------------
    @Test
    void shouldReturnSortedListInDescendingBySweetsByPrice() {
        List<String> expectedOrderOfSweets = List.of("Kaju Katli", "Gajar Halwa", "Gulab Jamun");
        List<String> resultedOrderOfSweets = sweetService.sortSweetsByPriceDescending()
                                                            .stream()
                                                            .map(Sweet::getName).toList();
        assertEquals(expectedOrderOfSweets,resultedOrderOfSweets);
    }

    @Test
    void shouldReturnSortedListInAscendingBySweetsByPrice() {
        List<String> expectedOrderOfSweets = List.of("Gulab Jamun", "Gajar Halwa", "Kaju Katli");
        List<String> resultedOrderOfSweets = sweetService.sortSweetsByPriceAscending()
                .stream()
                .map(Sweet::getName).collect(Collectors.toList());
        assertEquals(expectedOrderOfSweets,resultedOrderOfSweets);
    }

    //                        -------------Inventory Management------------
    //                        -------------Purchase sweet---------------
    @Test
    void shouldPurchaseSweetSuccessfullyAndReduceStock() {
        Sweet purchasedSweet = sweetService.purchaseSweet(1000L, 5);  // ID = 1000, quantity = 5
        assertEquals(15, purchasedSweet.getQuantityInStock());  // original stock was 20 of Kaju Katli
    }

    @Test
    void shouldThrowExceptionIfStockNotAvailable() {
        assertThrows(InsufficientStockException.class,()->sweetService.purchaseSweet(1001L, 16));// original stock was 15 only of gajar halwa
    }

    @Test
    void shouldThrowExceptionIfSweetNotFound() {
        assertThrows(SweetNotFoundException.class,()->sweetService.purchaseSweet(1009L, 10));
    }

    //                        -------------Restock sweet---------------
    @Test
    void shouldRestockSweetSuccessfully() {
        Sweet restockedSweet = sweetService.restockSweet(1000L, 10);  // Assuming ID 1000 exists
        assertEquals(30, restockedSweet.getQuantityInStock());  // Original was 20, now should be 30
    }
}
