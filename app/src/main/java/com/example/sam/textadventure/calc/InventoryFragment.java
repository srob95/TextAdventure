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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 20/10/2016.
 */
public class InventoryFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initializeUI();
        super.onActivityCreated(savedInstanceState);
    }


    private void initializeUI() {
        List<String> str = new ArrayList<>();
        str.add("Nothing");

        //Set adapter for list view + listen for click action
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.list_view_item, str);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

}

