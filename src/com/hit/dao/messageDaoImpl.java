package com.hit.dao;

import com.hit.beans.*;
import com.hit.utility.DBUtil;

import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author onemo
 */
public class messageDaoImpl implements messageDao{

    //    Message insert starts
	@Override
    public MessageBean saveMessage(int sender, int reciever, String message) {

		Connection con = DBUtil.provideConnection();
        MessageBean msg = null;

        try {
            String saveQuery = "insert into messages(sender, reciever, message) values(?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(saveQuery);

            pstmt.setInt(1, sender);
            pstmt.setInt(2, reciever);
            pstmt.setString(3, message);

            pstmt.executeUpdate();

       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
//    Message saving ends

    //    User read starts
	@Override
    public List<MessageBean> readMessage(int recieverId, int senderId) {
        List<MessageBean> list = new ArrayList<>();

		Connection con = DBUtil.provideConnection();

        try {
            String readQuery = "select * from messages where reciever=? and sender=? or sender=? and reciever=?";
            PreparedStatement pstmt = con.prepareStatement(readQuery);
            pstmt.setInt(1, recieverId);
            pstmt.setInt(2, senderId);
            pstmt.setInt(3, recieverId);
            pstmt.setInt(4, senderId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int msgId = rs.getInt("msgId");
                int reciever = rs.getInt("reciever");
                int sender = rs.getInt("sender");
                Timestamp msgTime = rs.getTimestamp("msgTime");
                String msgStatus = rs.getString("msgStatus");
                String message = rs.getString("message");

                MessageBean msg = new MessageBean(msgId, sender, reciever, msgTime, msgStatus, message);
                list.add(msg);
        
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

}
