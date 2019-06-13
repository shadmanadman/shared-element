package org.bavand.sharedElement.ui.Splash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.widget.ImageView;

import org.bavand.R;
import org.bavand.sharedElement.ui.login.LoginActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

public class SplashActivity extends AppCompatActivity {

    /*
    make a thread to end splash activity after 5 sec
     */
    private ImageView imageViewLogo;
    Thread splash = new Thread() {
        @Override
        public void run() {
            try {
                Looper.prepare();
                sleep(5000);
                runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        /*
                        make a scene transition for image view and add transition name. you must enter transition name for image view in xml
                        file.
                         */
                        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this, imageViewLogo, "logo");
                        startActivity(i, optionsCompat.toBundle());
                        getWindow().setExitTransition(null);
                    }
                });

            } catch (Exception e) {

            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LoginActivity.changeStatusBarColor(this);
        imageViewLogo = findViewById(R.id.imageViewLogo);
        splash.start();
    }


    /*
    finish activity after transition commit. if you finish activity just after
    strart activity the activity will blinking.
     */
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
