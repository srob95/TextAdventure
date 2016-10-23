package com.example.sam.textadventure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestMoveCommand {
    @Test
    public void MovePlayerTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        Location location = new Location ("Location", "A location");
        Location location2 = new Location ("Location2", "Another location");
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");

        //Creates a look command
        MoveCommand move = new MoveCommand ();
        Path path = new Path (location, location2, "north", "A very scary path");
        location.addPath(path);
        test.getInventory().Put(item);
        location.addPlayer (test);

        //Sets the test value to actual
        String actual = move.Execute (test, new String[] { "move", "north"});

        //Sets the value of the expected result
        String expected = "Moved to new location: location2 (Another location)\n" +
                "In the location2 you can find:  \n" +
                "Paths exist to the:";

        assertEquals(expected, actual.trim());
    }
    @Test
    public void MovePlayerBackTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        //Create Locations
        Location location = new Location ("Location", "A location");
        Location location2 = new Location ("Location2", "Another location");
        //Create Items
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");
        //Creates a look command
        MoveCommand move = new MoveCommand ();
        //Create Paths
        Path path = new Path (location, location2, "north", "A very scary path");
        Path pathReturn = new Path (location2, location, "south", "A very scary path");

        location.addPath(path);
        location.addPlayer (test);
        location2.addPath(pathReturn);
        test.getInventory().Put(item);


        String actual = move.Execute (test, new String[] { "move", "north"});
        //Sets the test value to actual
        actual = move.Execute (test, new String[] { "move", "south"});

        //Sets the value of the expected result
        String expected = "Moved to new location: location (A location)\n" +
                "In the location you can find:  \n" +
                "Paths exist to the: (north)";

        assertEquals(expected, actual.trim());
    }
    @Test
    public void MovePlayerBackItemStillExistsTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        //Create Locations
        Location location = new Location ("Location", "A location");
        Location location2 = new Location ("Location2", "Another location");
        //Create Items
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");
        Item item2 = new Item (new String[] {"Gemmy Stone"}, "Gemmy", "A very shiny Gemmy");
        //Creates a look command
        MoveCommand move = new MoveCommand ();
        //Create Paths
        Path path = new Path (location, location2, "north", "A very scary path");
        Path pathReturn = new Path (location2, location, "south", "A very scary path");

        location.addPath(path);
        location.addPlayer (test);
        location.getInventory().Put(item2);
        location2.addPath(pathReturn);
        test.getInventory().Put(item);


        String actual = move.Execute (test, new String[] { "move", "north"});
        //Sets the test value to actual
        actual = move.Execute (test, new String[] { "move", "south"});

        //Sets the value of the expected result
        String expected = "Moved to new location: location (A location)\n" +
                "In the location you can find:  " +
                "\r\n\tgemmy (gemmy stone)\n" +
                "Paths exist to the: (north)";

        assertEquals(expected, actual.trim());
    }
}