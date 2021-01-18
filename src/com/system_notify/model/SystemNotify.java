package com.system_notify.model;

import java.sql.Timestamp;

public class SystemNotify {
	private String snno;
	private String snContent;
	private Timestamp snTime;
	
	public String getSnno() {
		return snno;
	}
	public void setSnno(String snno) {
		this.snno = snno;
	}
	public String getSnContent() {
		return snContent;
	}
	public void setSnContent(String snContent) {
		this.snContent = snContent;
	}
	public Timestamp getSnTime() {
		return snTime;
	}
	public void setSnTime(Timestamp snTime) {
		this.snTime = snTime;
	}
}
