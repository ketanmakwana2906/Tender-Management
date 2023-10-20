package com.hit.dao;

import java.util.List;

import com.hit.beans.MessageBean;
import com.hit.beans.TenderBean;
import com.hit.beans.TenderStatusBean;
import com.hit.beans.UserBean;

public interface userDao {
	
    public UserBean readUser(String uEmail, String uPass);
    
    public UserBean readReciever(int recieverId);
    
    public List<UserBean> readAllUser();

	public List<UserBean> readForAccountant();
    
    

    
}
