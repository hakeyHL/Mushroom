package zxh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zxh.dao.BaseDaoI;
import zxh.model.TmrCollector;
import zxh.model.Tmrhouse;
import zxh.model.Tmrvariety;
import zxh.pageModel.DataGrid;
import zxh.pageModel.MrVariety;
import zxh.service.MrVarietyServiceI;

@Service(value = "mrVarietyService")
public class MrVarietyServiceImpl implements MrVarietyServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(MrVarietyServiceImpl.class);
	private BaseDaoI<Tmrvariety> mrVarietyDao;
	
	public BaseDaoI<Tmrvariety> getMrVarietyDao() {
		return mrVarietyDao;
	}
	@Autowired
	public void setMrVarietyDao(BaseDaoI<Tmrvariety> mrVarietyDao) {
		this.mrVarietyDao = mrVarietyDao;
	}

	@Override
	public MrVariety save(MrVariety mrVariety) {
		Tmrvariety t = new Tmrvariety();
		BeanUtils.copyProperties(mrVariety, t);
		t.setId(UUID.randomUUID().toString());
		mrVarietyDao.save(t);
//		if (mrVariety.getMrHouseIds() != null) {
//			String mrHouseNames = "";
//			for (String id : mrVariety.getMrHouseIds().split(",")) {
//				Tmrhouse r = mrHouseDao.get(Tmrhouse.class, id);
//				if (r != null) {
//					TmrCollector rr = new TmrCollector();
//					rr.setId(UUID.randomUUID().toString());
//					rr.setTmrhouse(r);
//					rr.setTmrVariety(t);
//					mrCollectorDao.save(rr);
//
//					if (mrHouseNames.length() > 0) {
//						mrHouseNames += ",";
//					}
//					mrHouseNames += r.getMrHouseName();
//				}
//			}
//			mrVariety.setMrHouseNames(mrHouseNames);
//		}
//		mrVariety.setId(t.getId());
		return mrVariety;
	}

	@Override
	public DataGrid datagrid(MrVariety mrVariety) {
		DataGrid dg = new DataGrid();
		String hql = "from Tmrvariety t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(mrVariety, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(mrVariety, hql);
		//logger.info(hql);
		List<Tmrvariety> l = mrVarietyDao.find(hql, params, mrVariety.getPage(),mrVariety.getRows());
		List<MrVariety> nl = new ArrayList<MrVariety>();
		changeModel(l, nl);
		dg.setTotal(mrVarietyDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Tmrvariety> l, List<MrVariety> nl) {
		if (l != null && l.size() > 0) {
			for (Tmrvariety t : l) {
				MrVariety u = new MrVariety();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(MrVariety mrVariety, String hql) {
		if (mrVariety.getSort() != null) {
			hql += "order by " + mrVariety.getSort() + " " + mrVariety.getOrder();
		}
		return hql;
	}

	private String addWhere(MrVariety mrVariety, String hql, Map<String, Object> params) {
		// 模糊查询
		if (mrVariety.getMushroomname() != null && !mrVariety.getMushroomname() .trim().equals("")) {
			hql += "where t.name like :name";
			params.put("name", "%%" + mrVariety.getMushroomname() .trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String [] nids=ids.split(",");
		String hql="delete Tmrvariety t where t.id in(";
		for(int i=0;i<nids.length;i++){
			if(i>0){
				hql+=",";
			}
			hql+="'"+nids[i]+"'";
		}
		hql+=")";
		mrVarietyDao.executeHql(hql);
//		for (String id : ids.split(",")) {
//			Tcollector u = collectorDao.get(Tcollector.class, id);
//			if (u != null) {
//				collectorDao.delete(u);
//			}
//		}
	}

	@Override
	public MrVariety edit(MrVariety mrVariety) {
		Tmrvariety t=mrVarietyDao.get(Tmrvariety.class,mrVariety.getId());
		BeanUtils.copyProperties(mrVariety,t);
		
		return mrVariety;
	}


}
