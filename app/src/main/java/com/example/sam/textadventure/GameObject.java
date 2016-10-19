package com.example.sam.textadventure;

/**
 * Created by Obifang on 2016-10-11.
 */
public class GameObject extends IdentifiableObject {
    private String description;
    private String name;

    public GameObject(String[] ids, String objectName, String desc) {
        super(ids);
        name = objectName.toLowerCase();
        description = desc;
    }

    //Returns the Objects Name
    public String Name() {
        return name;
    }

    //Returns the name and Id of the object
    public String ShortDescription() {
        String shortDesc = name.toLowerCase() + " (" + getFirstID() + ")";
        return shortDesc;
    }

    //Returns the description of the object
    public String FullDescription() {
        return description;
    }
}


