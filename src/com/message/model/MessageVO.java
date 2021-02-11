package com.message.model;

import java.sql.Timestamp;

public class MessageVO {
	private String msgNo;
	private String msgFrom;
	private Timestamp msgTime;
	private String msgContent;
	private String msgCrno;
	private Integer msgStatus;
	
	public String getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
	}
	public String getMsgFrom() {
		return msgFrom;
	}
	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}
	public Timestamp getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(Timestamp msgTime) {
		this.msgTime = msgTime;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgCrno() {
		return msgCrno;
	}
	public void setMsgCrno(String msgCRNo) {
		this.msgCrno = msgCRNo;
	}
	public Integer getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(Integer msgStatus) {
		this.msgStatus = msgStatus;
	}
}
