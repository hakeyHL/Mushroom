package zxh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zxh.dao.BaseDaoI;
import zxh.model.TmrCollector;
import zxh.model.Tmrvariety;
import zxh.model.Tvariety;
import zxh.model.TvarietyMrv;
import zxh.pageModel.DataGrid;
import zxh.pageModel.Variety;
import zxh.service.VarietyServiceI;

@Service(value = "varietyService")
public class VarietyServiceImpl implements VarietyServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(VarietyServiceImpl.class);
	private BaseDaoI<Tvariety> varietyDao;
	private BaseDaoI<Tmrvariety> mrvarietyDao;
	private BaseDaoI<TvarietyMrv> varietyMrvDao;

	public BaseDaoI<TvarietyMrv> getVarietyMrvDao() {
		return varietyMrvDao;
	}
	@Autowired
	public void setVarietyMrvDao(BaseDaoI<TvarietyMrv> varietyMrvDao) {
		this.varietyMrvDao = varietyMrvDao;
	}
	public BaseDaoI<Tmrvariety> getMrvarietyDao() {
		return mrvarietyDao;
	}
	@Autowired
	public void setMrvarietyDao(BaseDaoI<Tmrvariety> mrvarietyDao) {
		this.mrvarietyDao = mrvarietyDao;
	}
	public BaseDaoI<Tvariety> getVarietyDao() {
		return varietyDao;
	}
	@Autowired
	public void setVarietyDao(BaseDaoI<Tvariety> varietyDao) {
		this.varietyDao = varietyDao;
	}

	@Override
	public Variety save(Variety variety) {
		Tvariety t = new Tvariety();
		BeanUtils.copyProperties(variety, t);
		t.setId(UUID.randomUUID().toString());
		varietyDao.save(t);
		if (variety.getMrVarietyIds() != null) {
			String mrVarietyIds = "";
			String mrVarietyNames = "";
			for (String id : variety.getMrVarietyIds().split(",")) {
				Tmrvariety c = mrvarietyDao.get(Tmrvariety.class, id.trim());
				if (c != null) {
					TvarietyMrv mc = new TvarietyMrv();
					mc.setId(UUID.randomUUID().toString());
					mc.setTmrvariety(c);
					mc.setTvariety(t);
					varietyMrvDao.save(mc);

					if (mrVarietyIds.length() > 0) {
						mrVarietyIds += ",";
						mrVarietyNames += ",";
					}
					mrVarietyIds += c.getId();
					mrVarietyNames += c.getMushroomname();
				}
			}
			variety.setMrVarietyIds(mrVarietyIds);
			variety.setMrVarietyNames(mrVarietyNames);
		}

		return variety;
	}

	@Override
	public DataGrid datagrid(Variety variety) {
		DataGrid dg = new DataGrid();
		String hql = "from Tvariety t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(variety, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(variety, hql);
//		logger.info(hql);
		List<Tvariety> l = varietyDao.find(hql, params, variety.getPage(),variety.getRows());
		List<Variety> nl = new ArrayList<Variety>();
		changeModel(l, nl);
		dg.setTotal(varietyDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Tvariety> l, List<Variety> nl) {
		if (l != null && l.size() > 0) {
			for (Tvariety t : l) {
				Variety u = new Variety();
				BeanUtils.copyProperties(t, u);
				
				Set<TvarietyMrv> s = t.getTvarietyMrvs();
				if (s != null && !s.isEmpty()) {
					String mrVarietyIds = "";
					String mrVarietyNames = "";

					for (TvarietyMrv tt : s) {
						if (mrVarietyIds.length() > 0) {
							mrVarietyIds += ",";
							mrVarietyNames += ",";
						}
						mrVarietyIds += tt.getTmrvariety().getId();
						mrVarietyNames += tt.getTmrvariety().getMushroomname();
					}

					u.setMrVarietyIds(mrVarietyIds);
					u.setMrVarietyNames(mrVarietyNames);
				}
				
				nl.add(u);
			}
		}
	}

	private String addOrder(Variety variety, String hql) {
		if (variety.getSort() != null) {
			hql += " order by " + variety.getSort() + " " + variety.getOrder();
		}
		return hql;
	}

	private String addWhere(Variety variety, String hql, Map<String, Object> params) {
		// 模糊查询
		if (variety.getCuldays() != null && !variety.getCuldays() .trim().equals("")) {
			hql += "where t.name like :name";
			params.put("name", "%%" + variety.getCuldays() .trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String [] nids=ids.split(",");
		String hql="delete Tvariety t where t.id in(";
		for(int i=0;i<nids.length;i++){
			if(i>0){
				hql+=",";
			}
			hql+="'"+nids[i]+"'";
		}
		hql+=")";
		varietyDao.executeHql(hql);
	}

	@Override
	public Variety edit(Variety variety) {
		Tvariety t=varietyDao.get(Tvariety.class,variety.getId());
		BeanUtils.copyProperties(variety,t);
		
		return variety;
	}


}
