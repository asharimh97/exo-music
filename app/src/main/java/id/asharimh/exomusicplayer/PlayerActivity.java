package id.asharimh.exomusicplayer;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity {

    ImageView imageViewPlayPause, imageViewBackButton, imageViewNext, imageViewPrev ;
    MediaPlayer mediaPlayer ;
    ProgressBar progressBar;
    String url, musicTitle, musicDaerah ;
    TextView songTitle, songDaerah ;
    MusicData dataMusik ;
    int lastPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        final Intent intent = getIntent() ;
        final Intent newIntent = new Intent(this, PlayerActivity.class) ;
        url = intent.getStringExtra(BaseClass.WILL_PLAY) ;
        musicDaerah = intent.getStringExtra(BaseClass.WILL_PLAY_DAERAH) ;
        musicTitle = intent.getStringExtra(BaseClass.WILL_PLAY_TITLE) ;
        BaseClass.idx = intent.getIntExtra(BaseClass.WILL_PLAY_IDX, 0) ;

        dataMusik = BaseClass.listPlaying.get(BaseClass.idx) ;
        System.out.println(dataMusik);

        BaseClass.CURRENT_MEDIA = url ;
        BaseClass.CURRENT_MEDIA_DAERAH = musicDaerah ;
        BaseClass.CURRENT_MEDIA_TITLE = musicTitle ;

        //playButton = (Button) findViewById(R.id.imageViewPlayPause);
        imageViewPlayPause = (ImageView) findViewById(R.id.imageViewPlayPause);
        imageViewBackButton = (ImageView) findViewById(R.id.imageViewBackButton);
        imageViewNext = (ImageView) findViewById(R.id.imageViewActionNext);
        imageViewPrev = (ImageView) findViewById(R.id.imageViewActionPrevious);

        songTitle = (TextView) findViewById(R.id.textViewSongDescriptionTitle);
        songDaerah = (TextView) findViewById(R.id.textViewSongDescriptionDaerah);
        progressBar = (ProgressBar) findViewById(R.id.progressBarDuration);

        songTitle.setText(musicTitle);
        songDaerah.setText(musicDaerah);

        if (BaseClass.idx == 0){
            imageViewPrev.setEnabled(false);
        }else{
            imageViewPrev.setEnabled(true);
        }

        if (BaseClass.idx == BaseClass.listPlaying.size()-1){
            imageViewNext.setEnabled(false);
        }else{
            imageViewNext.setEnabled(true);
        }

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
                            int duration = mediaPlayer.getDuration() ;
                            progressBar.setMax(duration);
                            System.out.println(duration);
//                            progressBar.postDelayed(onEverySecond)
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

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int next = intent.getIntExtra(BaseClass.WILL_PLAY_IDX,0) + 1 ;
                if (next < BaseClass.maxIdx) {
                    MusicData temp = BaseClass.listPlaying.get(next);
                    String nextUrl = temp.linkLagu;
                    String nextTitle = temp.judul;
                    String nextDaerah = temp.daerah;

                    newIntent.putExtra(BaseClass.WILL_PLAY, nextUrl);
                    newIntent.putExtra(BaseClass.WILL_PLAY_DAERAH, nextDaerah);
                    newIntent.putExtra(BaseClass.WILL_PLAY_TITLE, nextTitle);
                    newIntent.putExtra(BaseClass.WILL_PLAY_IDX, next);

                    startActivity(newIntent);
                }
            }
        });

        imageViewPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int prev = intent.getIntExtra(BaseClass.WILL_PLAY_IDX, 0) - 1 ;
                if (prev >=0 ) {
                    MusicData temp = BaseClass.listPlaying.get(prev);
                    String prevUrl = temp.linkLagu;
                    String prevDaerah = temp.daerah;
                    String prevTitle = temp.judul;

                    newIntent.putExtra(BaseClass.WILL_PLAY, prevUrl);
                    newIntent.putExtra(BaseClass.WILL_PLAY_DAERAH, prevDaerah);
                    newIntent.putExtra(BaseClass.WILL_PLAY_TITLE, prevTitle);
                    newIntent.putExtra(BaseClass.WILL_PLAY_IDX, prev);

                    startActivity(newIntent);
                }
            }
        });
    }


//    public void onPrepared(MediaPlayer arg){
//        int duration = mediaPlayer.getDuration();
//        progressBar.setMax(duration);
//
//    }
}
