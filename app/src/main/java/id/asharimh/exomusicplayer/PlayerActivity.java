package id.asharimh.exomusicplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity {

    ImageView imageViewPlayPause, imageViewBackButton ;
    MediaPlayer mediaPlayer ;
    String url = "http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3" ;
    int lastPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //playButton = (Button) findViewById(R.id.imageViewPlayPause);
        imageViewPlayPause = (ImageView) findViewById(R.id.imageViewPlayPause);
        imageViewBackButton = (ImageView) findViewById(R.id.imageViewBackButton);

        mediaPlayer = new MediaPlayer() ;
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        if (BaseClass.getPlayState() == "PAUSED"){
            imageViewPlayPause.setImageResource(R.drawable.playbutton);
        }else{
            imageViewPlayPause.setImageResource(R.drawable.pausebutton);
        }

        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerActivity.super.onBackPressed();
            }
        });

        imageViewPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseClass.getPlayState() == "PAUSED"){
                    if (lastPosition != -1){
                        mediaPlayer.seekTo(lastPosition);
                        mediaPlayer.start();
                    }else{
                        try {
                            mediaPlayer.setDataSource(url);
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                    }
                    imageViewPlayPause.setImageResource(R.drawable.pausebutton);
                }else{
                    lastPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                    imageViewPlayPause.setImageResource(R.drawable.playbutton);
                }
                BaseClass.togglePlayState();
                Log.d("STATE", BaseClass.getPlayState()) ;
            }
        });
    }
}
