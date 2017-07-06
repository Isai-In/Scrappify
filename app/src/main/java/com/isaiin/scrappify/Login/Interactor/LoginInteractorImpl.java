package com.isaiin.scrappify.Login.Interactor;

import com.isaiin.scrappify.Login.Presenter.LoginPresenter;
import com.isaiin.scrappify.Login.Repository.LoginRepository;
import com.isaiin.scrappify.Login.Repository.LoginRepositoryImpl;

/**
 * Created by alejandro.acosta on 06/07/17.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter loginPresenter;
    private LoginRepository loginRepository;

    public LoginInteractorImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
        loginRepository = new LoginRepositoryImpl(loginPresenter);
    }

    @Override
    public void signIn(String username, String password) {
        loginRepository.signIn(username, password);
    }
}
