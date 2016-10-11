package com.example.sam.textadventure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestGameObject {
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
    public void ShortDescTest() {
        //Creating an Object
        GameObject test = new GameObject(new String[]{"sword"}, "Sword", "Sharp");

        //Sets the test value to actual
        String actual = test.ShortDescription();

        //Sets the value of the expected result
        String expected = "sword (sword)";

        assertEquals(expected, actual);
    }

    @Test
    public void FullDescTest() {
        //Creating an Object
        GameObject test = new GameObject(new String[]{"sword"}, "Sword", "Sharp");

        //Sets the test value to actual
        String actual = test.FullDescription();

        //Sets the value of the expected result
        String expected = "Sharp";

        assertEquals(expected, actual);
    }
}