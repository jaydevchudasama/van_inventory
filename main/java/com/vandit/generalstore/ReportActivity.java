package com.vandit.generalstore;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Toast;

public class ReportActivity extends AppCompatActivity
{

    AppCompatImageView back;
    AppCompatImageView Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        back = findViewById(R.id.imgBack);
        Logout = findViewById(R.id.imgLogout);

        View.OnClickListener clickListener=new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                switch (view.getId())
                {
                    case R.id.imgBack:
                            onBackPressed();
                    break;

                    case R.id.imgLogout:
                        startActivity(new Intent(ReportActivity.this,LoginActivity.class));
                        finish();
                        Toast.makeText(ReportActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    break;
                }

            }
        };
        back.setOnClickListener(clickListener);
        Logout.setOnClickListener(clickListener);

    }
    
}
