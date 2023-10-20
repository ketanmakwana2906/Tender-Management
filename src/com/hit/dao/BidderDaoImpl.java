package com.hit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hit.beans.*;
import com.hit.utility.DBUtil;
import com.hit.utility.IDUtil;
import java.sql.CallableStatement; // Add this import statement

public class BidderDaoImpl implements BidderDao{
	
	
	@Override
	public String acceptBid(String applicationId,String tenderId,String vendorId) {
		String status = "Bid Acceptance Failed";
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("select * from tenderstatus where tid=?");
			ps.setString(1, tenderId);
			rs = ps.executeQuery();
			if(rs.next()){
				
				status = "Project Already Assigned";
			}
			else{
			
				pst = con.prepareStatement("update bidder set status = ? where bid=? and status=?");
				
				pst.setString(1, "AdminAccepted");
				pst.setString(2, applicationId);
				pst.setString(3, "Pending");
				
				int x = pst.executeUpdate();
				if(x>0){
					status = "Bid Has Been Accepted Successfully!";
					TenderDao dao = new TenderDaoImpl();
					//status = status + "<br>"+dao.assignTender(tenderId, vendorId,applicationId);
				}
			}
		} catch (SQLException e) {

			status = status + "Error: "+e.getMessage();
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
		}
		return status;
	}
	
	@Override
	public String AccacceptBid(String applicationId,String tenderId,String vendorId) {
		String status = "Bid Acceptance Failed";
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("select * from tenderstatus where tid=?");
			ps.setString(1, tenderId);
			rs = ps.executeQuery();
			if(rs.next()){
				
				status = "Project Already Assigned";
			}
			else{
			
				pst = con.prepareStatement("update bidder set status = ? where bid=? and status=?");
				
				pst.setString(1, "paymentpending");
				pst.setString(2, applicationId);
				pst.setString(3, "AdminAccepted");
				
				int x = pst.executeUpdate();
				if(x>0){
					status = "Bid Has Been Accepted Successfully!";
					TenderDao dao = new TenderDaoImpl();
					//status = status + "<br>"+dao.assignTender(tenderId, vendorId,applicationId);
				}
			}
		} catch (SQLException e) {

			status = status + "Error: "+e.getMessage();
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
		}
		return status;
	}
	
	@Override
	public String acceptBidassign(String applicationId,String tenderId,String vendorId,String paymentId) {
		String status = "Bid Acceptance Failed";
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("select * from tenderstatus where tid=?");
			ps.setString(1, tenderId);
			rs = ps.executeQuery();
			if(rs.next()){
				
				status = "Project Already Assigned";
			}
			else{
			
				pst = con.prepareStatement("UPDATE bidder SET status = CASE WHEN tid = ? AND bid = ? AND status = 'paymentpending' THEN 'Accepted' ELSE 'Rejected' END WHERE tid = ? AND (status = 'paymentpending' OR status = 'pending')");
				
				pst.setString(1, tenderId);
				pst.setString(2, applicationId);
				pst.setString(3, tenderId);
				
				int x = pst.executeUpdate();
				if(x>0){
					status = "Bid Has Been Accepted Successfully!";
					TenderDao dao = new TenderDaoImpl();
					status = status + "<br>"+dao.assignTender(tenderId, vendorId,applicationId);
				}
				pst = con.prepareStatement("update paymentorder set paymentId=?, status='paid' where bid=?");
                pst.setString(1, paymentId);
                pst.setString(2, applicationId);
                int paymentUpdateResult = pst.executeUpdate();
                if (paymentUpdateResult > 0) {
                    status = status + "<br>Payment details updated successfully.";
                } else {
                    status = status + "<br>Error updating payment details.";
                }
			}
		} catch (SQLException e) {

			status = status + "Error: "+e.getMessage();
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
		}
		return status;
	}
	
	@Override
	public String rejectBid(String applicationId) {
		String status = "Bid Rejection Failed";
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("update bidder set status = ? where bid=? and status = ?");
			
			ps.setString(1, "AdminRejected");
			ps.setString(2, applicationId);
			ps.setString(3, "Pending");
			
			int x = ps.executeUpdate();
			if(x>0)
				status = "Bid Has Been Rejected Successfully by admin!";
			
		} catch (SQLException e) {

			status = status + "Error: "+e.getMessage();
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
		}
		return status;
			
	}
	
	@Override
	public String AccountantrejectBid(String applicationId) {
		String status = "Bid Rejection Failed";
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("update bidder set status = ? where bid=? and status = ?");
			
			ps.setString(1, "AccountantRejected");
			ps.setString(2, applicationId);
			ps.setString(3, "AdminAccepted");
			
			int x = ps.executeUpdate();
			if(x>0)
				status = "Bid Has Been Rejected Successfully by accountant!";
			
		} catch (SQLException e) {

			status = status + "Error: "+e.getMessage();
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
		}
		return status;
			
	}

	@Override
	public String bidTender(String bidId, String tenderId, String basePrice,String vendorId, String bidAmount, String bidDeadline, int points,String licence) {
	    String status = "Tender Bidding Failed!";

	    String bidStatus = "Pending";

		BidderBean bidder = new BidderBean(bidId, vendorId, tenderId,basePrice, bidAmount, bidDeadline, bidStatus,points,licence);
	    Connection con = DBUtil.provideConnection();
	    CallableStatement cs = null;
	    
	    try {
	        // Call the InsertOrUpdateBid stored procedure
	        cs = con.prepareCall("{CALL InsertOrUpdateBid(?, ?, ?, ?,?, ?, ?, ?,?)}");
	        
	        cs.setString(1, bidId);
	        cs.setString(2, vendorId);
	        cs.setString(3, tenderId);
	        cs.setInt(4, Integer.parseInt(basePrice));
	        cs.setInt(5, Integer.parseInt(bidAmount));
	    	Timestamp deadline = bidder.getBidDeadline();
			cs.setTimestamp(6, deadline);
	        cs.setString(7, bidStatus);
	        cs.setInt(8, points);
	        cs.setString(9, licence);
	        
	        int x = cs.executeUpdate();
	        if(x>0)
				status = "You have successfully Bid for the tender";
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.closeConnection(con);
	        DBUtil.closeConnection(cs);
	    }
	    
	    return status;
	}




	@Override
	public List<BidderBean> getAllBidsOfaTender(String tenderId) {
		
		List<BidderBean> bidderList = new ArrayList<BidderBean>();
		
		
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("SELECT * FROM bidder WHERE tid = ? ORDER BY points DESC;");
			
			ps.setString(1, tenderId);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				BidderBean bidder = new BidderBean();
				
				bidder.setBidAmount(rs.getInt("bidamount"));
				Timestamp sqlDate = rs.getTimestamp("deadline");
				bidder.setBidDeadline(sqlDate);
				bidder.setBidId(rs.getString("bid"));
				bidder.setBidStatus(rs.getString("status"));
				bidder.setTenderId(rs.getString("tid"));
				bidder.setVendorId(rs.getString("vid"));
				bidder.setPoints(rs.getInt("points"));
				bidder.setLicence(rs.getString("licence"));

				
				bidderList.add(bidder);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
			
			DBUtil.closeConnection(rs);
		}
		
		
		
		return bidderList;
	}

	@Override
	public List<BidderBean> getAllBidsOfaAccTender() {
		
		List<BidderBean> bidderList = new ArrayList<BidderBean>();
		
		
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("SELECT * FROM bidder WHERE (status = 'AdminAccepted' OR status = 'PaymentPending' OR status = 'Accepted' OR status = 'AccountantRejected'  ) ORDER BY points DESC;");
		

			
			rs = ps.executeQuery();
			
			while(rs.next()){
				BidderBean bidder = new BidderBean();
				
				bidder.setBidAmount(rs.getInt("bidamount"));
				Timestamp sqlDate = rs.getTimestamp("deadline");
				bidder.setBidDeadline(sqlDate);				bidder.setBidId(rs.getString("bid"));
				bidder.setBaseprice(rs.getInt("basePrice"));
				bidder.setBidStatus(rs.getString("status"));
				bidder.setTenderId(rs.getString("tid"));
				bidder.setVendorId(rs.getString("vid"));
				bidder.setPoints(rs.getInt("points"));
				bidder.setLicence(rs.getString("licence"));

				
				bidderList.add(bidder);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
			
			DBUtil.closeConnection(rs);
		}
		
		
		
		return bidderList;
	}
	
	@Override
	public List<PaymentBean> getAllBidPaymentHistory() {
		
		List<PaymentBean> paymentList = new ArrayList<PaymentBean>();
		
		
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("SELECT * FROM paymentorder;");
		

			
			rs = ps.executeQuery();
			
			while(rs.next()){
				PaymentBean payment = new PaymentBean();
				
				payment.setOrderId(rs.getString("orderId"));
				payment.setorderCreatedAt(rs.getTimestamp("createdAt"));
				payment.setBidId(rs.getString("bid"));
				payment.setVendorId(rs.getString("vid"));
				payment.setTenderId(rs.getString("tid"));
				payment.setOrderAmount(rs.getInt("amount"));
                payment.setOrderPaymentId(rs.getString("paymentId"));
                payment.setOrderStatus(rs.getString("status"));
				

				
                paymentList.add(payment);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
			
			DBUtil.closeConnection(rs);
		}
		
		
		
		return paymentList;
	}

	@Override
	public List<BidderBean> getAllBidsOfaVendor(String vendorId) {
List<BidderBean> bidderList = new ArrayList<BidderBean>();
		
		
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("select * from bidder where vid=?");
			
			ps.setString(1, vendorId);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				BidderBean bidder = new BidderBean();
				
				bidder.setBidAmount(rs.getInt("bidamount"));
				Timestamp sqlDate = rs.getTimestamp("deadline");
				bidder.setBidDeadline(sqlDate);				bidder.setBidId(rs.getString("bid"));
				bidder.setBaseprice(rs.getInt("basePrice"));
				bidder.setBidStatus(rs.getString("status"));
				bidder.setTenderId(rs.getString("tid"));
				bidder.setVendorId(rs.getString("vid"));
				bidder.setPoints(rs.getInt("points"));
				System.out.print(rs.getInt("points"));
				
				bidderList.add(bidder);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
			
			DBUtil.closeConnection(rs);
		}
		
		
		
		return bidderList;
	}

   
	@Override
	public String bidPaymentOrder(PaymentBean payment) {
		
		 String status = "order creation failed";
		
		Connection conn=DBUtil.provideConnection();
		
		PreparedStatement pst = null;
		
		try {
			pst= conn.prepareStatement("insert into paymentorder values(?,?,?,?,?,?,?,?)");
			pst.setString(1, payment.getOrderId());
			Timestamp createdAt = payment.getorderCreatedAt();
		
			pst.setTimestamp(2, createdAt);
			pst.setString(3, payment.getVendorId());
			pst.setString(4, payment.getBidId());
			pst.setString(5, payment.getTenderId());			
			pst.setInt(6, payment.getOrderAmount());
			pst.setString(7, payment.getOrderPaymentId());
			pst.setString(8, payment.getOrderStatus());


			int x=pst.executeUpdate();
			if(x>0)
				status = "order created for bid payment <br> Your order id: "+payment.getOrderId();
		
		} catch (SQLException e) {
			
			status="Error : "+e.getMessage();
			
			e.printStackTrace();
		}
		finally{
		
			DBUtil.closeConnection(pst);
			
			DBUtil.closeConnection(conn);
			
		}
		
		return status;
	}
	
	@Override
	public List<PaymentBean> getBidOrderId(String bidId) {
List<PaymentBean> orderList = new ArrayList<PaymentBean>();
		
		
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("select * from paymentorder where bid=?");
			
			ps.setString(1, bidId);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				PaymentBean payment = new PaymentBean();
				
				payment.setOrderId(rs.getString("orderId"));
				payment.setorderCreatedAt(rs.getTimestamp("createdAt"));
				payment.setVendorId(rs.getString("vid"));
				payment.setBidId(rs.getString("bid"));
				payment.setTenderId(rs.getString("tid"));
				payment.setOrderAmount(rs.getInt("amount"));
				payment.setOrderPaymentId(rs.getString("paymentId"));
				payment.setOrderStatus(rs.getString("status"));

				
				orderList.add(payment);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			
			DBUtil.closeConnection(con);
			
			DBUtil.closeConnection(ps);
			
			DBUtil.closeConnection(rs);
		}
		
		
		
		return orderList;
	}

	

	@Override
	public String bidTender(String tenderId, String basePrice, String vendorId, String bidAmount, String deadline) {
		// TODO Auto-generated method stub
		return null;
	}


	

	
}
