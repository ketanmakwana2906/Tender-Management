package com.hit.beans;

import java.sql.Timestamp;

/**
 *
 * @author onemo
 */
public class UserBean {
    int userId;
    String userName;
    String userEmail;
    String userPass;
    String userImg;
    Timestamp userRegDate;
    public boolean valid;

    public UserBean() {
        
    }

 

    public UserBean(int userId, String userName, String userEmail, String userPass, String userImg, Timestamp userRegDate, boolean valid) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.userImg = userImg;
        this.userRegDate = userRegDate;
        this.valid = valid;
    }


    public Timestamp getUserRegDate() {
        return userRegDate;
    }

    public void setUserRegDate(Timestamp stuRegDate) {
        this.userRegDate = userRegDate;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

 

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
    
    
}
