package com.example.sam.textadventure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestInventoryObject {
    @Test
    public void InvenPutTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        //Create Items
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");
        test.getInventory().Put(item);

        //Sets the test value to actual
        boolean actual = test.getInventory().HasItem("gem");

        //Sets the value of the expected result
        boolean expected = true;

        assertEquals (expected, actual);
    }
    @Test
    public void InvenTakeTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        //Create Items
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");
        test.getInventory().Put(item);
        test.getInventory().Take("gem");

        //Sets the test value to actual
        boolean actual = test.getInventory().HasItem("gem");
        //Sets the value of the expected result
        boolean expected = false;
        assertEquals (expected, actual);
    }
    @Test
    public void InvenTakeFromLocationTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        //Create Locations
        Location location = new Location ("Location", "A location");
        Location location2 = new Location ("Location2", "Another location");
        //Create Items
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");
        Item item2 = new Item (new String[] {"Gemmy Stone"}, "Gemmy", "A very shiny Gemmy");
        //Create Paths
        Path path = new Path (location, location2, "north", "A very scary path");
        Path pathReturn = new Path (location2, location, "south", "A very scary path");

        location.addPath(path);
        location.addPlayer (test);
        location.getInventory().Put(item2);
        location2.addPath(pathReturn);
        test.getInventory().Put(item);
        location.getInventory().Take("gem");

        //Sets the test value to actual
        boolean actual = location.getInventory().HasItem("gem");
        //Sets the value of the expected result
        boolean expected = false;
        assertEquals (expected, actual);
    }

}