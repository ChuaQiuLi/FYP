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

public class Favourite extends AppCompatActivity {

    RecyclerView favList;
    public FavAdapter favAdapter;
    public ArrayList<Video> fl;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    FloatingActionButton fabSetting;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        fl = new ArrayList<Video>();
        favList = findViewById(R.id.favList);
        fabSetting = findViewById(R.id.settingBtn);
        navigationView = findViewById(R.id.hamburgerMenu);


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


        FavDB favDB = new FavDB(Favourite.this);
        fl.addAll(favDB.getAllKeywordSign());
        favList.setLayoutManager(new LinearLayoutManager(Favourite.this));
        favAdapter = new FavAdapter(this, fl);
        favList.setAdapter(favAdapter);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.nav_favourite) {

                    startActivity(new Intent(Favourite.this, Favourite.class));

                }

                else if (id == R.id.nav_home){

                    startActivity(new Intent(Favourite.this, MainActivity.class));
                }

                else if (id == R.id.nav_game_quiz){

                    startActivity(new Intent(Favourite.this, Quiz.class));
                }

                else if (id == R.id.nav_list_of_words){

                    startActivity(new Intent(Favourite.this, KeywordSigns.class));

                }


                else if (id == R.id.share){

                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
//                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Keyword sign");
                        String shareMessage= "\nLet me recommend you this keyword sign application to you!\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "Choose one"));

                    }

                    catch(Exception e) {

                        Toast.makeText(Favourite.this,""+e.getMessage(), Toast.LENGTH_SHORT).show();

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

                startActivity(new Intent(Favourite.this, Setting.class));

            }
        });


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
                favAdapter.getFilter().filter(query);

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //called whenever user type something in the searchbar
                favAdapter.getFilter().filter(newText);

                return false;

            }

        });

        return true;


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

}