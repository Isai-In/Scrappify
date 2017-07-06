package com.isaiin.scrappify.Login.Presenter;

/**
 * Created by alejandro.acosta on 06/07/17.
 */

public interface LoginPresenter {

    void signIn(String username, String password);// Interactor
    void loginSuccess();
    void loginError(String error);
}
