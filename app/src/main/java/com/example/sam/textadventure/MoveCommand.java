package com.example.sam.textadventure;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Obifang on 2016-10-17.
 */
public class MoveCommand extends Command{
    public MoveCommand ()
    {
        super(new String [] {"move"});
    }

    @Override
    public String Execute (Player p, String [] text)
    {
        List<String> list;
        String locationDesc = "";
        Path path;

        if ((text.length == 2)) { //Checks that there are 2 words
            list = new ArrayList (Arrays.asList(text [0].toLowerCase(), text [1].toLowerCase())) ;
            if (list.get(0).equals("move") || list.get(0).equals("go") || list.get(0).equals("head") || list.get(0).equals("leave")) { //Checks that the first word is "move", "go", "head" or "leave"
                if (list.get(1).equals("north") || list.get(1).equals("south") || list.get(1).equals("east") || list.get(1).equals("west")) { //Checks that the seconds word is a direction
                    if (p.getCurrentLocation().returnPathDirection (list.get(1)) != null) {
                        path = p.getCurrentLocation().returnPath (list.get(1));
                        MovePlayerToNewPath (path);
                        locationDesc = "Moved to new location: " + p.getCurrentLocation().getFullDescription() + p.getCurrentLocation().hasPaths () + "\n";
                    } else
                        return "You cannot move to a location that does not exist";
                } else
                    return "I don't know how to move like that";
            } else
                return "Where do you want to move?";
        } else
            return "Error in move input";
        return locationDesc;
    }

    private void MovePlayerToNewPath (Path p)
    {
        try {
            p.removePlayerFromLocation ();
            p.addPlayerToLocation ();
        }
        catch (NullPointerException e) {
            Log.i("Message: ", "There seems to be no Item or Player at the location");
        }
    }
}
