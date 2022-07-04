package sg.edu.rp.c346.id20007649.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Information extends AppCompatActivity {


    TextView tvDescription;
    VideoView videos;
    Video data;
    MediaController mediaController;
    ImageButton favBtn;
    FloatingActionButton fabSetting;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        tvDescription = findViewById(R.id.description);
        videos = findViewById(R.id.video);
        favBtn = findViewById(R.id.btn);
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



        mediaController = new MediaController(this);

        Intent i = getIntent();
        data = (Video) i.getSerializableExtra("data");

        Uri uri = Uri.parse(data.getVideo());
        tvDescription.setText(data.getDescription());


        videos.setVideoURI(uri);
        videos.setMediaController(mediaController);
        mediaController.setAnchorView(videos);
        videos.start();


        if (isLandScape()) {
            mediaController = new MediaController(this);


        }



        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FavDB favDB = new FavDB(Information.this);

                long result = favDB.insertFav(data.getName(), data.getVideo(), data.getDescription());



                if (result != -1) {
                    Toast.makeText(Information.this, "Favourite saved", Toast.LENGTH_SHORT).show();
                    favBtn.setImageResource(R.drawable.ic_favorite_red_24dp);


                }

                else{
                    Toast.makeText(Information.this, "Already favorite this keyword sign ", Toast.LENGTH_SHORT).show();



                }

            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id == R.id.nav_favourite) {

                    startActivity(new Intent(Information.this, Favourite.class));

                }

                else if (id == R.id.nav_home){

                    startActivity(new Intent(Information.this, MainActivity.class));
                }

                else if (id == R.id.nav_game_quiz){

                    startActivity(new Intent(Information.this, Quiz.class));
                }

                else if (id == R.id.nav_list_of_words){

                    startActivity(new Intent(Information.this, KeywordSigns.class));

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

                        Toast.makeText(Information.this,""+e.getMessage(), Toast.LENGTH_SHORT).show();

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

                startActivity(new Intent(Information.this, Setting.class));

            }
        });




    }


    private boolean isLandScape() {

        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();

        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {

            return true;
        }

        return false;
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










//    private void showToast(String message) {
//
//        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
//    }



//    private Uri getContentUri() {
//
//
////        Bitmap bitmap = null;
////
////        BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
////        bitmap = bitmapDrawable.getBitmap();
//
//        Uri contentUri = null;
//
//        File videoFolder = new File(getCacheDir(), "videos");
//
//       InputStream inputStream = (InputStream) getResources().openRawResource(R.raw.careful); // equivalent to R.raw.yoursound
//
//
//
//        try{
//
//
//            videoFolder.mkdirs();
//            File file = new File(videoFolder, "shared_video.mp4");
//            FileOutputStream stream = new FileOutputStream(file);
//
//            byte[] buffer = new byte[1024];
//            int read;
//            while((read = inputStream.read(buffer)) != -1) stream.write(buffer, 0, read);
//
//            stream.flush();
//            stream.close();
//            contentUri = FileProvider.getUriForFile(this, "sg.edu.rp.c346.id20007649.fyp.instrumentlist.fileprovider", file);
//
//        }
//
//        catch (Exception e){
//
//            showToast(""+e.getMessage());
//
//
//        }
//
//
//        return contentUri;
//
//
//    }





}