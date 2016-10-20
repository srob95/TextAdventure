package com.example.sam.textadventure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestPlayerObject {
    @Test
    public void PlayerInitTest() {
        Player p = new Player("Sam", "A man");
        p.getInventory().Put(new Item(new String[]{"id", "banana"}, "banana", "yellow + moldy"));
        String expected = "You are carrying: " + "\n" + "banana (id)";
        String actual = p.FullDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void PlayerInventoryMissingTest() {
        Player p = new Player("Sam", "A man");
        // p.getInventory().Put(new Item(new String[]{"id", "banana"}, "banana", "yellow + moldy"));
        String expected = "You are carrying: nothing.";
        String actual = p.FullDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void PlayerLocationTest() {
        Player p = new Player("Sam", "A man");
        Location l = new Location("Cave", "Dark and mysterious");
        p.setCurrentLocation(l);
        l.addPlayer(p);
        String expected = "Dark and mysterious";
        String actual = p.getCurrentLocation().getDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void PlayerLocateTest() {
        Player p = new Player("Sam", "A man");
        p.getInventory().Put(new Item(new String[]{"bananaID", "banana"}, "banana", "yellow and moldy"));
        String expected = "yellow and moldy";
        String actual = p.Locate("bananaID").FullDescription();
        assertEquals(expected, actual);
    }
}