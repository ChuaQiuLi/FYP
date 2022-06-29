package sg.edu.rp.c346.id20007649.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class Information2 extends AppCompatActivity {

    TextView tvDescription;
    VideoView videos;
    Video datas;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information2);

        tvDescription = findViewById(R.id.keyWordSign);
        videos = findViewById(R.id.favVideo);
        mediaController = new MediaController(this);


        Intent i = getIntent();

        datas = (Video) i.getSerializableExtra("datas");


            Uri uriResult = Uri.parse(datas.getVideo());
            tvDescription.setText(datas.getDescription());

            videos.setVideoURI(uriResult);
            videos.setMediaController(mediaController);
            mediaController.setAnchorView(videos);
            videos.start();


        }
    }
