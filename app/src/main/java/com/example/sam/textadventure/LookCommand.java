package com.example.sam.textadventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Obifang on 2016-10-17.
 */
public class LookCommand extends Command{
    public LookCommand ()
    {
        super(new String []{"look"});
    }

    @Override
    public String Execute (Player p, String [] text)
    {
        List<String> list;
        IHaveInventory inven;
        String itemDesc = "";


        if ((text.length >= 3) && (text.length <= 5) && (text.length != 4)) { //Checks that there are 3 or 5 words
            list = new ArrayList<> (Arrays.asList(text [0].toLowerCase(), text [1].toLowerCase(), text [2].toLowerCase()));
            if (list.get(0).equals("look")) { //Checks that the first word is "look"
                if (list.get(1).equals("at")) { //Checks that the seconds word is "at"
                    if (text.length == 3) { //Checks whether there are 3 words
                        if (p.getCurrentLocation().getInventory().HasItem (list.get(2))) {
                            itemDesc = LookAtIn (list.get(2), p) + " was found in " + p.getCurrentLocation().getDescription(); //If there are 3 words then container is player
                        } else
                            itemDesc = LookAtIn (list.get(2), p);
                    } else if (text.length > 3) { //Checks whethere there are 5 words
                        list.add (text [3].toLowerCase());
                        list.add (text [4].toLowerCase());
                        if (list.get(3) == "in") { //If there are 5 words then the 4th word must be "in"
                            inven = FetchContainer (p, list.get(4)); //Searches for the container
                            itemDesc = LookAtIn (list.get(2), inven); //Gives the item Desc based on found container
                        } else
                            return "What do you want to look in?";
                    } else
                        return "I don't know how to look like that";
                } else
                    return "What do you want to look at?";
            } else
                return "Error in look input";
        } else
            return "I don't know how to look like that";
        return itemDesc;
    }

    private IHaveInventory FetchContainer (Player p, String containterId)
    {
        IHaveInventory obj = (IHaveInventory)p;
        obj = (IHaveInventory) p.getInventory().Fetch (containterId);

        if ((containterId.equals("me")) || (containterId.equals("inventory"))) {
            return p;
        } else if (p.getInventory().Fetch (containterId) != null) {
            return (IHaveInventory) p.getInventory().Fetch (containterId);
        } else
            return null;
    }

    private String LookAtIn (String thingId, IHaveInventory container)
    {
        String result = null;
        if (container == null) {
            result = "I can't find the Bag";
        } else if (container.Locate (thingId) != null) {
            result = container.Locate (thingId).FullDescription();
        } else if (container == container) {
        result = "I cannot find the " + thingId;
    } else result = "I cannot find the " + thingId + " in the " + container.getName();
        return result;
    }

}
