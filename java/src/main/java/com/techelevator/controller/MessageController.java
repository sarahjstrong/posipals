package com.techelevator.controller;

import com.techelevator.dao.MessageDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Message;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        try{
            List<Message> messages = messageDao.getMessages();
            if(messages.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No messages found");
            } else {
                return messages;
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "GET Request Failed");
        }
    }

    @RequestMapping(path = "/messages/strangers/{username}", method = RequestMethod.GET)
    public List<Message> getMessagesFromStrangers(@RequestParam String username) {
        try {
            List<Message> messages = messageDao.getMessagesFromStrangers(username);
            if(messages.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No messages found");
            } else {
                return messages;
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "GET Request Failed");
        }
    }

    @RequestMapping(path = "/messages/{id}", method = RequestMethod.GET)
    public Message getMessageById(@RequestParam int id) {
        try {
            Message message = messageDao.getMessageById(id);
            if(message == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No message found");
            } else {
                return message;
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "GET Request Failed");
        }
    }

    @RequestMapping(path = "/messages/{username}", method = RequestMethod.GET)
    public List<Message> getMessagesByUsername(@RequestParam String username) {
        try {
            List<Message> messages = messageDao.getMessagesByUsername(username);
            if (messages.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No messages found");
            } else {
                return messages;
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "GET Request Failed");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/messages", method = RequestMethod.POST)
    public Message addMessage(@Valid @RequestBody Message newMessage) {
        try {
            Message message = messageDao.addMessage(newMessage);
            if (message == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error submitting message");
            } else {
                return message;
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "POST Request Failed");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/messages", method = RequestMethod.DELETE)
    public int deleteMessageById(@RequestParam int id) {
        try {
            int rowsDeleted = messageDao.deleteMessageById(id);
            if (rowsDeleted == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting message");
            } else {
                return rowsDeleted;
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DELETE Request Failed");
        }
    }
}
