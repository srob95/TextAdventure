package com.example.sam.textadventure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestPutCommand {
    @Test
    public void PutItemTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        PutCommand put = new PutCommand();
        Item item = new Item (new String[] {"Sword"}, "Long Sword", "A Long Sword");
        Item item1 = new Item (new String[] {"Sword1"}, "Long Sword1", "A Long Sword1");

        test.getInventory().Put (item);
        location.getInventory().Put (item1);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = put.Execute (test, new String[] {"put", "sword", "in" , "location"});

        //Sets the value of the expected result
        String expected = "long sword (sword) has been placed in: location";

        assertEquals (expected, actual);
    }
    @Test
    public void PutNotItemTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        PutCommand put = new PutCommand();
        Item item = new Item (new String[] {"Sword"}, "Long Sword", "A Long Sword");
        Item item1 = new Item (new String[] {"Sword1"}, "Long Sword1", "A Long Sword1");

        test.getInventory().Put (item);
        location.getInventory().Put (item1);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = put.Execute (test, new String[] {"put", "swag", "in" , "location"});

        //Sets the value of the expected result
        String expected = "swag is not in inventory";

        assertEquals (expected, actual);
    }
}