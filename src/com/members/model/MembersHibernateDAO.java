package com.members.model;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.Timestamp;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MembersHibernateDAO extends HibernateDaoSupport implements MembersDAO_interface{
	
	private static final String GET_ALL_STMT = "from MembersVO order by memNo";

	@Override
	public void insert(MembersVO membersVO) {
		getHibernateTemplate().saveOrUpdate(membersVO);
	}

	@Override
	public void update(MembersVO membersVO) {
		getHibernateTemplate().update(membersVO);
	}

	@Override
	public void delete(Integer memNo) {
		MembersVO membersVO = (MembersVO)getHibernateTemplate().get(MembersVO.class, memNo);
		getHibernateTemplate().delete(membersVO);
	}

	@Override
	public MembersVO findByPrimaryKey(Integer memNo) {
		MembersVO membersVO = (MembersVO)getHibernateTemplate().get(MembersVO.class, memNo);
		return membersVO;
	}

	@Override
	public List<MembersVO> getAll() {
		List<MembersVO> list = null;
		list = getHibernateTemplate().find(GET_ALL_STMT);
		return list;
	}
	
	
	public static void main(String[] args) {
		
		//取得ApplicationContext實體
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		
		//建立DAO物件
		MembersDAO_interface dao = (MembersDAO_interface)context.getBean("membersDAO");
		
		//測試新增insert
		MembersVO membersVO = new MembersVO();
		//Set<ChatRoomVO> set = new HashSet<ChatRoomVO>();
		
//		membersVO.setMemAcc("a123");
//		membersVO.setMemPw("123");
//		membersVO.setMemEmail("1@abc.com");
//		membersVO.setMemStatus(1);
//		membersVO.setMemAuthority("11111");
//		membersVO.setMemRegdate(new Timestamp(20));
//		
//		dao.insert(membersVO);
		
		//測試修改
		
		//測試查詢
		List<MembersVO> list = dao.getAll();
		for(MembersVO amembersVO : list) {
			System.out.println(amembersVO.getMemAcc());
			System.out.println(amembersVO.getMemPw());
			System.out.println(amembersVO.getMemEmail());
			System.out.println(amembersVO.getMemRegdate());
			
		}
		
		
	}
}
