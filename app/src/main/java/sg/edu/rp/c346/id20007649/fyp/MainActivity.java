package sg.edu.rp.c346.id20007649.fyp;

import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton fabMic;
    ImageButton fabList;
    ImageButton fabFav;
//    public DrawerLayout drawerLayout;
//    public ActionBarDrawerToggle actionBarDrawerToggle;
//    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabList = findViewById(R.id.ListBtn);
        fabMic = findViewById(R.id.S2TBtn);
        fabFav = findViewById(R.id.FavBtn);
//        navigationView = findViewById(R.id.hamburgerMenu);
//
//
//        // drawer layout instance to toggle the menu icon to open
//        // drawer and back button to close drawer
//        drawerLayout = findViewById(R.id.my_drawer_layout);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
//
//        // pass the Open and Close toggle for the drawer layout listener
//        // to toggle the button
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//
//        // to make the Navigation drawer icon always appear on the action bar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fabList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, KeywordSigns.class);

                startActivity(intent);

                Toast.makeText(MainActivity.this, "Displaying the list of keyword signs...", Toast.LENGTH_SHORT).show();
            }
        });


        fabMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intents = new Intent(MainActivity.this, SpeechToText.class);

                startActivity(intents);

                Toast.makeText(MainActivity.this, "Going to speech to text...", Toast.LENGTH_LONG).show();
            }
        });


        fabFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intents = new Intent(MainActivity.this, Favourite.class);

                startActivity(intents);

                Toast.makeText(MainActivity.this, "Going to favourite list...", Toast.LENGTH_LONG).show();


            }
        });



//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                int id = item.getItemId();
//
//                if(id == R.id.nav_favourite) {
//
//                    startActivity(new Intent(MainActivity.this, Favourite.class));
//
//                }
//
//                else if (id == R.id.nav_home){
//
//                    startActivity(new Intent(MainActivity.this, MainActivity.class));
//                }
//
//                else if (id == R.id.nav_game_quiz){
//
//                    startActivity(new Intent(MainActivity.this, Quiz.class));
//                }
//
//                else if (id == R.id.nav_list_of_words){
//
//                    startActivity(new Intent(MainActivity.this, KeywordSigns.class));
//
//                }
//
//
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.my_drawer_layout);
//                drawer.closeDrawer(GravityCompat.START);
//
//                return true;
//
//            }
//        });


    }



//    // override the onOptionsItemSelected()
//    // function to implement
//    // the item click listener callback
//    // to open and close the navigation
//    // drawer when the icon is clicked
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }







}





