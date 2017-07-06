package com.isaiin.scrappify.Chat.Interactor;

import android.content.Context;
import android.util.Log;

import com.isaiin.scrappify.Chat.Presenter.ChatPresenter;
import com.isaiin.scrappify.Chat.Repository.ChatRepository;
import com.isaiin.scrappify.Chat.Repository.ChatRepositoryImpl;
import com.isaiin.scrappify.Model.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alejandro.acosta on 06/07/17.
 */

public class ChatInteractorImpl implements ChatInteractor {

    private ChatPresenter presenter;
    private ChatRepository repository;

    public ChatInteractorImpl(ChatPresenter presenter) {
        this.presenter = presenter;
        repository = new ChatRepositoryImpl(presenter);
    }

    @Override
    public void sendMessage(String message, Context context) {
        // if the message contains url go to repository layer, else, send a plain text message
        ArrayList<String> links = pullLinks(message);
        if (links.size() > 0){
            repository.getMetadata(message, links.get(0), context);
        }else {
            presenter.messageSuccess(new Message(
                    message,
                    new Date(),
                    Message.Type.ONLY_TEXT
            ));
        }
    }

    private ArrayList<String> pullLinks(String text) {
        String[] strings = text.split(" ");
        ArrayList<String> links = new ArrayList();
        String regex = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";
        Pattern p = Pattern.compile(regex);
        for (String w: strings) {
            Matcher m = p.matcher(w);
            while(m.find()) {
                String urlStr = m.group();
                if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
                    urlStr = urlStr.substring(1, urlStr.length() - 1);
                }
                links.add(urlStr);
                Log.d("SCRAPPIFY", "URL FOUND: "+urlStr);
            }
        }
        return links;
    }


}
