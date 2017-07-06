package com.isaiin.scrappify.Login.Repository;

import com.isaiin.scrappify.Login.Presenter.LoginPresenter;

/**
 * Created by alejandro.acosta on 06/07/17.
 */

public class LoginRepositoryImpl implements LoginRepository{

    private LoginPresenter loginPresenter;

    public LoginRepositoryImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void signIn(String username, String password) {
        //Connection whit webservice
        boolean succes = true;
        if (succes){
            loginPresenter.loginSuccess();
        }else {
            loginPresenter.loginError("Error");
        }
    }
}
