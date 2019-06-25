package romain.com.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import romain.com.recycleme.utils.ActivityUtils;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadApplication();
            }
        }, 5000);
    }

    private void loadApplication(){
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        ActivityUtils.launchActivity(this, intent);
    }
}
