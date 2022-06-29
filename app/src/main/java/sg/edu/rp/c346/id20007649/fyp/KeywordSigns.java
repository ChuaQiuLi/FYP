package sg.edu.rp.c346.id20007649.fyp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

public class KeywordSigns extends AppCompatActivity  {

    CustomAdapter adapter;
    ArrayList<Video> vl;
    RecyclerView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_signs);


        lv = findViewById(R.id.lv);
        vl = new ArrayList<Video>();

        lv.setLayoutManager(new LinearLayoutManager(KeywordSigns.this));
        adapter = new CustomAdapter(this, vl);
        lv.setAdapter(adapter);


        String alright = "android.resource://sg.edu.rp.c346.id20007649.fyp/" + R.raw.alright_okay;
        String greetings = "android.resource://sg.edu.rp.c346.id20007649.fyp/" + R.raw.how_are_you;
        String please = "android.resource://sg.edu.rp.c346.id20007649.fyp/" + R.raw.please;
        String sorry = "android.resource://sg.edu.rp.c346.id20007649.fyp/" + R.raw.sorry;
        String thankyou = "android.resource://sg.edu.rp.c346.id20007649.fyp/" + R.raw.thank_you;
//        String play = "android.resource://sg.edu.rp.c346.id20007649.fyp/" + R.raw.play;


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

}






















