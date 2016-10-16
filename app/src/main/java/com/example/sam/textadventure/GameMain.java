package com.example.sam.textadventure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameMain extends AppCompatActivity {

    //private Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
        initUI();
    }

    private void initUI() {
        EditText userName = (EditText) findViewById(R.id.userName);
        TextView greeting = (TextView) findViewById(R.id.greeting);
        greeting.setText("");
        userName.setOnFocusChangeListener(fcl);
    }

    public void confirmButtonClick(View v) {
        TextView greeting = (TextView) findViewById(R.id.greeting);
        TextView question = (TextView) findViewById(R.id.textView);
        question.setText(""); // removes text at top
        EditText userName = (EditText) findViewById(R.id.userName);
        userName.setVisibility(View.GONE);
        v.setVisibility(View.GONE); //removes button from view
        //String message = "Greetings " + player.getName() + ".";
        //greeting.setText(message);

    }


    //Name function
    /** Focus change will trigger a store of all information provided so far */
    View.OnFocusChangeListener fcl = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            AddName();
        }
    };

    private void AddName() {
        EditText userName = (EditText)findViewById(R.id.userName);
        //player.setName(userName.getText().toString());
    }
}
