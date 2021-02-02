package com.memcrset.model;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemCRSetHibernateDAO extends HibernateDaoSupport implements MemCRSetDAO_interface {
	
	private static final String GET_ALL_STMT = "from MemCRSetVO order by memCRSet_crNo";
	
	@Override
	public void insert(MemCRSetVO memCRSetVO) {
		getHibernateTemplate().saveOrUpdate(memCRSetVO);
	}
	
	@Override
	public void update(MemCRSetVO memCRSetVO) {
		getHibernateTemplate().update(memCRSetVO);
	}
	
	@Override
	public void delete(String memCRSet_no) {
		MemCRSetVO memCRSetVO = (MemCRSetVO) getHibernateTemplate().get(MemCRSetVO.class, memCRSet_no);
		getHibernateTemplate().delete(memCRSetVO);
	}
	
	@Override
	public MemCRSetVO findByPrimaryKey(String memCRSet_no) {
		MemCRSetVO memCRSetVO = (MemCRSetVO) getHibernateTemplate().get(MemCRSetVO.class, memCRSet_no);
		return memCRSetVO;
	}
	
	@Override
	public List<MemCRSetVO> getAll(){
		List<MemCRSetVO> list = null;
		list = getHibernateTemplate().find(GET_ALL_STMT);
		return list;
	}
	
	public static void main(String[] args) {
		
	}
	
}
