package com.example.sam.textadventure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestLocationObject {
    @Test
    public void LocationInitTest() {
        Location l = new Location("West", "Wild and exciting");
        String expected = "Wild and exciting";
        String actual = l.getDescription();

        assertEquals(expected, actual);
    }

    @Test
    public void LocationLocateTest() {
        Location l = new Location("Cavern", "Wild and exciting");
        Location l2 = new Location("Outside", "Gross");
        l.addPath(new Path(l, l2, "west", "Wild!"));
        String expected = "Wild!";
        String actual = l.returnPath("west").FullDescription();

        assertEquals(expected, actual);
    }
}