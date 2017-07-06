package com.isaiin.scrappify.Chat.Presenter;

import android.content.Context;

import com.isaiin.scrappify.Chat.Interactor.ChatInteractor;
import com.isaiin.scrappify.Chat.Interactor.ChatInteractorImpl;
import com.isaiin.scrappify.Chat.View.ChatView;
import com.isaiin.scrappify.Model.Message;

/**
 * Created by alejandro.acosta on 06/07/17.
 */

public class ChatPresenterImpl implements ChatPresenter {

    private ChatView chatView;
    private ChatInteractor chatInteractor;

    public ChatPresenterImpl(ChatView chatView) {
        this.chatView = chatView;
        chatInteractor = new ChatInteractorImpl(this);
    }

    @Override
    public void sendMessage(String message, Context context) {
        chatInteractor.sendMessage(message, context);
        chatView.updateViews();
    }

    @Override
    public void messageError(String error) {
        chatView.showError();//need String
    }

    @Override
    public void messageSuccess(Message message) {
        chatView.updateMessagesList(message);
    }
}
