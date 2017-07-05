package com.isaiin.scrappify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.isaiin.scrappify.Views.ChatActivity;
import com.isaiin.scrappify.Views.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goCreateAccount(View view){
        Intent i = new Intent(this, CreateAccountActivity.class);
        startActivity(i);
    }

    //this method is a test
    public void goChatView(View view){
        Intent i = new Intent(this, ChatActivity.class);
        startActivity(i);
    }
}
