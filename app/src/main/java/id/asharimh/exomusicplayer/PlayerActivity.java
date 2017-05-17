package id.asharimh.exomusicplayer;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity {

    ImageView imageViewPlayPause, imageViewBackButton ;
    MediaPlayer mediaPlayer ;
    String url, musicTitle, musicDaerah ;
    TextView songTitle, songDaerah ;
    int lastPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Intent intent = getIntent() ;
        url = intent.getStringExtra(BaseClass.WILL_PLAY) ;
        musicDaerah = intent.getStringExtra(BaseClass.WILL_PLAY_DAERAH) ;
        musicTitle = intent.getStringExtra(BaseClass.WILL_PLAY_TITLE) ;

        BaseClass.CURRENT_MEDIA = url ;
        BaseClass.CURRENT_MEDIA_DAERAH = musicDaerah ;
        BaseClass.CURRENT_MEDIA_TITLE = musicTitle ;

        //playButton = (Button) findViewById(R.id.imageViewPlayPause);
        imageViewPlayPause = (ImageView) findViewById(R.id.imageViewPlayPause);
        imageViewBackButton = (ImageView) findViewById(R.id.imageViewBackButton);
        songTitle = (TextView) findViewById(R.id.textViewSongDescriptionTitle);
        songDaerah = (TextView) findViewById(R.id.textViewSongDescriptionDaerah);

        songTitle.setText(musicTitle);
        songDaerah.setText(musicDaerah);

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
