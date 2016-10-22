package com.example.sam.textadventure;

/**
 * Created by Obifang on 2016-10-17.
 */
public interface IHaveInventory {
        GameObject Locate(String id);

        void Put (Item item);

        Item Take (String id);

        String getName ();
}
