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
import zxh.model.Tcollector;
import zxh.model.TmrCollector;
import zxh.model.Tmrhouse;
import zxh.pageModel.Collector;
import zxh.pageModel.DataGrid;
import zxh.service.CollectorServiceI;

@Service(value = "collectorService")
public class CollectorServiceImpl implements CollectorServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CollectorServiceImpl.class);
	private BaseDaoI<Tcollector> collectorDao;
	private BaseDaoI<Tmrhouse> mrHouseDao;
	private BaseDaoI<TmrCollector> mrCollectorDao;

	
	public BaseDaoI<TmrCollector> getMrCollectorDao() {
		return mrCollectorDao;
	}
	@Autowired
	public void setMrCollectorDao(BaseDaoI<TmrCollector> mrCollectorDao) {
		this.mrCollectorDao = mrCollectorDao;
	}
	public BaseDaoI<Tmrhouse> getMrHouseDao() {
		return mrHouseDao;
	}
	@Autowired
	public void setMrHouseDao(BaseDaoI<Tmrhouse> mrHouseDao) {
		this.mrHouseDao = mrHouseDao;
	}

	public BaseDaoI<Tcollector> getCollectorDao() {
		return collectorDao;
	}
	@Autowired
	public void setCollectorDao(BaseDaoI<Tcollector> collectorDao) {
		this.collectorDao = collectorDao;
	}

	@Override
	public Collector save(Collector collector) {
		Tcollector t = new Tcollector();
		BeanUtils.copyProperties(collector, t);
		t.setId(UUID.randomUUID().toString());
		collectorDao.save(t);
//		if (collector.getMrHouseIds() != null) {
//			String mrHouseNames = "";
//			for (String id : collector.getMrHouseIds().split(",")) {
//				Tmrhouse r = mrHouseDao.get(Tmrhouse.class, id);
//				if (r != null) {
//					TmrCollector rr = new TmrCollector();
//					rr.setId(UUID.randomUUID().toString());
//					rr.setTmrhouse(r);
//					rr.setTcollector(t);
//					mrCollectorDao.save(rr);
//
//					if (mrHouseNames.length() > 0) {
//						mrHouseNames += ",";
//					}
//					mrHouseNames += r.getMrHouseName();
//				}
//			}
//			collector.setMrHouseNames(mrHouseNames);
//		}
		collector.setId(t.getId());
		return collector;
	}
//		Tcollector t = new Tcollector();
//		// t的屬性拷貝到Collector
//		BeanUtils.copyProperties(collector, t);
//		logger.info(t.getId());
//		logger.info(t.getCollectorIp());
//		logger.info(t.getCollectorIp());
//		t.setId(UUID.randomUUID().toString());
//		collectorDao.save(t);
//		logger.info(t.getId());
////		BeanUtils.copyProperties(t, collector);
//		collector.setId(t.getId());
//		return collector;
//	}
	@Override
	public DataGrid datagrid(Collector collector) {
		DataGrid dg = new DataGrid();
		String hql = "from Tcollector t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(collector, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(collector, hql);
		//logger.info(hql);
		List<Tcollector> l = collectorDao.find(hql, params, collector.getPage(),collector.getRows());
		List<Collector> nl = new ArrayList<Collector>();
		changeModel(l, nl);
		dg.setTotal(collectorDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Tcollector> l, List<Collector> nl) {
		if (l != null && l.size() > 0) {
			for (Tcollector t : l) {
				Collector u = new Collector();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(Collector collector, String hql) {
		if (collector.getSort() != null) {
			hql += "order by " + collector.getSort() + " " + collector.getOrder();
		}
		return hql;
	}

	private String addWhere(Collector collector, String hql, Map<String, Object> params) {
		// 模糊查询
		if (collector.getCollectorName() != null && !collector.getCollectorName().trim().equals("")) {
			hql += "where t.name like :name";
			params.put("name", "%%" + collector.getCollectorName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String [] nids=ids.split(",");
		String hql="delete Tcollector t where t.id in(";
		for(int i=0;i<nids.length;i++){
			if(i>0){
				hql+=",";
			}
			hql+="'"+nids[i]+"'";
		}
		hql+=")";
		collectorDao.executeHql(hql);
//		for (String id : ids.split(",")) {
//			Tcollector u = collectorDao.get(Tcollector.class, id);
//			if (u != null) {
//				collectorDao.delete(u);
//			}
//		}
	}

	@Override
	public Collector edit(Collector collector) {
		Tcollector t=collectorDao.get(Tcollector.class,collector.getId());
		BeanUtils.copyProperties(collector,t);
		//t.setPwd(Encrypt.e(collector.getPwd()));
		
		return collector;
	}


}
