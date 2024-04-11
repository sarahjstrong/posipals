package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Message;
import org.springframework.dao.DataIntegrityViolationException;
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
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allMessagesFromStrangers;
    }

    @Override
    public Message getMessageById(int id) {
        Message message = null;
        String sql = "SELECT message_id, message_username, location, message FROM messages WHERE message_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                message = mapRowToMessage(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return message;
    }



    @Override
    public List <Message> getMessagesByUsername(String username) {
        List <Message> messagesByUsername = new ArrayList<>();
        String sql = "SELECT message_id, message_username, location, message FROM messages WHERE message_username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while (results.next()) {
                Message messageToAdd = mapRowToMessage(results);
                messagesByUsername.add(messageToAdd);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return messagesByUsername;
    }
    @Override
    public Message addMessage(Message newMessage) {
        Message addedMessage = null;
        String sql = "INSERT INTO messages (message_username, location, message)\n" +
                "VALUES (?, ?, ?);";
        try {
            int newMessageId = jdbcTemplate.queryForObject(sql, int.class, newMessage.getUsername(), newMessage.getLocation(), newMessage.getMessage());
            addedMessage = getMessageById(newMessageId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return addedMessage;
    }

    @Override
    public int deleteMessageById(int id) {
        String sql = "DELETE FROM messages WHERE message_id = ?;";
            int rowsDeleted = 0;
        try {
           rowsDeleted =  jdbcTemplate.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }
        return rowsDeleted;
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
