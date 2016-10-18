package com.example.sam.textadventure;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sam on 27/09/2016.
 */
public class Player extends GameObject implements IHaveInventory {
    private Inventory inventory;
    private Location currentLocation;

    public Player(String name, String desc)
    {
        super(new String[]{"me", "inventory"}, name, desc);
        inventory = new Inventory();
    }

    public GameObject Locate(String id)
    {
        GameObject item = null;

        if (id.equals("me") || id.equals("inventory")) {
            item = this;
        } else if (inventory.HasItem(id)) {
            item = inventory.Fetch(id);
        } else if (currentLocation != null) {
            if (currentLocation.getInventory().HasItem(id)) {
                item = currentLocation.locate(id);
            }
        }
        return item;
    }

    @Override
    public String getName() {
       return super.Name();
    }

    public String FullDescription() {
        String result = "You are carrying:" + " " + inventory.getItemList() ;
        return result.replace("]","").replace("[", "").replace(",", "");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location value) {
        currentLocation = value;
    }

}