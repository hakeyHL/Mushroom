package zxh.service;

import java.util.List;

import zxh.pageModel.Menu;

public interface MenuServiceI {
		//异步树
	public List<Menu> getTreeNode(String id);
	//全部树
	public List<Menu> getAllTreeNode();
}
