package com.isaiin.scrappify.Model;

import java.util.Date;

/**
 * Created by alejandro.acosta on 05/07/17.
 */

public class Message {
    private String body;
    private Date date;
    private Type type;

    public enum Type{
        ONLY_TEXT, TEXT_WHIT_METADATA, ONLY_METADATA
    }

    public Message(String body, Date date, Type type) {
        this.body = body;
        this.date = date;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
