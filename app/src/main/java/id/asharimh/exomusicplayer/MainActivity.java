package id.asharimh.exomusicplayer;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView homeNavigation, searchNavigation, inboxNavigation, playPauseButton ;
    ScrollView containerData ;
    FragmentManager fragmentManager ;
    FragmentTransaction fragmentTransaction ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseClass.PAGE_ACTIVE = "BERANDA" ;

        // kenalkan semua variabel ke layout
        homeNavigation = (ImageView) findViewById(R.id.homeNavigation);
        searchNavigation = (ImageView) findViewById(R.id.searchNavigation);
        inboxNavigation = (ImageView) findViewById(R.id.inboxNavigation);
        playPauseButton = (ImageView) findViewById(R.id.playPauseButton);
        containerData = (ScrollView) findViewById(R.id.containerData);

        Typeface tfMont = Typeface.createFromAsset(getAssets(), BaseClass.MONTSERRAT_BOLD_PATH) ;
        final TextView textViewPageName = (TextView) findViewById(R.id.textViewPageName);
        textViewPageName.setTypeface(tfMont);
        textViewPageName.setText(BaseClass.PAGE_ACTIVE);

        // nambahkan data ke fragment saat pertama kali load
        fragmentManager = getSupportFragmentManager() ;
        fragmentTransaction = fragmentManager.beginTransaction() ;
        final BerandaFragment bf = BerandaFragment.newInstance("data", "data") ;
        fragmentTransaction.add(R.id.containerData, bf) ;
        fragmentTransaction.commit() ;

        homeNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseClass.setPageActive("BERANDA");
                textViewPageName.setText(BaseClass.PAGE_ACTIVE);
                BerandaFragment bf2 = BerandaFragment.newInstance("data", "data") ;
                fragmentTransaction = fragmentManager.beginTransaction() ;
                fragmentTransaction.replace(R.id.containerData, bf2) ;
                fragmentTransaction.addToBackStack("") ;
                fragmentTransaction.commit() ;
            }
        });

        searchNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseClass.setPageActive("SEARCH");
                textViewPageName.setText(BaseClass.PAGE_ACTIVE);
                SearchFragment sf = SearchFragment.newInstance("data", "data") ;
                fragmentTransaction = fragmentManager.beginTransaction() ;
                fragmentTransaction.replace(R.id.containerData, sf, "SearchFragmentTag");
                fragmentTransaction.addToBackStack("") ;
                fragmentTransaction.commit() ;
            }
        });

        inboxNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseClass.setPageActive("DAERAH");
                textViewPageName.setText(BaseClass.PAGE_ACTIVE);
                DaerahFragment df = DaerahFragment.newInstance("data", "data") ;
                fragmentTransaction = fragmentManager.beginTransaction() ;
                fragmentTransaction.replace(R.id.containerData, df, "DaerahFragmentTag") ;
                fragmentTransaction.addToBackStack("") ;
                fragmentTransaction.commit() ;
            }
        });

        if (BaseClass.getPlayState() == "PAUSED"){
            playPauseButton.setImageResource(R.drawable.circleplay);
        }else{
            playPauseButton.setImageResource(R.drawable.circlepause);
        }

    }

    public void backFragment(View v){
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount() ;

        if (count == 0){
            super.onBackPressed();
        }else{
            getSupportFragmentManager().popBackStack();
        }
    }

    public void goToPlay(View v){
        Intent playIntent = new Intent(this, PlayerActivity.class) ;
        playIntent.putExtra(BaseClass.WILL_PLAY, "https://firebasestorage.googleapis.com/v0/b/ta-exo.appspot.com/o/Butet%20-%20Jessica%20N.m4a?alt=media&token=a888694e-3f01-4ce4-8f6f-b9c11f39113b") ;
        playIntent.putExtra(BaseClass.WILL_PLAY_TITLE, "Butet") ;
        playIntent.putExtra(BaseClass.WILL_PLAY_DAERAH, "Sumatera Utara") ;
        startActivity(playIntent);
    }

    public void togglePlay(View v){
        if (BaseClass.getPlayState() == "PAUSED"){
            playPauseButton.setImageResource(R.drawable.circlepause);
        }else{
            playPauseButton.setImageResource(R.drawable.circleplay);
        }
        BaseClass.togglePlayState();

        Toast.makeText(getApplicationContext(), "Pencet play", Toast.LENGTH_SHORT).show();
    }

    public void toggleImagePlay(){
        if (BaseClass.getPlayState() == "PAUSED"){
            playPauseButton.setImageResource(R.drawable.circlepause);
        }else{
            playPauseButton.setImageResource(R.drawable.circleplay);
        }
    }
}
