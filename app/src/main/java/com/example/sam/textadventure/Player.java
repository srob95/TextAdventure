package com.example.sam.textadventure;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sam on 27/09/2016.
 */
public class Player extends AppCompatActivity {
    private String name;

    // Constructors
    public Player()
    {
        name="Anonymous";
    }
    public Player(String userName)
    {
        name = userName;
    }

    //Setters & Getters
    public void setName(String userName) { name=userName;}
    public String getName(){return name;}

}
