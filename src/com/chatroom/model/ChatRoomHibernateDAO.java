package com.chatroom.model;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ChatRoomHibernateDAO extends HibernateDaoSupport implements ChatRoomDAO_interface {
	
	private static final String GET_ALL_STMT = "from ChatRoomVO order by crNo";
	
	@Override
	public void insert(ChatRoomVO chatRoomVO) {
		getHibernateTemplate().saveOrUpdate(chatRoomVO);
	}
	
	@Override
	public void update(ChatRoomVO chatRoomVO) {
		getHibernateTemplate().update(chatRoomVO);
	}
	
	@Override
	public void delete(String crNo) {
		ChatRoomVO chatRoomVO = (ChatRoomVO) getHibernateTemplate().get(ChatRoomVO.class, crNo);
		getHibernateTemplate().delete(chatRoomVO);
	}
	
	@Override
	public ChatRoomVO findByPrimaryKey(String crNo) {
		ChatRoomVO chatRoomVO = (ChatRoomVO) getHibernateTemplate().get(ChatRoomVO.class, crNo);
		return chatRoomVO;
	}
	
	@Override
	public List<ChatRoomVO> getAll() {
		List<ChatRoomVO> list = null;
		list = getHibernateTemplate().find(GET_ALL_STMT);
		return list;
	}
	
	public static void main(String[] args) {
		
	}
	
}
