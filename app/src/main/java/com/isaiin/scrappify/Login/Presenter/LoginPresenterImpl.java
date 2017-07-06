package com.isaiin.scrappify.Login.Presenter;

import com.isaiin.scrappify.Login.Interactor.LoginInteractor;
import com.isaiin.scrappify.Login.Interactor.LoginInteractorImpl;
import com.isaiin.scrappify.Login.View.LoginView;

/**
 * Created by alejandro.acosta on 06/07/17.
 */

public class LoginPresenterImpl implements LoginPresenter{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password) {
        loginView.disableInputs();
        loginView.showProgressBar();
        loginInteractor.signIn(username, password);
    }

    @Override
    public void loginSuccess() {
        loginView.goChat();
        loginView.hideProgressBar();
    }

    @Override
    public void loginError(String error) {
        loginView.enableInputs();
        loginView.hideProgressBar();
        loginView.loginError(error);
    }
}
