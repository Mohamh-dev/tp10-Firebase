package com.example.tp10firebase;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Message {
    private String msg;
    private String key;
    private String author;
    public Message() {}
    public Message(String key, String msg, String author) {
        this.key = key;
        this.msg = msg;
        this.author = author;
    }
    public String getMsg() {
        return msg;
    }
    public String getAuthor() {
        return author;
    }
    public String getKey() {
        return key;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setKey(String key) {
        this.key = key;
    }
}