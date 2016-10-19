package com.example.sam.textadventure;

/**
 * Created by Obifang on 2016-10-17.
 */
public abstract class Command extends IdentifiableObject{
    public Command (String [] ids) {
        super(ids);
    }

    public abstract String Execute (Player p, String[] text);
}
