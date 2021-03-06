package com.example.sam.textadventure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestLookCommand {
    @Test
    public void AreYouTest() {
        //Creating an Object
        GameObject test = new GameObject(new String[]{"sword"}, "Sword", "Sharp");

        //Sets the test value to actual
        boolean actual = test.AreYou("sword");

        //Sets the value of the expected result
        boolean expected = true;

        assertEquals(expected, actual);
    }
    @Test
    public void LookAtMeItemTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        LookCommand look = new LookCommand ();
        Item item = new Item (new String[] {"Sword"}, "Long Sword", "A Long Sword");
        Item item1 = new Item (new String[] {"Sword"}, "Long Sword1", "A Long Sword1");

        test.getInventory().Put (item);
        test.getInventory().Put (item1);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = look.Execute (test, new String[] { "look", "at", "inventory"});

        //Sets the value of the expected result
        String expected = "You are carrying: \r\n\tlong sword (sword) \r\n\tlong sword1 (sword)";

        assertEquals (expected, actual);
    }
    @Test
    public void LookAtGemTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        LookCommand look = new LookCommand ();
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");

        test.getInventory().Put (item);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = look.Execute (test, new String[] { "look", "at", "gem"});
        //Sets the value of the expected result
        String expected = "A very shiny Gem";

        assertEquals (expected, actual);
    }
    @Test
    public void LookAtNoGemTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        LookCommand look = new LookCommand ();
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");

        location.addPlayer (test);

        //Sets the test value to actual
        String actual = look.Execute (test, new String[] { "look", "at", "gem"});
        //Sets the value of the expected result
        String expected = "I cannot find the gem";

        assertEquals (expected, actual);
    }
    @Test
    public void LookAtGemInMeTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        LookCommand look = new LookCommand ();
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");

        test.getInventory().Put (item);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = look.Execute (test, new String[] { "look", "at", "gem", "in", "me"});
        //Sets the value of the expected result
        String expected = "A very shiny Gem";

        assertEquals(expected, actual.trim());
    }
    @Test
    public void LookInvalidNoWordsTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        LookCommand look = new LookCommand ();
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");

        test.getInventory().Put (item);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = look.Execute (test, new String[] { "look", "at", "gem", "in", "me", "gem"});
        //Sets the value of the expected result
        String expected = "I don't know how to look like that";

        assertEquals(expected, actual.trim());
    }
    @Test
    public void LookInvalidLookTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        //Creates a look command
        LookCommand look = new LookCommand ();
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");

        test.getInventory().Put (item);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = look.Execute (test, new String[] { "lookkk", "at", "gem", "in", "me"});
        //Sets the value of the expected result
        String expected = "Error in look input";

        assertEquals (expected, actual);
    }
}