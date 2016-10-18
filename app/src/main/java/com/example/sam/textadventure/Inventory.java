package com.example.sam.textadventure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 11/10/2016.
 * Inventory
 */
public class Inventory extends GameObject {
    private List<Item> items;

    //Inventory Constructor
    public Inventory() {
        super(new String[]{"Inven"}, "Inven", "Inven");
        items = new ArrayList<Item>();
    }


    //Checks if Item in Inventory
    public boolean HasItem(String id) {
        boolean result = false;
        for (Item item : items) {
            if (item.getFirstID().equals(id)) {
                result = true;
                break;
            } else if (item.Name().equals(id)){
                result = true;
                break;
            }
        }
        return result;
    }

    //Adds Item to Inventory
    public void Put(Item itm) {
        items.add(itm);
    }

    //Item Retrieval
    public Item Fetch(String id) {
        Item itemReturn = null;
        if (HasItem(id)) {
                for (Item item : items) {
                    if (item.AreYou(id)) {
                        itemReturn = item;
                        break;
                    } else if (item.Name().equals(id)){
                        itemReturn = item;
                        break;
                    }
                }
                return itemReturn;
        }
        return itemReturn;
    }

    // Removes Item
    public Item Take(String id) {
        Item item = null;
        item = Fetch(id);
        items.remove(item);
        return item;
    }

    //Returns Description of Items In Inventory
    public List<String> getItemList() {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < items.size(); i++) {
            list.add("\r\n\t" + items.get(i).ShortDescription());
        }
        return list;
    }
}

