package com.hit.dao;

import com.hit.beans.*;
import com.hit.utility.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userDaoImpl implements userDao {

	@Override
    public UserBean readUser(String uEmail, String uPass) {

		Connection con = DBUtil.provideConnection();
        UserBean user = null;

        try {
            String readQuery = "SELECT * FROM vendor WHERE vemail=? AND password=?";
            PreparedStatement pstmt = con.prepareStatement(readQuery);
            pstmt.setString(1, uEmail);
            pstmt.setString(2, uPass);
            ResultSet rs = pstmt.executeQuery();
           System.out.print(uEmail);
           System.out.print(uPass);
            if (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("vname");
                String userEmail = rs.getString("vemail");
                String userPass = rs.getString("password");
                String userImg = rs.getString("userImg");
                Timestamp userRegDate = rs.getTimestamp("userRegDate");

                user = new UserBean(userId, userName, userEmail, userPass, userImg, userRegDate, true);
                System.out.println("User was read! User Id = " + userId);
            } else {
                System.out.println("User not present in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
        }

        return user;
    }
	
	@Override
    public UserBean readReciever(int recieverId) {

		Connection con = DBUtil.provideConnection();
        UserBean reciever = null;

        try {
            String readQuery = "SELECT * FROM vendor WHERE userId=?";
            PreparedStatement pstmt = con.prepareStatement(readQuery);
            pstmt.setInt(1, recieverId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("vname");
                String userEmail = rs.getString("vemail");
                String userPass = rs.getString("password");
                String userImg = rs.getString("userImg");
                Timestamp userRegDate = rs.getTimestamp("userRegDate");

                reciever = new UserBean(userId, userName, userEmail, userPass, userImg, userRegDate, true);
            } else {
                System.out.println("User not present in DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reciever;
    }

	@Override
    public List<UserBean> readAllUser() {

		Connection con = DBUtil.provideConnection();
        List<UserBean> list = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            String readQuery = "SELECT * FROM vendor ORDER BY userId";

            ResultSet rs = stmt.executeQuery(readQuery);

            while (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("vname");
                String userEmail = rs.getString("vemail");
                String userPass = rs.getString("password");
                String userImg = rs.getString("userImg");
                Timestamp userRegDate = rs.getTimestamp("userRegDate");

                UserBean user = new UserBean(userId, userName, userEmail, userPass, userImg, userRegDate, true);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
	
	@Override
    public List<UserBean> readForAccountant() {

		Connection con = DBUtil.provideConnection();
        List<UserBean> list = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            String readQuery = "SELECT * FROM tender.vendor where vemail=\"admin-krd@gmail.com\" ;";

            ResultSet rs = stmt.executeQuery(readQuery);

            while (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("vname");
                String userEmail = rs.getString("vemail");
                String userPass = rs.getString("password");
                String userImg = rs.getString("userImg");
                Timestamp userRegDate = rs.getTimestamp("userRegDate");

                UserBean user = new UserBean(userId, userName, userEmail, userPass, userImg, userRegDate, true);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
