package com.example.sam.textadventure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestIdentifiableObject {
    @Test
    public void AreYouTest() throws Exception {
        //Creating an Object
        IdentifiableObject test = new IdentifiableObject(new String[]{"id1", "id2"});

        //Sets the test value to actual
        boolean actual = test.AreYou("id1");

        //Sets the value of the expected result
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void AreYouNotTest() //Test whether and identifier is not available
    {
        //Creating an Object
        IdentifiableObject test = new IdentifiableObject(new String[]{"id1", "id2"});

        //Sets the test value to actual
        boolean actual = test.AreYou("id4");

        //Sets the value of the expected result
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void AreYouCaseTest() //Test whether an identifier is able to be located regardless of case
    {
        //Creating an Object
        IdentifiableObject test = new IdentifiableObject(new String[]{"id1", "id2"});

        //Sets the test value to actual
        boolean actual = test.AreYou("ID1");

        //Sets the value of the expected result
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void FirstIDTest() //Test whether the first identifier is correct
    {
        //Creating an Object
        IdentifiableObject test = new IdentifiableObject(new String[]{"id1", "id2"});

        //Sets the test value to actual
        String actual = test.getFirstID();

        //Sets the value of the expected result
        String expected = "id1";

        assertEquals(expected, actual);
    }

    @Test
    public void AddIdentifierTest() //Test whether you are able to add an identifier
    {
        //Creating an Object
        IdentifiableObject test = new IdentifiableObject(new String[]{"id1", "id2"});

        //Adds identifier to list
        test.addIdentifier("id3");

        //Sets the test value to actual
        boolean actual = test.AreYou("id3");

        //Sets the value of the expected result
        boolean expected = true;

        assertEquals(expected, actual);
    }
}