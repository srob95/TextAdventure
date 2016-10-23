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

import com.example.sam.textadventure.Item;
import com.example.sam.textadventure.Location;
import com.example.sam.textadventure.Path;
import com.example.sam.textadventure.Player;
import com.example.sam.textadventure.R;
import com.example.sam.textadventure.calc.GameMainFragment;
import com.example.sam.textadventure.calc.HelpFragment;
import com.example.sam.textadventure.calc.IntroductionFragment;
import com.example.sam.textadventure.calc.InventoryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int INTRO_STATE = 0;
    private static final int GAME_STATE = 1;
    private static final int GAME_INTRO_STATE = 2;

    private static final int CELL = 0;
    private static final int PRISON_WING = 1;
    private static final int PRISON_WARD = 2;
    private static final int PRISON_WARD_EAST = 3;
    private static final int PRISON_WARD_SOUTH = 4;
    private static final int CASTLE_COURTYARD = 5;
    private static final int CASTLE_KITCHEN = 6;
    private static final int CASTLE_DINING_ROOM = 7;
    private static final int CASTLE_MORGUE = 8;
    private static final int CASTLE_CENTRAL_HALL = 9;
    private static final int STAIRWAY = 10;
    private static final int HEAVENS_GATES = 11;
    private static final int KINGS_ROOM = 12;


    private Integer GameState = INTRO_STATE;
    private List<Location> locations = new ArrayList<Location>();
    private Player player = new Player("You", "Handsome");
    private Location currentLocation;
    private String commandHistory;

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
            LoadResources();
            int locationID;
            boolean initPlayer = false;
            for (int i = 0; i < getLocations().size(); i++) {
                //Check if player is added to map
                if (getLocations().get(i) != null) {
                    locationID = i;
                    initPlayer = true;
                }
            }
            if (initPlayer) {
                this.getLocations().get(0).addPlayer(player);
            }
            FragmentTransaction ft;
            getSupportFragmentManager().executePendingTransactions();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_holder, new GameMainFragment());
            ft.addToBackStack("GameMain").commit();
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
        } else if (id == R.id.nav_gameMain) {
            getSupportFragmentManager().executePendingTransactions();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_holder, new GameMainFragment());
            ft.addToBackStack("GameMain").commit();
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

    public void openPath(){
        if (checkOpenPath()){
            if(locations.get(STAIRWAY).returnPath("south") == null) {
                Path stairwayPathSouth = new Path(locations.get(STAIRWAY), locations.get(HEAVENS_GATES), "South", "Southern Door");
                locations.get(STAIRWAY).addPath(stairwayPathSouth);
            }
        }
    }

    public boolean checkEnd(){
        boolean result = false;
        if(player.getInventory().HasItem("hgem")){
            result = true;
        }
        return result;
    }

    public boolean checkOpenPath(){
        boolean result = false;

        if(player.getInventory().HasItem("mgem") && player.getInventory().HasItem("ggem") &&
                player.getInventory().HasItem("ygem") && player.getInventory().HasItem("rgem") &&
                player.getInventory().HasItem("pgem")){
            result = true;
        }

        return result;
    }

    private void LoadResources() {
        if (locations.isEmpty()) {
            //Locations

            Location cell = new Location("Cell", "Dark and Mysterious");
            Location prisonWing = new Location("Prison Wing", "Cold and Disgusting");
            Location prisonWard = new Location("Prison Ward", "The Prison Ward is Freezing!");
            Location prisonWardEast = new Location("Prison Ward East", "Still Freezing!");
            Location prisonWardSouth = new Location("Prison Ward South", "Even Colder!");
            Location castleCourtyard = new Location("Castle Courtyard", "Overcast.");
            Location castleKitchen = new Location("Castle Kitchen", "Dirty and Unhygienic, Yuk!");
            Location castleDiningRoom = new Location("Castle Dining Room", "Superfluous.");
            Location castleMorgue = new Location("Castle Morgue", "Spooky!");
            Location castleCentralHall = new Location("Castle Central Hall", "Evil.");
            Location stairway = new Location("Stairway to Heaven", "Overzealous.");
            Location heavensGates = new Location("Heaven's Gates", "A strange name for a bedroom.");
            Location kingsRoom = new Location("The Kings Room", "Glorious.");


            //Items
            Item magicGem = new Item(new String[]{"MGem"}, "Magic Gem", "Beautiful and Magical.");
            Item greenGem = new Item(new String[]{"GGem"}, "Green Gem", "A Lovely Emerald Colour.");
            Item magicCandle = new Item(new String[]{"YGem"}, "Yellow Gem", "This looks like....");
            Item redGem = new Item(new String[]{"RGem"}, "Red Gem", "A Lovely Ruby Colour.");
            Item purpleGem = new Item(new String[]{"PGem"}, "Purple Gem", "A Lovely Purple Colour.");
            Item bedroomKey = new Item(new String[]{"BedKey"}, "Bedroom Key", "A Simple Key.");
            Item holygem = new Item(new String[]{"HGem"}, "Holy Gem", "The Ultimate Treasure.");


            //Locations add
            locations.add(cell);
            locations.add(prisonWing);
            locations.add(prisonWard);
            locations.add(prisonWardEast);
            locations.add(prisonWardSouth);
            locations.add(castleCourtyard);
            locations.add(castleKitchen);
            locations.add(castleDiningRoom);
            locations.add(castleMorgue);
            locations.add(castleCentralHall);
            locations.add(stairway);
            locations.add(heavensGates);
            locations.add(kingsRoom);

            //Items Add
            locations.get(PRISON_WARD).getInventory().Put(magicGem);
            locations.get(PRISON_WARD_SOUTH).getInventory().Put(greenGem);
            locations.get(CASTLE_MORGUE).getInventory().Put(magicCandle);
            locations.get(CASTLE_KITCHEN).getInventory().Put(redGem);
            locations.get(CASTLE_CENTRAL_HALL).getInventory().Put(purpleGem);
            locations.get(HEAVENS_GATES).getInventory().Put(bedroomKey);
            locations.get(KINGS_ROOM).getInventory().Put(holygem);

            //Paths
            Path cellPathWest = new Path(cell, prisonWing, "West", "Western Door");

            Path prisonWingPathEast = new Path(prisonWing, cell, "East", "Eastern Door");
            Path prisonWingPathSouth = new Path(prisonWing, prisonWard, "South", "Southern Door");

            Path prisonWardPathEast = new Path(prisonWard, prisonWardEast, "East", "Eastern Door");
            Path prisonWardPathNorth = new Path(prisonWard, prisonWing, "North", "Northern Door");
            Path prisonWardPathSouth = new Path(prisonWard, prisonWardSouth, "South", "Southern Door");

            Path prisonWardSouthPathNorth = new Path(prisonWardSouth, prisonWard, "North", "Northern Door");
            Path prisonWardSouthPathSouth = new Path(prisonWardSouth, castleMorgue, "South", "Southern Door");

            Path castleMorguePathNorth = new Path(castleMorgue, prisonWardSouth, "North", "Northern Door");
            Path castleMorguePathEast = new Path(castleMorgue, castleCentralHall, "East", "Eastern Door");

            Path castleCentralHallPathWest = new Path(castleCentralHall, castleMorgue, "West", "Western Door");
            Path castleCentralHallPathEast = new Path(castleCentralHall, castleDiningRoom, "East", "Eastern Door");
            Path castleCentralHallPathSouth = new Path(castleCentralHall, stairway, "South", "Southern Door");

            Path prisonWardEastPathWest = new Path(prisonWardEast, prisonWard, "West", "Western Door");
            Path prisonWardEastPathEast = new Path(prisonWardEast, castleCourtyard, "East", "Eastern Door");

            Path castleCourtyardPathWest = new Path(castleCourtyard, prisonWardEast, "West", "Western Door");
            Path castleCourtyardPathSouth = new Path(castleCourtyard, castleKitchen, "South", "Southern Door");

            Path castleKitchenPathNorth = new Path(castleKitchen, castleCourtyard, "North", "Northern Door");
            Path castleKitchenPathSouth = new Path(castleKitchen, castleDiningRoom, "South", "Southern Door");

            Path castleDiningRoomPathNorth = new Path(castleDiningRoom, castleKitchen, "North", "Northern Door");
            Path castleDiningRoomPathWest = new Path(castleDiningRoom, castleCentralHall, "West", "Western Door");

            Path stairwayPathNorth = new Path(stairway, castleCentralHall, "North", "Northern Door");


            Path heavensGatesPathNorth = new Path(heavensGates, stairway, "North", "Northern Door");
            Path heavensGatesPathWest = new Path(heavensGates, kingsRoom, "West", "Western Door");

            Path kingsRoomPathEast = new Path(kingsRoom, heavensGates, "East", "Eastern Door");

            //Add paths
            locations.get(CELL).addPath(cellPathWest);
            locations.get(PRISON_WING).addPath(prisonWingPathEast);
            locations.get(PRISON_WING).addPath(prisonWingPathSouth);
            locations.get(PRISON_WARD).addPath(prisonWardPathEast);
            locations.get(PRISON_WARD).addPath(prisonWardPathNorth);
            locations.get(PRISON_WARD).addPath(prisonWardPathSouth);
            locations.get(PRISON_WARD_EAST).addPath(prisonWardEastPathEast);
            locations.get(PRISON_WARD_EAST).addPath(prisonWardEastPathWest);
            locations.get(PRISON_WARD_SOUTH).addPath(prisonWardSouthPathNorth);
            locations.get(PRISON_WARD_SOUTH).addPath(prisonWardSouthPathSouth);
            locations.get(CASTLE_CENTRAL_HALL).addPath(castleCentralHallPathEast);
            locations.get(CASTLE_CENTRAL_HALL).addPath(castleCentralHallPathSouth);
            locations.get(CASTLE_CENTRAL_HALL).addPath(castleCentralHallPathWest);
            locations.get(CASTLE_MORGUE).addPath(castleMorguePathEast);
            locations.get(CASTLE_MORGUE).addPath(castleMorguePathNorth);
            locations.get(CASTLE_COURTYARD).addPath(castleCourtyardPathSouth);
            locations.get(CASTLE_COURTYARD).addPath(castleCourtyardPathWest);
            locations.get(CASTLE_KITCHEN).addPath(castleKitchenPathNorth);
            locations.get(CASTLE_KITCHEN).addPath(castleKitchenPathSouth);
            locations.get(CASTLE_DINING_ROOM).addPath(castleDiningRoomPathNorth);
            locations.get(CASTLE_DINING_ROOM).addPath(castleDiningRoomPathWest);
            locations.get(STAIRWAY).addPath(stairwayPathNorth);

            locations.get(HEAVENS_GATES).addPath(heavensGatesPathNorth);
            locations.get(HEAVENS_GATES).addPath(heavensGatesPathWest);
            locations.get(KINGS_ROOM).addPath(kingsRoomPathEast);

            currentLocation = cell;
        }
    }

    public List<Location> getLocations() {
        return locations;
    }

    public String getCommandHistory() {
        return commandHistory;
    }

    public void updateHistory(String text) {
        commandHistory = text;
    }

    public Player getPlayer() {
        return player;
    }

    public int getGameState() {
        return GameState;
    }

}
