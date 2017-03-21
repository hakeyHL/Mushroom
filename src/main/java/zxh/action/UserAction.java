package zxh.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import zxh.pageModel.DataGrid;
import zxh.pageModel.Json;
import zxh.pageModel.User;
import zxh.service.UserServiceI;

import com.opensymphony.xwork2.ModelDriven;

//@ParentPackage(value = "basePackage")
@Namespace("/")
@Action(value = "userAction")
public class UserAction<T> extends BaseAction implements ModelDriven<User>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	User user =new User();
	private UserServiceI userService;
	@Autowired
	public UserServiceI getUserService() {
		return userService;
	}
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	public void reg(){
		Json j=new Json();
		try {
			userService.save(user);
			j.setSuccess(true);
			j.setMsg("注册成功！");
			// m.put("success", true);
			// m.put("msg", "注册成功！");
		} catch (Exception e) {
			// m.put("success", false);
			// m.put("msg", "注册失败！");
			j.setMsg(e.getMessage());
			// e.printStackTrace();
		}
		super.writeJson(j);
	}
	public void add(){
		Json j=new Json();
		try {
			User u=userService.save(user);
			j.setSuccess(true);
			j.setMsg("增加成功！");
			j.setObj(u);
			// m.put("success", true);
			// m.put("msg", "注册成功！");
		} catch (Exception e) {
			// m.put("success", false);
			// m.put("msg", "注册失败！");
			j.setMsg(e.getMessage());
			// e.printStackTrace();
		}
		super.writeJson(j);
	}
	public void login(){
		Json j=new Json();
		User u=userService.login(user);
		if (u != null) {
			j.setSuccess(true);
			j.setMsg("登录成功！");
		} else {
			j.setMsg("登录失败，用户名或密码错误！");
		}
		super.writeJson(j);
	}
	@Override
	public User getModel() {
		return user;
	}
	public void datagrid(){
		super.writeJson(userService.datagrid(user));
	}
	public void remove(){
		Json j=new Json();
		//logger.info(user.getNames());
		String [] names=user.getNames().split(",");
		for(int i=0;i<names.length;i++){
			if(names[i].equals("zxh")){
				j.setSuccess(false);
				j.setMsg("用户\"zxh\"不能删除！");
				super.writeJson(j);
				return;
			}
		}
		userService.remove(user.getIds());
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
	public void edit(){
		User u=userService.edit(user);
		Json j=new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(u);
		super.writeJson(j);
	}
	public void modifyPwd(){
		Json j = new Json();
		User u=userService.modifyPwd(user);
		j.setSuccess(true);
		j.setMsg("重置成功！");
		j.setObj(u);
		super.writeJson(j);
	}
}
