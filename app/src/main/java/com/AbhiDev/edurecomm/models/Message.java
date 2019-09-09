package com.AbhiDev.edurecomm.models;

public class Message {
    String text;
    String sender;
    String createdAt;

    public static Message createBotGreetingMessage(){
        Message message = new Message();
        message.setText("Hello! I'm Poker Bot. How may I help you? You can try searching for courses, universities and even get to create your career goals.");
        message.setCreatedAt(" ");
        message.setSender("Poker Bot");
        return message;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
