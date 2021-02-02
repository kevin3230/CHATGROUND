package com.members.model;

import java.util.List;
import java.util.Set;


public interface MembersDAO_interface {
	public void insert(MembersVO membersVO);
	public void update(MembersVO membersVO);
	public void delete(Integer memNo);
	public MembersVO findByPrimaryKey(Integer memNo);
	public List<MembersVO> getAll();
	
	//public Set<ChatRoomVO> getChatRoomBycrNo(String crNo);
}
