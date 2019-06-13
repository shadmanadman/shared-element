package org.bavand.sharedElement.ui.login;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import org.bavand.R;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    private int time_interval = 2000;
    private long oldCurrentTimeMillis;
    private CardView cardViewLogin;
    private ImageView imageViewHalfCircleLeft, imageViewHalfCircleRight;

    /////////////////////////////////////
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        changeStatusBarColor(this);
        initView();
    }

    private void initView() {
        cardViewLogin = findViewById(R.id.cardViewLogin);
        imageViewHalfCircleLeft = findViewById(R.id.halfCircleLeft);
        imageViewHalfCircleRight = findViewById(R.id.halfCircleRight);
        /*
        add animation to components with animation that i put in anim folder
         */
        imageViewHalfCircleLeft.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_text));
        imageViewHalfCircleRight.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_text2));
        cardViewLogin.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anticipateovershoot_interpolator1));
    }

    /*
    back press must be tow time pressed to exite
     */
    @Override
    public void onBackPressed() {
        if (oldCurrentTimeMillis + time_interval > System.currentTimeMillis()) {

            super.onBackPressed();
            return;
        } else {
            onFirstBackPressed();

        }
        oldCurrentTimeMillis = System.currentTimeMillis();
    }


    public void onFirstBackPressed() {
        Toast.makeText(getBaseContext(), "Just one more time to exite", Toast.LENGTH_SHORT).show();
    }

    /**
     * Making notification bar transparent
     */
    public static void changeStatusBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

}
