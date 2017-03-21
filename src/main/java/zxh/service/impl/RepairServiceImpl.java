package zxh.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zxh.dao.BaseDaoI;
import zxh.model.Tmenu;
import zxh.model.Tuser;
import zxh.service.RepairServiceI;
import zxh.util.Encrypt;

@Service("repairService")
public class RepairServiceImpl implements RepairServiceI {

	private BaseDaoI<Tmenu> menuDao;
	private BaseDaoI<Tuser> userDao;

	
	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}
	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDao;
	}
	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}

	//修复有户名和首页
	@Override
	public void repair() {
		repairUser();
		repairMenu();
	}
	//还原管理员zxh的密码为123456
	public void repairUser(){
		
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("name", "zxh"); 
		Tuser t=userDao.get("from Tuser t where t.name=:name and t.id!='0'",m);
		if(t!=null){
			t.setName("malegebide");
		}
		Tuser admin=new Tuser();
		admin.setId("0");
		admin.setName("zxh");
		admin.setPwd(Encrypt.e("123456"));
		admin.setModifydatetime(new Date());
		userDao.saveOrUpdate(admin);
	}
	private void repairMenu() {
//		Tmenu root=new Tmenu();
//		root.setId("firstpage");
//		root.setText("首页");
//		menuDao.saveOrUpdate(root);
		
//		Tmenu sm=new Tmenu();
//		sm.setTmenu(root);
//		sm.setId("xtgl");
//		sm.setText("系统管理");
//		menuDao.saveOrUpdate(sm);
		
		Tmenu um=new Tmenu();		
		um.setId("yhgl");
//		um.setTmenu(root);
		um.setText("用户管理");
		um.setUrl("/admin/userM.jsp");
		menuDao.saveOrUpdate(um);
		
//		Tmenu om=new Tmenu();		
//		om.setId("jsgl");
//		om.setTmenu(root);
//		om.setText("角色管理");
//		menuDao.saveOrUpdate(om);
		
		Tmenu cjsbm=new Tmenu();		
		cjsbm.setId("1");
		//cjsbm.setTmenu(root);
		cjsbm.setText("采集设备管理");
		cjsbm.setUrl("/admin/collectorM.jsp");
		menuDao.saveOrUpdate(cjsbm);
		
		Tmenu jksbm=new Tmenu();		
		jksbm.setId("2");
		//jksbm.setTmenu(root);
		jksbm.setText("监控设备管理");
		jksbm.setUrl("/admin/monitorM.jsp");
		menuDao.saveOrUpdate(jksbm);
		
		Tmenu zxsbm=new Tmenu();		
		zxsbm.setId("3");
		//jksbm.setTmenu(root);
		zxsbm.setText("执行设备管理");
		zxsbm.setUrl("/admin/equipmentM.jsp");
		menuDao.saveOrUpdate(zxsbm);
		
		Tmenu mm=new Tmenu();		
		mm.setId("4");
		//mm.setTmenu(root);
		mm.setText("菇房管理");
		mm.setUrl("/admin/mrHouseM.jsp");
		menuDao.saveOrUpdate(mm);
		
		Tmenu syjpzm=new Tmenu();		
		syjpzm.setId("5");
		//syjpzm.setTmenu(root);
		syjpzm.setText("食用菌品种管理");
		syjpzm.setUrl("/admin/mrVarietyM.jsp");
		menuDao.saveOrUpdate(syjpzm);
		
		Tmenu syjsztjm=new Tmenu();		
		syjsztjm.setId("6");
		//syjsztjm.setTmenu(root);
		syjsztjm.setText("食用菌生长条件管理");
		syjsztjm.setUrl("/admin/varietyM.jsp");
		menuDao.saveOrUpdate(syjsztjm);
		
		Tmenu zpxxm=new Tmenu();		
		zpxxm.setId("7");
		//zpxxm.setTmenu(root);
		zpxxm.setText("栽培信息管理");
		zpxxm.setUrl("/admin/culInfoM.jsp");
		menuDao.saveOrUpdate(zpxxm);
					
			
		Tmenu kzsbzxxxm=new Tmenu();		
		kzsbzxxxm.setId("8");
		//kzsbzxxxm.setTmenu(root);
		kzsbzxxxm.setText("控制设备执行信息");
		kzsbzxxxm.setUrl("/admin/CDEM.jsp");
		menuDao.saveOrUpdate(kzsbzxxxm);
		
		Tmenu hjyzcjm=new Tmenu();		
		hjyzcjm.setId("9");
		//hjyzcjm.setTmenu(root);
		hjyzcjm.setText("环境因子采集信息");
		hjyzcjm.setUrl("/admin/emfactorM.jsp");
		menuDao.saveOrUpdate(hjyzcjm);
	}

}
