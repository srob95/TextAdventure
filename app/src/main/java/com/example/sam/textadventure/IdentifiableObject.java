package com.example.sam.textadventure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Obifang on 2016-10-11.
 */
public class IdentifiableObject {
    private List<String> identifiers;

    //Constructor
    public IdentifiableObject(String[] idents) {
        identifiers = new ArrayList<String>();

        //Iterates through the idents array and adds them to the list.
        for (int i = 0; i < idents.length; i++) {
            identifiers.add(idents[i].toLowerCase());
        }
    }

    ///Checks if the object(string) exists in the list
    public boolean AreYou(String id) {
        boolean result = false;
        if (identifiers.contains(id.toLowerCase())) {
            result = true;
        }
        return result;
    }

    //Gets the first ID in the identifiers list
    public String getFirstID() {
        if (identifiers.get(0) == null) {
            return "";
        } else
            return identifiers.get(0);
    }

    //Lets you add an id to the _identifiers list
    public void addIdentifier(String id) {
        identifiers.add(id.toLowerCase());
    }
}
