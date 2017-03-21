package zxh.action;

//import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import zxh.pageModel.Menu;
import zxh.service.MenuServiceI;

import com.opensymphony.xwork2.ModelDriven;

@Action(value = "menuAction")
public class MenuAction extends BaseAction implements ModelDriven<Menu>{
	private Menu menu=new Menu();
	
	private MenuServiceI menuService;
	public MenuServiceI getMenuService() {
		return menuService;
	}
	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}
	@Override
	public Menu getModel() {
		// TODO Auto-generated method stub
		return menu;
	}
	//异步回取树节点
	public void getTreeNode(){
		super.writeJson(menuService.getTreeNode(menu.getId()));
	}
	
	public void getAllTreeNode(){
		super.writeJson(menuService.getAllTreeNode());
	}
}
