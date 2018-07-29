package com.vandit.generalstore;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    private AppCompatEditText username;
    private AppCompatEditText password;
    private AppCompatButton login;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.textUsername);
        password = findViewById(R.id.textPassword);
        login  = findViewById(R.id.buttonLogin);


        View.OnClickListener clickListener=new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.buttonLogin:

                        String uname=username.getText().toString().trim();
                        String upassword=password.getText().toString().trim();

                        if (TextUtils.isEmpty(uname))
                        {
                            username.setError("Enter Username");
                            return;
                        }
                        if (TextUtils.isEmpty(upassword))
                        {
                            password.setError("Enter Password");
                            return;
                        }

                        if (!username.getText().toString().equals("Admin"))
                        {
                            username.setError("Enter Valid Username");
                            return;
                        }
                        if (!password.getText().toString().equals("0000"))
                        {
                            password.setError("Enter Valid Password");
                            return;
                        }
                        else {
                            Intent dashboardIntent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(dashboardIntent);
                            finish();
                        }
                    break;
                }
            }
        };
        login.setOnClickListener(clickListener);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }
}
