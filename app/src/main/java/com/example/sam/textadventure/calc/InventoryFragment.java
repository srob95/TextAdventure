package com.example.sam.textadventure.calc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.sam.textadventure.R;
import com.example.sam.textadventure.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 20/10/2016.
 */
public class InventoryFragment extends ListFragment {

    private View view;
    private MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inventory, container, false);
        activity = (MainActivity) getActivity();
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initializeUI();
        super.onActivityCreated(savedInstanceState);
    }

    private void initializeUI() {
        List<String> str = new ArrayList<>();

        //Set adapter for list view + listen for click action
        ArrayAdapter adapter = new ArrayAdapter<>(activity, R.layout.list_view_item, activity.getPlayer().getInventory().getItemList());
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

}

