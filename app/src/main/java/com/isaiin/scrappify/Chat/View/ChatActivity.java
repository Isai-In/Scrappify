package com.isaiin.scrappify.Chat.View;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.isaiin.scrappify.Adapter.MessagingAdapterRecyclerView;
import com.isaiin.scrappify.Chat.Presenter.ChatPresenter;
import com.isaiin.scrappify.Chat.Presenter.ChatPresenterImpl;
import com.isaiin.scrappify.Model.Message;
import com.isaiin.scrappify.Model.MessageWhitMetaData;
import com.isaiin.scrappify.R;

import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity implements ChatView{

    private ArrayList<Message> messages;
    private EditText message;
    private FloatingActionButton send;
    private ChatPresenter chatPresenter;
    MessagingAdapterRecyclerView adapterRecyclerView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        showToolbar("Messaging");
        setRecyclerView();
        message = (EditText) findViewById(R.id.message_field);
        send = (FloatingActionButton) findViewById(R.id.send);
        chatPresenter = new ChatPresenterImpl(this);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.sendMessage(message.getText().toString(), getApplicationContext());
            }
        });
    }


    @Override
    public void updateMessagesList(Message message) {
        //Update the recyclerview
        adapterRecyclerView.addItem(message);
        recyclerView.smoothScrollToPosition(adapterRecyclerView.getItemCount());
    }

    @Override
    public void updateViews() {
        message.setText("");
        message.clearFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(message.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.messages_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        messages = new ArrayList<>();
        adapterRecyclerView = new MessagingAdapterRecyclerView(messages ,this);
        recyclerView.setAdapter(adapterRecyclerView);
    }

}
