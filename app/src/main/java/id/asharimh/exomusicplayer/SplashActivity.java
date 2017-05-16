package id.asharimh.exomusicplayer;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1500 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Typeface tf = Typeface.createFromAsset(getAssets(), BaseClass.LATO_LIGHT_PATH) ;
        TextView textViewLogo = (TextView) findViewById(R.id.textViewLogo) ;
        TextView textViewCopyright = (TextView) findViewById(R.id.textViewCopyright);

        textViewLogo.setTypeface(tf);
        textViewCopyright.setTypeface(tf);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class) ;
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH) ;

    }
}
