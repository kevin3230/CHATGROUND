package com.members.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.chatroom.model.ChatRoomVO;

public class MembersVO {
	private Integer memNo;
	private String memAcc;
	private String memPw;
	private String memNickName;
	private String memEmail;
	private Date memBirth;
	private String memGender;
	private Timestamp memRegdate;
	private Integer memStatus;
	private String memSalt;
	private String memAuthority;
	private Byte[] memPic;
	//private Set<ChatRoomVO> chatRooms = new HashSet<ChatRoomVO>();
	
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public String getMemAcc() {
		return memAcc;
	}
	public void setMemAcc(String memAcc) {
		this.memAcc = memAcc;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemNickName() {
		return memNickName;
	}
	public void setMemNickName(String memNickName) {
		this.memNickName = memNickName;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public Date getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public Timestamp getMemRegdate() {
		return memRegdate;
	}
	public void setMemRegdate(Timestamp memRegdate) {
		this.memRegdate = memRegdate;
	}
	public Integer getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}
	public String getMemSalt() {
		return memSalt;
	}
	public void setMemSalt(String memSalt) {
		this.memSalt = memSalt;
	}
	public String getMemAuthority() {
		return memAuthority;
	}
	public void setMemAuthority(String memAuthority) {
		this.memAuthority = memAuthority;
	}
	public Byte[] getMemPic() {
		return memPic;
	}
	public void setMemPic(Byte[] memPic) {
		this.memPic = memPic;
	}
//	public Set<ChatRoomVO> getChatRooms() {
//		return chatRooms;
//	}
//	public void setChatRooms(Set<ChatRoomVO> chatRooms) {
//		this.chatRooms = chatRooms;
//	}
	
}
