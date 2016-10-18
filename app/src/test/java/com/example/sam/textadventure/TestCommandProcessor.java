package com.example.sam.textadventure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestCommandProcessor {
    @Test
    public void FindCommandMoveFailTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        //Create Locations
        Location location = new Location ("Location", "A location");
        Location location2 = new Location ("Location2", "Another location");
        //Create Items
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");
        //Creates a look command

        //Create Paths
        Path path = new Path (location, location2, "north", "A very scary path");
        Path pathReturn = new Path (location2, location, "south", "A very scary path");

        location.addPath(path);
        location.addPlayer (test);
        location2.addPath(pathReturn);
        test.getInventory().Put(item);

        CommandProcessor command = new CommandProcessor (test, "move south");
        //Sets the test value to actual
        String actual = command.Execute();
        //Sets the value of the expected result
        String expected = "You cannot move to a location that does not exist";

        assertEquals (expected, actual);
    }
    @Test
    public void FindCommandMovePassTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        //Create Locations
        Location location = new Location ("Location", "A location");
        Location location2 = new Location ("Location2", "Another location");
        //Create Items
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");
        //Creates a look command

        //Create Paths
        Path path = new Path (location, location2, "north", "A very scary path");
        Path pathReturn = new Path (location2, location, "south", "A very scary path");

        location.addPath(path);
        location.addPlayer (test);
        location2.addPath(pathReturn);
        test.getInventory().Put(item);

        CommandProcessor command = new CommandProcessor (test, "move north");
        //Sets the test value to actual
        String actual = command.Execute();
        //Sets the value of the expected result
        String expected = "Moved to new location: Another location\n" +
                "In Another location you can find:  \n" +
                "Paths exist to the: (south)";

        assertEquals (expected, actual);
    }
    @Test
    public void FindCommandLookFailTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        //Create Locations
        Location location = new Location ("Location", "A location");
        Location location2 = new Location ("Location2", "Another location");
        //Create Items
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");
        //Creates a look command

        //Create Paths
        Path path = new Path (location, location2, "north", "A very scary path");
        Path pathReturn = new Path (location2, location, "south", "A very scary path");

        location.addPath(path);
        location.addPlayer (test);
        location2.addPath(pathReturn);
        test.getInventory().Put(item);

        CommandProcessor command = new CommandProcessor (test, "look at sword");
        //Sets the test value to actual
        String actual = command.Execute();
        //Sets the value of the expected result
        String expected = "I cannot find the sword";

        assertEquals (expected, actual);
    }
    @Test
    public void FindCommandLookPassTest() {
        //Creating an Inventory and Item Object
        Player test = new Player ("Bob", "Bob's the man");
        //Create Locations
        Location location = new Location ("Location", "A location");
        Location location2 = new Location ("Location2", "Another location");
        //Create Items
        Item item = new Item (new String[] {"Gem Stone"}, "Gem", "A very shiny Gem");
        //Creates a look command

        //Create Paths
        Path path = new Path (location, location2, "north", "A very scary path");
        Path pathReturn = new Path (location2, location, "south", "A very scary path");

        location.addPath(path);
        location.addPlayer (test);
        location2.addPath(pathReturn);
        test.getInventory().Put(item);

        CommandProcessor command = new CommandProcessor (test, "look at gem");
        //Sets the test value to actual
        String actual = command.Execute();
        //Sets the value of the expected result
        String expected = "A very shiny Gem";

        assertEquals (expected, actual);
    }
}