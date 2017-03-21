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
import zxh.model.Tcollector;
import zxh.model.Tmonitor;
import zxh.model.TmrCollector;
import zxh.model.TmrMonitor;
import zxh.model.Tmrhouse;
import zxh.pageModel.DataGrid;
import zxh.pageModel.MrHouse;
import zxh.service.MrHouseServiceI;

@Service(value = "mrHouseService")
public class MrHouseServiceImpl implements MrHouseServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(MrHouseServiceImpl.class);
	private BaseDaoI<Tmrhouse> mrHouseDao;
	private BaseDaoI<Tcollector> collectorDao;
	private BaseDaoI<TmrCollector> mrCollectorDao;
	private BaseDaoI<Tmonitor> monitorDao;
	private BaseDaoI<TmrMonitor> mrMonitorDao;
	
	
	public BaseDaoI<TmrMonitor> getMrMonitorDao() {
		return mrMonitorDao;
	}
	@Autowired
	public void setMrMonitorDao(BaseDaoI<TmrMonitor> mrMonitorDao) {
		this.mrMonitorDao = mrMonitorDao;
	}
	public BaseDaoI<Tmonitor> getMonitorDao() {
		return monitorDao;
	}
	@Autowired
	public void setMonitorDao(BaseDaoI<Tmonitor> monitorDao) {
		this.monitorDao = monitorDao;
	}
	public BaseDaoI<TmrCollector> getMrCollectorDao() {
		return mrCollectorDao;
	}
	@Autowired
	public void setMrCollectorDao(BaseDaoI<TmrCollector> mrCollectorDao) {
		this.mrCollectorDao = mrCollectorDao;
	}
	public BaseDaoI<Tcollector> getCollectorDao() {
		return collectorDao;
	}
	@Autowired
	public void setCollectorDao(BaseDaoI<Tcollector> collectorDao) {
		this.collectorDao = collectorDao;
	}
	public BaseDaoI<Tmrhouse> getMrHouseDao() {
		return mrHouseDao;
	}
	@Autowired
	public void setMrHouseDao(BaseDaoI<Tmrhouse> mrHouseDao) {
		this.mrHouseDao = mrHouseDao;
	}

	@Override
	public MrHouse save(MrHouse mrHouse) {
		Tmrhouse t = new Tmrhouse();
		// t的屬性拷貝到MrHouse
		BeanUtils.copyProperties(mrHouse, t);
		t.setId(UUID.randomUUID().toString());
//		logger.info(t.getId());
//		logger.info(t.getMrHouseName());
		//logger.info(mrHouse.getCollectorIds());
		
		mrHouseDao.save(t);
		//操作关联的采集设备表
		if (mrHouse.getCollectorIds() != null) {
			String collectorIds = "";
			String collectorNames = "";
			for (String id : mrHouse.getCollectorIds().split(",")) {
				Tcollector c = collectorDao.get(Tcollector.class, id.trim());
				if (c != null) {
					TmrCollector mc = new TmrCollector();
					mc.setId(UUID.randomUUID().toString());
					mc.setTcollector(c);
					mc.setTmrhouse(t);
					mrCollectorDao.save(mc);

					if (collectorIds.length() > 0) {
						collectorIds += ",";
						collectorNames += ",";
					}
					collectorIds += c.getId();
					collectorNames += c.getCollectorName();
				}
			}
			mrHouse.setCollectorNames(collectorNames);
			mrHouse.setCollectorIds(collectorIds);
		}
		//logger.info(mrHouse.getMonitorIds());
		//操作关联的监控设备表
		if (mrHouse.getMonitorIds() != null) {
			String monitorIds = "";
			String monitorNames = "";
			for (String id : mrHouse.getMonitorIds().split(",")) {
				Tmonitor m = monitorDao.get(Tmonitor.class, id.trim());
				if (m != null) {
					TmrMonitor mc = new TmrMonitor();
					mc.setId(UUID.randomUUID().toString());
					mc.setTmonitor(m);
					mc.setTmrhouse(t);
					mrMonitorDao.save(mc);

					if (monitorIds.length() > 0) {
						monitorIds += ",";
						monitorNames += ",";
					}
					monitorIds += m.getId();
					monitorNames += m.getMonitorName();
				}
			}
			mrHouse.setMonitorNames(monitorNames);
			mrHouse.setMonitorIds(monitorIds);
		}
		
		
		
		BeanUtils.copyProperties(t, mrHouse);
		return mrHouse;
	}
	@Override
	public DataGrid datagrid(MrHouse mrHouse) {
		DataGrid dg = new DataGrid();
		String hql = "from Tmrhouse t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(mrHouse, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(mrHouse, hql);
//		logger.info(hql);
		List<Tmrhouse> l = mrHouseDao.find(hql, params, mrHouse.getPage(),mrHouse.getRows());
		List<MrHouse> nl = new ArrayList<MrHouse>();
		changeModel(l, nl);
		dg.setTotal(mrHouseDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Tmrhouse> l, List<MrHouse> nl) {
		if (l != null && l.size() > 0) {
			for (Tmrhouse t : l) {
				MrHouse m = new MrHouse();
				BeanUtils.copyProperties(t, m);

				Set<TmrCollector> s = t.getTmrCollectors();
				if (s != null && !s.isEmpty()) {
					String collectorIds = "";
					String collectorNames = "";

					for (TmrCollector tt : s) {
						if (collectorIds.length() > 0) {
							collectorIds += ",";
							collectorNames += ",";
						}
						collectorIds += tt.getTcollector().getId();
						collectorNames += tt.getTcollector().getCollectorName();
					}

					m.setCollectorIds(collectorIds);
					m.setCollectorNames(collectorNames);
				}
				
				Set<TmrMonitor> mm = t.getTmrMonitors();
				if (mm != null && !mm.isEmpty()) {
					String monitorIds = "";
					String monitorNames = "";

					for (TmrMonitor tt : mm) {
						if (monitorIds.length() > 0) {
							monitorIds += ",";
							monitorNames += ",";
						}
						monitorIds += tt.getTmonitor().getId();
						monitorNames += tt.getTmonitor().getMonitorName();
					}

					m.setMonitorIds(monitorIds);
					m.setMonitorNames(monitorNames);
				}
				

				nl.add(m);
			}
		}
	}

	private String addOrder(MrHouse mrHouse, String hql) {
		if (mrHouse.getSort() != null) {
			hql += " order by " + mrHouse.getSort() + " " + mrHouse.getOrder();
		}
		return hql;
	}

	private String addWhere(MrHouse mrHouse, String hql, Map<String, Object> params) {
		// 模糊查询
		if (mrHouse.getMrHouseName() != null && !mrHouse.getMrHouseName().trim().equals("")) {
			hql += "where t.name like :name";
			params.put("name", "%%" + mrHouse.getMrHouseName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String [] nids=ids.split(",");
		for (String id : ids.split(",")) {
			Tmrhouse m = mrHouseDao.get(Tmrhouse.class, id);
//			TmrCollector mc=mrCollectorDao.get(TmrCollector.class,id);
			if (m != null) {
				mrHouseDao.delete(m);
			}
		}
	}

	@Override
	public MrHouse edit(MrHouse mrHouse) {
		Tmrhouse t=mrHouseDao.get(Tmrhouse.class,mrHouse.getId());
		BeanUtils.copyProperties(mrHouse,t);
		//t.setPwd(Encrypt.e(mrHouse.getPwd()));
		
		return mrHouse;
	}


}
