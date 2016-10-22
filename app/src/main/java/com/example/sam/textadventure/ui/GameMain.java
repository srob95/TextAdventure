package com.example.sam.textadventure.ui;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sam.textadventure.R;
import com.example.sam.textadventure.calc.HelpFragment;
import com.example.sam.textadventure.calc.IntroductionFragment;
import com.example.sam.textadventure.calc.InventoryFragment;
import com.example.sam.textadventure.calc.StoryActivity;

public class GameMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int INTRO_STATE = 0;
    private static final int GAME_STATE = 1;


    private Integer GameState = INTRO_STATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initUI();
    }

    private void initUI() {
        if (GameState != GAME_STATE) {
            FragmentTransaction ft;
            getSupportFragmentManager().executePendingTransactions();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_holder, new IntroductionFragment());
            ft.addToBackStack("Intro").commit();
        } else {
            FragmentTransaction ft;
            getSupportFragmentManager().executePendingTransactions();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_holder, new HelpFragment());
            ft.addToBackStack("help").commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        initUI();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ft;
        if (id == R.id.nav_inventory) {
            getSupportFragmentManager().executePendingTransactions();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_holder, new InventoryFragment());
            ft.addToBackStack("Inventory").commit();
        } else if (id == R.id.nav_story) {
            Intent i = new Intent(getApplicationContext(), StoryActivity.class);
            startActivityForResult(i, 0);
        } else if (id == R.id.nav_help) {
            getSupportFragmentManager().executePendingTransactions();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_holder, new HelpFragment());
            ft.addToBackStack("Help").commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(), StoryActivity.class);
        startActivityForResult(i, 1);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent == null)
            Log.i("ACTIVITY-RESULT-Intent", "IS NULL");
        else {
            Bundle bundle = new Bundle();
            bundle = intent.getExtras();
            GameState = bundle.getInt("STATE");
        }
    }

}
