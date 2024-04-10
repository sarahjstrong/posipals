package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Message;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcMessageDao implements MessageDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcMessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Message> getMessages() {
        List<Message> allMessages = new ArrayList<>();
        String sql = "SELECT message_id, message_username, location, message FROM messages;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Message newMessage = mapRowToMessage(results);
                allMessages.add(newMessage);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allMessages;
    }

    @Override
    public List<Message> getMessagesFromStrangers(String currentUsername) {
        List<Message> allMessagesFromStrangers = new ArrayList<>();
        String sql = "SELECT message_id, message_username, location, message FROM messages;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Message newMessage = mapRowToMessage(results);
                if (!newMessage.getUsername().equals(currentUsername)) {
                    allMessagesFromStrangers.add(newMessage);
                }
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }
        return allMessagesFromStrangers;
    }

    @Override
    public Message addMessage(Message newMessage) {
        return null;
    }

    private Message mapRowToMessage(SqlRowSet results) {
        Message message = new Message();
        message.setId(results.getInt("message_id"));
        message.setUsername(results.getString("message_username"));
        message.setLocation(results.getString("location"));
        message.setMessage(results.getString("message"));
        return message;
    }
}
