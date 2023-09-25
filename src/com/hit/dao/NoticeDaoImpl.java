package com.hit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hit.beans.NoticeBean;
import com.hit.utility.DBUtil;

public class NoticeDaoImpl implements NoticeDao{

	@Override
	public String removeNotice(int noticeId) {
		String status = "Notice Deletion Failed";
		NoticeBean notice = null;

		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = con.prepareStatement("update notice set status=? where id=?");
			
			ps.setString(1, "expired");
			ps.setInt(2, noticeId);
			
			int x = ps.executeUpdate();
			
			if(x>0){

				status = "Notice No: "+noticeId+" has been archived Successfully!";
			}
			
		} catch (SQLException e) {
			status = "Error: " +e.getMessage();
			
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			DBUtil.closeConnection(ps);
			
		}
		
		return status;
	}
	
	@Override
	public String restoreNotice(int noticeId) {
		String status = "Notice restore Failed";
		NoticeBean notice = null;

		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = con.prepareStatement("update notice set status=? where id=?");
			
			ps.setString(1, "active");
			ps.setInt(2, noticeId);
			
			int x = ps.executeUpdate();
			
			if(x>0){

				status = "Notice No: "+noticeId+" has been restored Successfully!";
			}
			
		} catch (SQLException e) {
			status = "Error: " +e.getMessage();
			
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			DBUtil.closeConnection(ps);
			
		}
		
		return status;
	}

	@Override
	public String addNotice(String noticeTitle,String noticeDesc,String validUntilDateTime) {
		String status = "Notice Addition Failed!";
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		
		try {
			
			ps = con.prepareStatement("insert into notice(title,info,validity,status) values(?,?,?,?)");
			
			ps.setString(1, noticeTitle);
			
			ps.setString(2, noticeDesc);
			ps.setString(3, validUntilDateTime);
			ps.setString(4, "active");

			
			int k = ps.executeUpdate();
			
			if(k>0){
				
				status = "Notice Added Successfully";
				
			}
			
		} catch (SQLException e) {

			status = "Error: " +e.getMessage();
			e.printStackTrace();
		}
		
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
		}
		
		return status;
	}

	@Override
	public List<NoticeBean> viewAllNotice() {
		
		List<NoticeBean> noticeList = new ArrayList<NoticeBean>();
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select * from notice order by sysdate() asc limit 8");
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				NoticeBean notice = new NoticeBean();
				
				notice.setNoticeId(rs.getInt("id"));
				
				notice.setNoticeTitle(rs.getString("title"));
				
				notice.setNoticeInfo(rs.getString("info"));
				
				notice.setNoticeValidity(rs.getString("validity"));
				
				notice.setNoticeStatus(rs.getString("status"));
				
				noticeList.add(notice);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(rs);
			
			DBUtil.closeConnection(ps);
		}
		
		return noticeList;
	}

	@Override
	public String updateNotice(NoticeBean notice) {
        
		String status = "Notice Updation Failed";
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		
		try {
			
			ps = con.prepareStatement("update notice set title=? , info=?,validity=?, status=? where id=?");
			
			ps.setString(1, notice.getNoticeTitle());
			
			ps.setString(2, notice.getNoticeInfo());
			
			ps.setString(3, notice.getNoticeValidity());
			String noticeValidity = notice.getNoticeValidity();
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formattedTimestamp = dateFormat.format(currentTimestamp);
			if (noticeValidity.compareTo(formattedTimestamp) < 0)  {
		            ps.setString(4, "expired"); // Set status to "expired"
		        } else {
		            ps.setString(4, "active"); // Set status to "active"
		        }
			 
				ps.setInt(5, notice.getNoticeId());
			
			int x = ps.executeUpdate();
			
			if(x>0)
				status = "Notice Updated Successfully!";
			
		} catch (SQLException e) {
		
			status = "Error: "+e.getMessage();
			
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			DBUtil.closeConnection(ps);
			
		}
		return status;
	}

	@Override
	public NoticeBean getNoticeById(int noticeId) {
		NoticeBean notice = null;
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select * from notice where id=?");
			
			ps.setInt(1, noticeId);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				notice = new NoticeBean(noticeId,rs.getString("title"),rs.getString("info"),rs.getString("validity"),rs.getString("status"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return notice;
	}

	

}
