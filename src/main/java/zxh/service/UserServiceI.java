package zxh.service;


import zxh.pageModel.DataGrid;
import zxh.pageModel.User;

public interface UserServiceI {
//	public void test();
	public User save(User user);
	public User login(User user);
	public DataGrid datagrid(User user);
	public void remove(String ids);
	//public void edit();
	// void edit(User user);
	public User edit(User user);
	public User modifyPwd(User user);
	
}
