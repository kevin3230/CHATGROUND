package com.chatroom.model;

import java.util.List;

public interface ChatRoomDAO_interface {
	public void insert(ChatRoomVO chatRoomVO);
	public void update(ChatRoomVO chatRoomVO);
	public void delete(String crNo);
	public ChatRoomVO findByPrimaryKey(String crNo);
	public List<ChatRoomVO> getAll();
	
}
