package com.hit.beans;

import java.io.Serializable;

public class NoticeBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int noticeId;
	
	public String noticeTitle;
	
	public String noticeInfo;

	private  String noticeValidity;
	
	private String noticeStatus;

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeInfo() {
		return noticeInfo;
	}

	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}
	
	public String getNoticeValidity() {
		return noticeValidity;
	}

	public void setNoticeValidity(String noticeValidity) {
		this.noticeValidity = noticeValidity;
	}

	public NoticeBean(int noticeId, String noticeTitle, String noticeInfo,String noticeValidity,String noticeStatus) {
		super();
		this.noticeId = noticeId;
		this.noticeTitle = noticeTitle;
		this.noticeInfo = noticeInfo;
		this.noticeValidity=noticeValidity;
		this.noticeStatus = noticeStatus;
	}

	public NoticeBean() {
		super();
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}


	
	
}
