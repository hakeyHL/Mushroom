package zxh.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zxh.dao.BaseDaoI;
import zxh.model.Tuser;
import zxh.pageModel.DataGrid;
import zxh.pageModel.User;
import zxh.service.UserServiceI;
import zxh.util.Encrypt;

@Service(value = "userService")
public class UserServiceImpl implements UserServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);
	private BaseDaoI<Tuser> userDao;

	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	@Override
	public User save(User user) {
		Tuser t = new Tuser();
		// t的屬性拷貝到user
		BeanUtils.copyProperties(user, t, new String[] { "pwd" });
		// System.out.println(t.getName());
		// System.out.println(user.getPwd());
		t.setId(UUID.randomUUID().toString());
		t.setPwd(Encrypt.e(user.getPwd()));
		// t.setName(user.getName());
		t.setCreatedatetime(new Date());
		userDao.save(t);
		BeanUtils.copyProperties(t, user);
		// user.setId(t.getId());
		return user;
	}

	@Override
	public User login(User user) {
		// Tuser t=userDao.get("from Tuser t where t.name=? and t.pwd=?", new
		// Object[]{user.getName(),Encrypt.e(user.getPwd())} );
		Map<String, Object> params = new HashMap<String, Object>();
		// logger.info(user.getName());
		// logger.info(user.getPwd());
		// logger.info(t);
		params.put("name", user.getName());
		params.put("pwd", Encrypt.e(user.getPwd()));
		Tuser t = userDao.get("from Tuser t where t.name=:name and t.pwd=:pwd",
				params);
		if (t != null) {
			return user;
		}
		return null;
	}

	@Override
	public DataGrid datagrid(User user) {
		DataGrid dg = new DataGrid();
		String hql = "from Tuser t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(user, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(user, hql);
		List<Tuser> l = userDao.find(hql, params, user.getPage(),
				user.getRows());
		List<User> nl = new ArrayList<User>();
		changeModel(l, nl);
		dg.setTotal(userDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Tuser> l, List<User> nl) {
		if (l != null && l.size() > 0) {
			for (Tuser t : l) {
				User u = new User();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(User user, String hql) {
		if (user.getSort() != null) {
			hql += " order by " + user.getSort() + " " + user.getOrder();
		}
		return hql;
	}

	private String addWhere(User user, String hql, Map<String, Object> params) {
		// 模糊查询
		if (user.getName() != null && !user.getName().trim().equals("")) {
			hql += "where t.name like :name";
			params.put("name", "%%" + user.getName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String [] nids=ids.split(",");
		String hql="delete Tuser t where t.id in(";
		for(int i=0;i<nids.length;i++){
			if(i>0){
				hql+=",";
			}
			hql+="'"+nids[i]+"'";
		}
		hql+=")";
		userDao.executeHql(hql);
//		for (String id : ids.split(",")) {
//			Tuser u = userDao.get(Tuser.class, id);
//			if (u != null) {
//				userDao.delete(u);
//			}
//		}
	}

	@Override
	public User edit(User user) {
		Tuser t=userDao.get(Tuser.class,user.getId());
		user.setModifydatetime(new Date());
		BeanUtils.copyProperties(user,t,new String[]{"id","pwd"});
		//t.setPwd(Encrypt.e(user.getPwd()));
		
		return user;
	}

	@Override
	public User modifyPwd(User user) {
		Tuser t = userDao.get(Tuser.class, user.getId());
		if (t != null) {
			t.setModifydatetime(new Date());
			t.setPwd(Encrypt.e(user.getPwd()));
		}
		BeanUtils.copyProperties(t,user);
		return user;
	}

}
