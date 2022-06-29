package sg.edu.rp.c346.id20007649.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Favourite extends AppCompatActivity {

    RecyclerView favList;
    public FavAdapter favAdapter;
    public ArrayList<Video> fl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        fl = new ArrayList<Video>();
        favList = findViewById(R.id.favList);



        FavDB favDB = new FavDB(Favourite.this);
        fl.addAll(favDB.getAllKeywordSign());
        favList.setLayoutManager(new LinearLayoutManager(Favourite.this));
        favAdapter = new FavAdapter(this, fl);
        favList.setAdapter(favAdapter);



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

}