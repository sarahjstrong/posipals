package com.techelevator.dao;

import com.techelevator.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MessageDao {
    List<Message> getMessages();
    List<Message> getMessagesFromStrangers(String currentUsername);
    Message addMessage(Message newMessage);
    List <Message> getMessagesByUsername(String username);
    Message getMessageById(int id);
    int deleteMessageById(int id);

}
