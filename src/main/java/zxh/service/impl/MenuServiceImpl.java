package zxh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zxh.dao.BaseDaoI;
import zxh.model.Tmenu;
import zxh.pageModel.Menu;
import zxh.service.MenuServiceI;

@Service("menuService")
public class MenuServiceImpl implements MenuServiceI {
	private BaseDaoI<Tmenu> menuDao;

	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDao;
	}
	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}
	  
	public void save(Menu menu){}
	@Override
	public List<Menu> getTreeNode(String id) {
		//由hibernate模型转换成pagemodel模型
		List<Menu> nl=new ArrayList<Menu>();
		String hql=null;
		Map<String,Object> params=new HashMap<String,Object>();
		if(id==null || id.equals("")){
			//查询所有根节点
			hql="from Tmenu t where t.tmenu is null";
		}else{
			//异步加载当前id下的子节点
			hql="from Tmenu t where t.tmenu.id=:id";
			params.put("id", id);
		}
		List<Tmenu> l=menuDao.find(hql,params);
		if(l!=null && l.size()>0){
			for(Tmenu t:l){
				Menu m=new Menu();
				BeanUtils.copyProperties(t, m);
				Map<String,Object> attributes=new HashMap<String,Object>();
				attributes.put("url",t.getUrl());
				m.setAttributes(attributes);
				Set<Tmenu> set=t.getTmenus();
				if(set !=null && !set.isEmpty()){
					m.setState("closed");//节点以文件夹的形式体现
				}else{
					m.setState("open");//节点以文件体现
				}
				nl.add(m);
			}
		}
		return nl;
	}
	@Override
	public List<Menu> getAllTreeNode() {
		List<Menu> nl=new ArrayList<Menu>();
		String hql="from Tmenu t";
		List<Tmenu> l=menuDao.find(hql);
		if(l!=null && l.size()>0){
			for(Tmenu t:l){
				Menu m=new Menu();
				BeanUtils.copyProperties(t, m);
				Map<String,Object> attributes=new HashMap<String,Object>();
				attributes.put("url",t.getUrl());
				m.setAttributes(attributes);
				Tmenu tm=t.getTmenu();//获得当前节点的父节点对象
				if(tm!=null){
					m.setPid(t.getTmenu().getId());
				}
				nl.add(m);
			}
		}
		return nl;
	}
}
