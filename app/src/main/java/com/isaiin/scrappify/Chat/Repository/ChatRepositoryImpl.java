package com.isaiin.scrappify.Chat.Repository;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.isaiin.scrappify.Chat.Presenter.ChatPresenter;
import com.isaiin.scrappify.Model.Message;
import com.isaiin.scrappify.Model.MessageWhitMetaData;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Date;

/**
 * Created by alejandro.acosta on 06/07/17.
 */

public class ChatRepositoryImpl implements ChatRepository {

    private ChatPresenter presenter;

    public ChatRepositoryImpl(ChatPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getMetadata(final String message, String url, Context context) {
        //String URL = "https://noembed.com/embed?url=https://www.youtube.com/watch?v=LAlU6xhLtfI";
        Uri uri = Uri.parse(url);
        String URL = "https://noembed.com/embed?url=" + uri;
        Log.e("SCRAPPIFY", URL);
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("error")){
                            try {
                                presenter.messageError(response.getString("error"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        MessageWhitMetaData m;
                        if (message.split(" ").length > 1){
                            try {
                                m = new MessageWhitMetaData(
                                        message,
                                        new Date(),
                                        Message.Type.TEXT_WHIT_METADATA,
                                        response.getString("thumbnail_url"),
                                        response.getString("provider_name"),
                                        response.getString("title")
                                );
                                presenter.messageSuccess(m);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            try {
                                m = new MessageWhitMetaData(
                                        null,
                                        new Date(),
                                        Message.Type.ONLY_METADATA,
                                        response.getString("thumbnail_url"),
                                        response.getString("provider_name"),
                                        response.getString("title")
                                );
                                presenter.messageSuccess(m);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d("RESPONSE", "Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RESPONSE", error.toString());
                        presenter.messageError(error.getMessage());
                    }
                }){

        };
        requestQueue.add(jsObjRequest);
    }

}
