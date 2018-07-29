package com.vandit.generalstore;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity
{
    public static final int sec=2000;
    private boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                finish();
                if (!flag)
                {
                    Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        },sec);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        flag=true;
    }
}

