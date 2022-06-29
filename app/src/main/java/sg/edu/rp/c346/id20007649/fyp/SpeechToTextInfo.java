package sg.edu.rp.c346.id20007649.fyp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class SpeechToTextInfo extends AppCompatActivity {

    TextView tvDescription;
    VideoView videos;
    MediaController mediaController;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ImageButton favBtn;
    ArrayList<Video> newSpeechToTextArray;
    TextView tvSpeechToText;
    FloatingActionButton fabSetting, textToSpeechBtn, speechToTextBtn;
    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text_info);


        tvDescription = findViewById(R.id.description);
        videos = findViewById(R.id.video);
        favBtn = findViewById(R.id.btn);
        speechToTextBtn = findViewById(R.id.speechToTextBtn);
        tvSpeechToText = findViewById(R.id.tvSpeechToText);
        textToSpeechBtn = findViewById(R.id.textToSpeechBtn);
        fabSetting = findViewById(R.id.settingBtn);
        navigationView = findViewById(R.id.hamburgerMenu);
        newSpeechToTextArray = new ArrayList<Video>();
        mediaController = new MediaController(this);

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


        newSpeechToTextArray.add(one);
        newSpeechToTextArray.add(two);
        newSpeechToTextArray.add(three);
        newSpeechToTextArray.add(four);
        newSpeechToTextArray.add(five);
//        newSpeechToTextArray.add(six);


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


        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status != TextToSpeech.ERROR ){
                    textToSpeech.setLanguage(Locale.UK);


                }
            }
        });




        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String description = i.getStringExtra("description");
        String video = i.getStringExtra("video");


        Uri uriResult = Uri.parse(video);
        tvDescription.setText(description);
        tvSpeechToText.setText(name);


        videos.setVideoURI(uriResult);
        videos.setMediaController(mediaController);
        mediaController.setAnchorView(videos);
        videos.start();


        textToSpeechBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textToSpeech.speak(tvDescription.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.nav_favourite) {

                    startActivity(new Intent(SpeechToTextInfo.this, Favourite.class));

                }

                else if (id == R.id.nav_home){

                    startActivity(new Intent(SpeechToTextInfo.this, MainActivity.class));
                }

                else if (id == R.id.nav_game_quiz){

                    startActivity(new Intent(SpeechToTextInfo.this, Quiz.class));
                }

                else if (id == R.id.nav_list_of_words){

                    startActivity(new Intent(SpeechToTextInfo.this, KeywordSigns.class));

                }


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.my_drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return true;

            }
        });


        fabSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SpeechToTextInfo.this, Setting.class));

            }
        });


        speechToTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Keyword sign that you want to search for...");
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);


            }

        });


        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FavDB favDB = new FavDB(SpeechToTextInfo.this);

                long result = favDB.insertFav(name, video, description);


                if (result != -1) {

                    Toast.makeText(SpeechToTextInfo.this, "Favourite saved", Toast.LENGTH_SHORT).show();
                    favBtn.setImageResource(R.drawable.ic_favorite_red_24dp);

                }


                else{

                    Toast.makeText(SpeechToTextInfo.this, "Already favorite this keyword sign ", Toast.LENGTH_SHORT).show();


                }
            }
        });


    }




    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                tvSpeechToText.setText(Objects.requireNonNull(result).get(0));
                for (int i =0; i < newSpeechToTextArray.size(); i++){
                    if (Objects.requireNonNull(result).get(0).equalsIgnoreCase(newSpeechToTextArray.get(i).getName())) {

                        Intent intents = new Intent(SpeechToTextInfo.this, SpeechToTextInfo.class);
                        intents.putExtra("name", newSpeechToTextArray.get(i).getName());
                        intents.putExtra("description", newSpeechToTextArray.get(i).getDescription());
                        intents.putExtra("video", newSpeechToTextArray.get(i).getVideo());
                        startActivity(intents);
                        Toast.makeText(SpeechToTextInfo.this, "Going to the search keyword sign...", Toast.LENGTH_SHORT).show();


                    }

                    else if (!Objects.requireNonNull(result).get(0).equalsIgnoreCase(newSpeechToTextArray.get(i).getName())){

                        Toast.makeText(SpeechToTextInfo.this, "Keyword sign not found. Please try again... ", Toast.LENGTH_SHORT).show();

                    }


                }

            }


        }


        super.onActivityResult(requestCode, resultCode, data);


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