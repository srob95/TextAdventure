package com.example.sam.textadventure;

import android.util.Log;

/**
 * Created by Sam on 11/10/2016.
 */
public class Path extends GameObject {
    private Player player;
    private Location local1;
    private Location local2;

    public Path(Location l, Location l2, String direction, String desc) {
        super(new String[]{direction}, "Path", desc);
        local1 = l;
        local2 = l2;
    }

    public boolean possibleDirections(Path path) {
        boolean result = false;
        if (path.getFirstID().equals("north")) {
            result = true;
        } else if (path.getFirstID().equals("east")) {
            result = true;
        } else if (path.getFirstID().equals("south")) {
            result = true;
        } else if (path.getFirstID().equals("west")) {
            result = true;
        }
        return result;
    }

    public void addPlayerToLocation() {
        Player p;
        p = returnPlayer();
        local2.addPlayer(p);
    }

    public void removePlayerFromLocation() {
        player = local1.getPlayer();
        local1.setPlayer(null);
    }

    private Player returnPlayer() {
        Player p;
        p = player;
        player = null;
        return p;
    }
}
