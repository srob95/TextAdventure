package com.example.sam.textadventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Obifang on 2016-10-17.
 */
public class TakeCommand extends Command{
    public TakeCommand()
    {
        super(new String []{"take"});
    }

    @Override
    public String Execute (Player p, String [] text)
    {
        List<String> list;
        IHaveInventory inven;
        IHaveInventory firstInven;
        String itemDesc = "";
        Item item;


        if ((text.length >= 2) && (text.length <= 4) && (text.length != 3)) { //Checks that there are 2 or 4 words
            list = new ArrayList<> (Arrays.asList(text [0].toLowerCase(), text [1].toLowerCase()));
            if (list.get(0).equals("take")) { //Checks that the first word is "take"
                firstInven = FetchOtherContainer (p, list.get(1)); //If there are 2 words then searches for cantainer of item.
                if (text.length == 2) { //Checks whether there are 2 words
                    item = TakeItem (p, firstInven, list.get(1));
                    if (item != null) {
                        itemDesc = item.ShortDescription () + " has been placed in inventory";
                    } else
                        return "It seems there is a " + text [1] + " in your inventory already\r\n Please specify from where you would like to take the " + text [1];
                } else if (text.length > 2) { //Checks whethere there are 4 words
                    list.add (text [2].toLowerCase ());
                    list.add (text [3].toLowerCase ());
                    if (list.get(2).equals("from")) { //If there are 4 words then the 3rd word must be "from"
                        inven = FetchContainer (p, list.get(3)); //Returns the container
                        if (inven != null) {
                            item = TakeItem (p, inven, text [1]); //Places the item from the container into
                            if (item != null) {
                                itemDesc = "You have taken the " + item.ShortDescription () + " from the " + inven.getName();
                            } else
                                return text [1] + " does not exist";
                        } else
                            return "Target for item does not exist";
                    } else
                        return "I don't know how to take like that";
                } else
                    return "Where do you want to take from?";
            } else
                return "Error in take input";
        } else
            return "I don't know how to take like that";
        return itemDesc;
    }

    //If there are 4 words then it uses the 4th word to find the container in the location
    private IHaveInventory FetchContainer (Player p, String containterId)
    {
        if (containterId.equals(p.getCurrentLocation().Name ())) {
            return (IHaveInventory) p.getCurrentLocation();
        } else if (p.getCurrentLocation().Locate (containterId) != null) {
            return (IHaveInventory) p.getCurrentLocation().getInventory().Fetch (containterId);
        } else if (p.getInventory().Fetch (containterId) != null) {
            return (IHaveInventory) p.getInventory().Fetch (containterId);
        } else
            return null;
    }

    //Finds the container for the item if there are only 2 words
    private IHaveInventory FetchOtherContainer (Player p, String item)
    {
        if (p.getCurrentLocation().getInventory().Fetch (item) != null) {
            return (IHaveInventory) p.getCurrentLocation();
        } else
            return null;
    }

    private Item TakeItem (Player player, IHaveInventory p, String thingId)
    {
        Item item;

        if (p != null) {
            if (p.Locate (thingId) == null) {
                item = null;
            } else if (p.Locate (thingId) != null) {
                if (player.getInventory().HasItem (thingId) == false) {
                    item = (Item)p.Take (thingId);
                    player.getInventory().Put (item);
                } else
                    item = null;
            } else
                item = null;
        } else
            item = null;
        return item;
    }
}
