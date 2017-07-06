package com.isaiin.scrappify.Model;

import android.media.Image;

import java.util.Date;

/**
 * Created by alejandro.acosta on 05/07/17.
 */

public class MessageWhitMetaData extends Message {
    private String image;
    private String title;
    private String description;

    public MessageWhitMetaData(String body, Date date, Type type, String image, String title, String description) {
        super(body, date, type);
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
