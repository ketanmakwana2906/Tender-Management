package com.hit.dao;

import java.util.List;

import com.hit.beans.MessageBean;
import com.hit.beans.TenderBean;
import com.hit.beans.TenderStatusBean;

public interface messageDao {
	
    public MessageBean saveMessage(int sender, int reciever, String message);
    
    public List<MessageBean> readMessage(int recieverId, int senderId);
    
}
