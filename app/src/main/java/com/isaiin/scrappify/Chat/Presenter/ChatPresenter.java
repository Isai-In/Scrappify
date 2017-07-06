package com.isaiin.scrappify.Chat.Presenter;

import android.content.Context;

import com.isaiin.scrappify.Model.Message;

/**
 * Created by alejandro.acosta on 06/07/17.
 */

public interface ChatPresenter {
    void sendMessage(String message, Context context);
    void messageError(String error);
    void messageSuccess(Message message);
}
