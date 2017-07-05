package com.isaiin.scrappify.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.isaiin.scrappify.Adapter.MessagingAdapterRecyclerView;
import com.isaiin.scrappify.Model.Message;
import com.isaiin.scrappify.Model.MessageWhitMetaData;
import com.isaiin.scrappify.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        showToolbar("Title", true);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.messages_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        MessagingAdapterRecyclerView adapterRecyclerView = new MessagingAdapterRecyclerView(buildMessages() ,this);
        recyclerView.setAdapter(adapterRecyclerView);
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    public ArrayList<Message> buildMessages(){
        ArrayList<Message> list = new ArrayList<>();
        Message onlyText = new Message("Este es un mensaje de solo texto", null, Message.Type.ONLY_TEXT);
        MessageWhitMetaData textWitMetaData = new MessageWhitMetaData("Este mensaje lleva texto y metadata", null, Message.Type.TEXT_WHIT_METADATA,R.drawable.dummy,"Title dummy", "Learn from zero to create the future of the web. Courses on programming, design, marketing, web development, Frontend, Backend, mobile dev, UX, usability.");
        MessageWhitMetaData metaDataWhitoutText = new MessageWhitMetaData(null, null, Message.Type.ONLY_METADATA,R.drawable.dummy,"Title dummy", "Learn from zero to create the future of the web. Courses on programming, design, marketing, web development, Frontend, Backend, mobile dev, UX, usability.");
        list.add(onlyText);
        list.add(textWitMetaData);
        list.add(metaDataWhitoutText);
        return list;
    }
}
