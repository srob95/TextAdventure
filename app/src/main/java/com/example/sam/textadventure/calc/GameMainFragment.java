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
    private static final int INTRO_STATE = 0;
    private static final int GAME_STATE = 1;

    private View view;
    private MainActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_commands, container, false);
        activity = (MainActivity) getActivity();
        if (activity.getGameState() == INTRO_STATE) {
            TextView responseText = (TextView) view.findViewById(R.id.responseText);
            EditText commandProcessor = (EditText) view.findViewById(R.id.commandProcessor);
            Button confirmButton = (Button) view.findViewById(R.id.confirmButton);
            confirmButton.setVisibility(View.GONE);
            responseText.setText("Please Read The Story First In Background Story");
            commandProcessor.setVisibility(View.GONE);
        } else {
            TextView tempText = (TextView) view.findViewById(R.id.responseText);

            if (activity.getCommandHistory() == null) {
                String items = "";
                String paths = "";
                for (int i = 0; i < activity.getLocations().get(0).getInventory().getItemList().size(); i++) {
                    items += activity.getLocations().get(0).getInventory().getItemList().get(i);
                }
                paths = activity.getLocations().get(0).hasPaths();
                if (activity.getLocations().get(0).getInventory().getItemList().size() == 0) {
                    items = "Nothing of note.";
                }
                tempText.setText("You are located in: \n  " + activity.getPlayer().getCurrentLocation().getName() + ". \n Items:\n  " + items + "  " + paths);
            } else {
                tempText.setText(activity.getCommandHistory());
            }

            initUI();
        }
        return view;
    }


    private void initUI() {
        final TextView responseText = (TextView) view.findViewById(R.id.responseText);
        final EditText commandProcessor = (EditText) view.findViewById(R.id.commandProcessor);

        Button confirmButton = (Button) view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CommandProcessor cp = new CommandProcessor(activity.getPlayer(), commandProcessor.getText().toString());
                String response = cp.Execute();
                activity.openPath();
                if (activity.checkEnd()){
                    response = ("A shiver runs down your spine, the prickling of an earie silence " +
                            "begins to bite at the back of your neck.\n Congratulations you have escaped the castle!" +
                            "But is this really the end?\n" +
                            "You can continue to explore and perhaps uncover secrets untold.");
                }
                commandProcessor.setText("");
                String output = response + "\n" + responseText.getText() + "\n";
                responseText.setText(output);
                activity.updateHistory(output);
            }
        });
    }

}

