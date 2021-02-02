package com.memcrset.model;

import java.util.List;

public interface MemCRSetDAO_interface {
	public void insert(MemCRSetVO memCRSetVO);
	public void update(MemCRSetVO memCRSetVO);
	public void delete(String memCRSet_no);
	public MemCRSetVO findByPrimaryKey(String memCRSet_no);
	public List<MemCRSetVO> getAll();
	
}
