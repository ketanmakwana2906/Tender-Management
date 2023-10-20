package com.hit.beans;

import java.sql.*;

/**
 *
 * @author onemo
 */
public class MessageBean {
    
    int msgId;
    int sender;
    int reciever;
    Timestamp msgTime;
    String msgStatus;
    String message;

    public MessageBean(int msgId, int sender, int reciever, Timestamp msgTime, String msgStatus, String message) {
        this.msgId = msgId;
        this.sender = sender;
        this.reciever = reciever;
        this.msgTime = msgTime;
        this.msgStatus = msgStatus;
        this.message = message;
    }

    public MessageBean() {
    }

 

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReciever() {
        return reciever;
    }

    public void setReciever(int reciever) {
        this.reciever = reciever;
    }

    public Timestamp getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Timestamp msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
