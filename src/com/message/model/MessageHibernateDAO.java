package com.message.model;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageHibernateDAO extends HibernateDaoSupport implements MessageDAO_interface {
	
	private static final String GET_ALL_STMT = "from MessageVO order by msgNo";
	
	@Override
	public void insert(MessageVO messageVO) {
		getHibernateTemplate().saveOrUpdate(messageVO);
	}
	
	@Override
	public void update(MessageVO messageVO) {
		getHibernateTemplate().update(messageVO);
	}
	
	@Override
	public void delete(String msgNo) {
		MessageVO messageVO = (MessageVO) getHibernateTemplate().get(MessageVO.class, msgNo);
		getHibernateTemplate().delete(messageVO);
	}
	
	@Override
	public MessageVO findByPrimaryKey(String msgNo) {
		MessageVO messageVO = (MessageVO) getHibernateTemplate().get(MessageVO.class, msgNo);
		return messageVO;
	}
	
	@Override
	public List<MessageVO> getAll(){
		List<MessageVO> list = null;
		list = getHibernateTemplate().find(GET_ALL_STMT);
		return list;
	}
	
	public static void main(String[] args) {
		
	}
	
	
}
