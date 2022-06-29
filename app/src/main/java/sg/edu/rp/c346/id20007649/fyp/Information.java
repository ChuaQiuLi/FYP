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

import com.google.android.material.navigation.NavigationView;

public class Information extends AppCompatActivity {


    TextView tvDescription;
    VideoView videos;
    Video data;
    MediaController mediaController;
    ImageButton favBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        tvDescription = findViewById(R.id.description);
        videos = findViewById(R.id.video);
        favBtn = findViewById(R.id.btn);



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





//        shareBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Uri contentUri = getContentUri();
//
//                String name = tvDescription.getText().toString();
//                Uri uri = Uri.parse(data.getVideo());
//
//
//                Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setType("video/*");
//                shareIntent.setType("text/plain");
//                shareIntent.putExtra(Intent.EXTRA_TEXT, name);
//                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
//                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
//                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                startActivity(Intent.createChooser(shareIntent, "Share Via"));
//
//
//            }
//        });



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




    }


    private boolean isLandScape() {

        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();

        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {

            return true;
        }

        return false;
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