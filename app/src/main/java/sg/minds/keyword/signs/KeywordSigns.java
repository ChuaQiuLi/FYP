package sg.minds.keyword.signs;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class KeywordSigns extends AppCompatActivity {

    CustomAdapter adapter;
    ArrayList<Video> vl;
    RecyclerView lv;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    FloatingActionButton fabSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_signs);


        lv = findViewById(R.id.lv);

        vl = new ArrayList<Video>();

        lv.setLayoutManager(new LinearLayoutManager(KeywordSigns.this));
        adapter = new CustomAdapter(this, vl);
        lv.setAdapter(adapter);

        navigationView = findViewById(R.id.hamburgerMenu);
        fabSetting = findViewById(R.id.settingBtn);


        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String alright = "android.resource://sg.minds.keyword.signs/" + R.raw.alright_okay;
        String greetings = "android.resource://sg.minds.keyword.signs/" + R.raw.how_are_you;
        String please = "android.resource://sg.minds.keyword.signs/" + R.raw.please;
        String sorry = "android.resource://sg.minds.keyword.signs/" + R.raw.sorry;
        String thankyou = "android.resource://sg.minds.keyword.signs/" + R.raw.thank_you;
//        String play = "android.resource://sg.minds.keyword.signs/" + R.raw.play;


        Video one = new Video(1, "Alright", alright , "Fingertips of thumb and index finger of opened palm to touch together into a circle.");
        Video two = new Video(2, "Greetings - How Are You", greetings,  "How: Knuckles of palm-down bent hand touching, roll hands from inward to outward ending palm-up. \n You: Extended thumb on fist points at person addressed (culturally appropriate).");
        Video three = new Video(3, "Please", please, "Palm rubs on chest in circle.");
        Video four = new Video(4, "Sorry", sorry,  "Place closed fist, with thumb at side and move on chest in a circular motion.");
        Video five = new Video(5, "Thank you",  thankyou,"Move fingertips of open dominant hand, forward from chin.");
//        Video six = new Video(6, "Play", play, "Play hand sign");

        vl.add(one);
        vl.add(two);
        vl.add(three);
        vl.add(four);
        vl.add(five);
//        vl.add(six);

//        quickScroll();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.nav_favourite) {

                    startActivity(new Intent(KeywordSigns.this, Favourite.class));

                }

                else if (id == R.id.nav_home){

                    startActivity(new Intent(KeywordSigns.this, MainActivity.class));
                }

                else if (id == R.id.nav_game_quiz){

                    startActivity(new Intent(KeywordSigns.this, Quiz.class));
                }

                else if (id == R.id.nav_list_of_words){

                    startActivity(new Intent(KeywordSigns.this, KeywordSigns.class));

                }


                else if (id == R.id.share){

                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
//                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Keyword sign");
                        String shareMessage= "\nLet me recommend you this application keyword sign application to you.\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "Choose one"));

                    }

                    catch(Exception e) {

                        Toast.makeText(KeywordSigns.this,""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.my_drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return true;

            }
        });


        fabSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(KeywordSigns.this, Setting.class));

            }
        });


    }


    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Search here...");


        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //called when search is being clicked
                adapter.getFilter().filter(query);

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //called whenever user type something in the searchbar
                adapter.getFilter().filter(newText);

                return false;


            }


        });


        return true;


    }
}


//    private void quickScroll() {
//        View view = v;
//        quickScroll(view);
//
//    }
//
//
//    public void quickScroll(@NonNull View v){
//
//        String alphabet = (String) v.getTag();
//        //find the index of the separator row view
//
//        int index =0;
//        lv.setSelectionFromTop(index, 0);
//
//    }