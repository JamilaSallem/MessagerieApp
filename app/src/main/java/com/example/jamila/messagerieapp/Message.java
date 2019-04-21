package com.example.jamila.messagerieapp;

public class Message {
    private String sender;
    private String msg;
    private String time;
    private int id;

    public Message() {
    }

    public Message(String sender, String msg, String time, int id) {
        this.sender = sender;
        this.msg = msg;
        this.time = time;
        this.id = id;
    }

    public String getSender() {

        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Message(String sender, String msg, String time) {

        this.sender = sender;
        this.msg = msg;
        this.time = time;
    }
}
