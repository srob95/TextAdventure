package com.example.sam.textadventure;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 11/10/2016.
 */
public class Location extends GameObject //, IHaveInventory
{
    private Inventory items;
    private Player player;
    private List<Path> paths;
    private String description;

    public Location(String Name, String Description) {
        super(new String[]{"Locations"}, Name, Description);
        paths = new ArrayList<Path>();
        description = Description;
        items = new Inventory();
    }

    public String hasPaths() {
        String direction = "\nPaths exist to the: ";
        for (Path p : paths) {
            direction += "(" + p.getFirstID() + ")";
        }
        return direction;
    }

    public Path returnPath(String id) {
        Path path = null;
        for (int i = 0; i < paths.size(); i++) {
            if ((paths.get(i)).AreYou(id)) {
                path = paths.get(i);
            }
        }
        return path;
    }

    public String returnPathDirection(String id) {
        return returnPath(id).getFirstID();
    }

    public void addPath(Path path) {
        if (path.possibleDirections(path)) {
            paths.add(path);
        } else
            Log.i("Issue: ", "A path cannot be added, as {0} is not a valid direction" + path.getFirstID());
    }

    public Player addPlayer(Player p) {
        player = p;
        p.setCurrentLocation(this);
        return player;
    }

    public void removePlayer(Player p) {
        player = p;
        player = null;
    }

    public GameObject locate(String s) {
        GameObject found = null;

        try {
            if (player.AreYou(s)) {
                found = player.Locate(s);
            } else if (player.getInventory().HasItem(s)) {
                found = player.getInventory().Fetch(s);
            } else if (items.HasItem(s)) {
                found = items.Fetch(s);
            }
        } catch (NullPointerException e) {
            Log.i("Message: ", "There seems to be no Item or Player at the location");
        }

        return found;
    }

    //Get Inventory
    public Inventory getInventory() {
        return items;
    }

    public void setInventory(Inventory value) {
        items = value;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player value) {
        player = value;
    }

    public String getFullDescription() {
        String result = description + "\n" + "In " + description + " you can find: " + " " + items.getItemList().toArray();
        return result;
    }

    public String getDescription() {
        return description;
    }
}