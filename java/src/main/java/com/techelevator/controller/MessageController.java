package com.techelevator.controller;

import com.techelevator.dao.MessageDao;
import com.techelevator.model.Message;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class MessageController {
    private MessageDao messageDao;

    public MessageController(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @RequestMapping(path = "/messages", method = RequestMethod.GET)
    public List<Message> getMessages() {
        return null;
    }

    @RequestMapping(path = "/messages/strangers", method = RequestMethod.GET)
    public List<Message> getMessagesFromStrangers() {
        return null;
    }

    @RequestMapping(path = "/messages/{id}", method = RequestMethod.GET)
    public Message getMessageById(@RequestParam int id) {
        return null;
    }

    @RequestMapping(path = "/messages/{username}", method = RequestMethod.GET)
    public List<Message> getMessagesByUsername(@RequestParam String username) {
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/messages", method = RequestMethod.POST)
    public Message addMessage(@Valid @RequestBody Message newMessage) {
        return null;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/messages", method = HttpMethod.DELETE)
    public int deleteMessageById(@RequestParam int id) {
        return 0;
    }
}
