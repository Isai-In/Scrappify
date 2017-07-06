package com.isaiin.scrappify.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.isaiin.scrappify.Model.Message;
import com.isaiin.scrappify.Model.MessageWhitMetaData;
import com.isaiin.scrappify.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by alejandro.acosta on 05/07/17.
 */

public class MessagingAdapterRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Message> messages;
    private Activity activity;

    public MessagingAdapterRecyclerView(ArrayList<Message> messages, Activity activity) {
        this.messages = messages;
        this.activity = activity;
    }

    public class TextViewHolder extends RecyclerView.ViewHolder{
        private TextView message;
        private TextView hour;
        public TextViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message);
            hour = (TextView) itemView.findViewById(R.id.hour);
        }
    }

    public class TextWhitMetaDataViewHolder extends RecyclerView.ViewHolder{
        private TextView message;
        private TextView hour;
        private ImageView image;
        private TextView title;
        private TextView description;
        public TextWhitMetaDataViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message);
            hour = (TextView) itemView.findViewById(R.id.hour);
            image = (ImageView) itemView.findViewById(R.id.message_image);
            title = (TextView) itemView.findViewById(R.id.message_title);
            description = (TextView) itemView.findViewById(R.id.message_description);
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (messages.get(position).getType()){
            case ONLY_TEXT:
                return 0;
            case ONLY_METADATA:
                return 1;
            case TEXT_WHIT_METADATA:
                return 2;
            default:
                return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType){
            case 0:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_message_item, parent, false);
                return new TextViewHolder(v);
            case 1:
            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_message_item_whit_metadata, parent, false);
                return new TextWhitMetaDataViewHolder(v);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        switch (holder.getItemViewType()){
            case 0:
                TextViewHolder textViewHolder = (TextViewHolder)holder;
                textViewHolder.message.setText(message.getBody());
                textViewHolder.hour.setText("15:30 pm");
                break;
            case 1:
                TextWhitMetaDataViewHolder textWhitMetaDataViewHolder = (TextWhitMetaDataViewHolder)holder;
                textWhitMetaDataViewHolder.message.setVisibility(View.GONE);
                Picasso.with(activity.getApplicationContext()).load(((MessageWhitMetaData)message).getImage()).into(textWhitMetaDataViewHolder.image);
                textWhitMetaDataViewHolder.title.setText(((MessageWhitMetaData)message).getTitle());
                textWhitMetaDataViewHolder.description.setText(((MessageWhitMetaData)message).getDescription());
                textWhitMetaDataViewHolder.hour.setText("15:30 pm");
                break;
            case 2:
                TextWhitMetaDataViewHolder textWhitMetaDataViewHolder2 = (TextWhitMetaDataViewHolder)holder;
                textWhitMetaDataViewHolder2.message.setText(message.getBody());
                textWhitMetaDataViewHolder2.title.setText(((MessageWhitMetaData)message).getTitle());
                Picasso.with(activity.getApplicationContext()).load(((MessageWhitMetaData)message).getImage()).into(textWhitMetaDataViewHolder2.image);
                textWhitMetaDataViewHolder2.description.setText(((MessageWhitMetaData)message).getDescription());
                textWhitMetaDataViewHolder2.hour.setText("15:30 pm");
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addItem(Message message){
        messages.add(message);
        notifyDataSetChanged();
    }


}
