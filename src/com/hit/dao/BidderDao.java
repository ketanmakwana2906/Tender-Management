package com.hit.dao;

import java.util.List;

import com.hit.beans.BidderBean;
import com.hit.beans.PaymentBean;

public interface BidderDao {

    public String acceptBid(String applicationId,String tenderId,String vendorId);
	
	public String rejectBid(String applicationId);
	
	public String bidTender(String tenderId,String basePrice, String vendorId,String bidAmount,String deadline);
	
	public List<BidderBean> getAllBidsOfaTender(String tenderId);
	
	public List<BidderBean> getAllBidsOfaVendor(String vendorId);

	String bidTender(String bidId,String tenderId, String basePrice,String vendorId, String bidAmount, String bidDeadline, int points,String licence);

	List<BidderBean> getAllBidsOfaAccTender();
	
	List<PaymentBean> getAllBidPaymentHistory();

	String AccacceptBid(String applicationId, String tenderId, String vendorId);

	String acceptBidassign(String applicationId, String tenderId, String vendorId,String paymentId);


	String bidPaymentOrder(PaymentBean payment);

	List<PaymentBean> getBidOrderId(String bidId);

	String AccountantrejectBid(String applicationId);
	
	
}
