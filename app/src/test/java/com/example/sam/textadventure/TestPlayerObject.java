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
        Inventory i = new Inventory();
        i.Put(new Item(new String[]{"id", "banana"}, "banana", "yellow + moldy"));

    }

}