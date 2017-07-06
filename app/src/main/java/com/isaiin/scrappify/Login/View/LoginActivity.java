package com.isaiin.scrappify.Login.View;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.isaiin.scrappify.Login.Presenter.LoginPresenter;
import com.isaiin.scrappify.Login.Presenter.LoginPresenterImpl;
import com.isaiin.scrappify.R;
import com.isaiin.scrappify.Chat.View.ChatActivity;
import com.isaiin.scrappify.CreateAccount.View.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private TextInputEditText username, password;
    private Button login;
    private ProgressBar progressBar;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginPresenter = new LoginPresenterImpl(this);
        hideProgressBar();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.signIn(username.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void goCreateAccount() {
        Intent i = new Intent(this, CreateAccountActivity.class);
        startActivity(i);
    }

    @Override
    public void goChat() {
        Intent i = new Intent(this, ChatActivity.class);
        startActivity(i);
    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, getString(R.string.login_faliure) + error, Toast.LENGTH_SHORT).show();
    }
}
