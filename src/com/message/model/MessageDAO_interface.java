package com.message.model;

import java.util.List;

public interface MessageDAO_interface {
	public void insert(MessageVO messageVO);
	public void update(MessageVO messageVO);
	public void delete(String msgNo);
	public MessageVO findByPrimaryKey(String msgNo);
	public List<MessageVO> getAll();
	
}
