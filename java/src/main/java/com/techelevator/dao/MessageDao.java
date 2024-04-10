package com.techelevator.dao;

import com.techelevator.model.Message;

import java.util.List;

public interface MessageDao {
    List<Message> getMessages();
    List<Message> getMessagesFromStrangers(String currentUsername);
    Message addMessage(Message newMessage);

}
