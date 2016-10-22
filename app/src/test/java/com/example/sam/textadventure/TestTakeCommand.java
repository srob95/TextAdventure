package com.example.sam.textadventure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestTakeCommand {
    @Test
    public void TakeItemTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        TakeCommand take = new TakeCommand ();
        Item item = new Item (new String[] {"Sword"}, "Long Sword", "A Long Sword");
        Item item1 = new Item (new String[] {"Sword1"}, "Long Sword1", "A Long Sword1");

        test.getInventory().Put (item);
        location.getInventory().Put (item1);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = take.Execute (test, new String[] {"take", "sword1", "from" , "location"});

        //Sets the value of the expected result
        String expected = "You have taken the long sword1 (sword1) from the location";

        assertEquals (expected, actual);
    }
    @Test
    public void TakeItemNoItemTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        TakeCommand take = new TakeCommand ();
        Item item = new Item (new String[] {"Sword"}, "Long Sword", "A Long Sword");
        Item item1 = new Item (new String[] {"Sword1"}, "Long Sword1", "A Long Sword1");

        test.getInventory().Put (item);
        location.getInventory().Put (item1);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = take.Execute (test, new String[] {"take", "sword", "from" , "location"});

        //Sets the value of the expected result
        String expected = "sword does not exist";

        assertEquals (expected, actual);
    }
    @Test
    public void TakeInvalidTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        TakeCommand take = new TakeCommand ();
        Item item = new Item (new String[] {"Sword"}, "Long Sword", "A Long Sword");
        Item item1 = new Item (new String[] {"Sword1"}, "Long Sword1", "A Long Sword1");

        test.getInventory().Put (item);
        location.getInventory().Put (item1);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = take.Execute (test, new String[] {"takasase", "sword1", "from" , "location"});

        //Sets the value of the expected result
        String expected = "Error in take input";

        assertEquals (expected, actual);
    }
}