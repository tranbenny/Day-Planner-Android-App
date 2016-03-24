package com.example.bennytran.yelpagendabuilder.FirebaseModels;


public class Message {

    private String body;
    private String sender;
    private String timestamp;

    public Message() {}

    public Message(String body, String sender, String timestamp) {
        this.body = body;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    public String getBody() { return body; }
    public String getSender() { return sender; }
    public String getTimestamp() { return timestamp; }


}
