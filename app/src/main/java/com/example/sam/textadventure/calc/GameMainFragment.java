package com.example.sam.textadventure.calc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sam.textadventure.CommandProcessor;
import com.example.sam.textadventure.Item;
import com.example.sam.textadventure.Location;
import com.example.sam.textadventure.Path;
import com.example.sam.textadventure.Player;
import com.example.sam.textadventure.R;
import com.example.sam.textadventure.ui.MainActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 22/10/2016.
 */
public class GameMainFragment extends Fragment {
    private View view;
    private Player player;
    private MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_commands, container, false);
        player = new Player("You", "Handsome");
        activity = (MainActivity) getActivity();
        player.setCurrentLocation(activity.getCurrentLocation());
        TextView tempText = (TextView) view.findViewById(R.id.responseText);
        tempText.setText(activity.getCommandHistory());
        initUI();
        return view;
    }

    private void initUI() {
        final TextView responseText = (TextView) view.findViewById(R.id.responseText);
        final EditText commandProcessor = (EditText) view.findViewById(R.id.commandProcessor);

        Button confirmButton = (Button) view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CommandProcessor cp = new CommandProcessor(player, commandProcessor.getText().toString());
                String response = cp.Execute();
                commandProcessor.setText("");
                String output = response + "\n" + responseText.getText() + "\n";
                responseText.setText(output);
                activity.updateHistory(output);
            }
        });
    }

}

