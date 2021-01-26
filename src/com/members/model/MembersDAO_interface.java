package com.members.model;

import java.util.List;

public interface MembersDAO_interface {
	public void insert(MembersVO membersVO);
	public void update(MembersVO membersVO);
	public void delete(String memNo);
	public MembersVO findByPrimaryKey(String memNo);
	public List<MembersVO> getAll();

}
