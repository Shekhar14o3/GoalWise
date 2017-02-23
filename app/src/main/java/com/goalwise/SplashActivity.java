package com.goalwise;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView mTextSplash;
    View sharedView;
    String transitionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mTextSplash = (TextView) findViewById(R.id.text_appName);
        mTextSplash.setText("GoalWise");

        sharedView = mTextSplash;
        transitionName = getString(R.string.app_name);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, sharedView, transitionName);
                    startActivity(intent, transitionActivityOptions.toBundle());
                } else {
                    startActivity(intent);
                }

            }
        }, 500);
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
