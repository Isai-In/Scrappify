package com.isaiin.scrappify.Chat.View;

import com.isaiin.scrappify.Model.Message;

/**
 * Created by alejandro.acosta on 06/07/17.
 */

public interface ChatView {

    void showToolbar(String title);
    void updateMessagesList(Message message);
    void updateViews();
    void setRecyclerView();
    void showError();

}
