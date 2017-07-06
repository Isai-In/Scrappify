package com.isaiin.scrappify.Login.View;

/**
 * Created by alejandro.acosta on 06/07/17.
 */

public interface LoginView {

    void goCreateAccount();
    void goChat();

    void enableInputs();
    void disableInputs();

    void showProgressBar();
    void hideProgressBar();

    void loginError(String error);
}
