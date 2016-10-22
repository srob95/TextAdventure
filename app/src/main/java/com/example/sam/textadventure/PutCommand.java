package com.example.sam.textadventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Obifang on 2016-10-17.
 */
public class PutCommand extends Command{
    public PutCommand()
    {
        super(new String []{"put"});
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
            if (list.get(0).equals("put")) { //Checks that the first word is "put"
                firstInven = FetchOtherContainer (p, list.get(1));
                if (text.length == 2) { //Checks whether there are 2 words
                    item = GetItem (firstInven, list.get(1));
                    if (item != null) {
                        PutItem (item, p);
                        itemDesc = item.ShortDescription()+ " has been placed in inventory";
                    } else
                        itemDesc = list.get(1) + " is not in inventory";
                } else if (text.length > 2) { //Checks whethere there are 4 words
                    list.add (text [2].toLowerCase());
                    list.add (text [3].toLowerCase());
                    if (list.get(2).equals("in")) { //If there are 4 words then the 3rd word must be "in"
                        inven = FetchContainer (p, list.get(3));
                        if (inven != null) {
                            item = GetItem (firstInven, text [1]); //Gives the item Desc based on found container
                            if (item != null) {
                                itemDesc = PutItem (item, inven);
                            } else
                                itemDesc = list.get(1) + " is not in inventory";
                        }else
                            return "Target for item does not exist";
                    } else
                        return "I don't know how to put like that";
                } else
                    return "Where do you want to put?";
            } else
                return "Error in put input";
        } else
            return "I don't know how to put like that";
        return itemDesc;
    }

    private IHaveInventory FetchContainer (Player p, String containterId)
    {
        if ((containterId.equals("me")) || (containterId.equals("invetory"))) {
            return p;
        } else if (containterId.equals(p.getCurrentLocation ().Name ())) {
            return (IHaveInventory) p.getCurrentLocation ();
        } else if (p.getInventory().Fetch (containterId) != null) {
            return (IHaveInventory) p.getInventory().Fetch (containterId);
        } else
            return null;
    }

    private IHaveInventory FetchOtherContainer (Player p, String item)
    {
        if (p.getInventory().Fetch (item) != null) {
            return p;
        } else
            return null;
    }

    private Item GetItem (IHaveInventory p, String thingId)
    {
        Item item;

        if (p != null) {
            if (p.Locate (thingId) == null) {
                item = null;
            } else if (p.Locate (thingId) != null) {
                item = (Item) p.Take (thingId);
            } else
                item = null;
        } else
            item = null;

        return item;
    }

    private String PutItem (Item thing, IHaveInventory container)
    {
        String result;

        if (container == null) {
            result = "Cannot find item";
        } else if (container != null) {
            if (thing != null) {
                container.Put (thing);
                result = thing.ShortDescription() + " has been placed in: " + container.getName();
            } else
                result = "Cannot place item an item that does not exist";
        } else result = "I cannot put the " + thing.Name() + " in the " + container.getName() + " as " + container.getName() + " does not exist";

        return result;
    }

}
