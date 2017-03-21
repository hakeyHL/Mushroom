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
import zxh.model.Tmonitor;
import zxh.model.TmrCollector;
import zxh.model.TmrMonitor;
import zxh.model.Tmrhouse;
import zxh.pageModel.DataGrid;
import zxh.pageModel.Monitor;
import zxh.service.MonitorServiceI;

@Service(value = "monitorService")
public class MonitorServiceImpl implements MonitorServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(MonitorServiceImpl.class);
	private BaseDaoI<Tmonitor> monitorDao;
	private BaseDaoI<Tmrhouse> mrHouseDao;
	private BaseDaoI<TmrMonitor> mrMonitorDao;

	
	public BaseDaoI<TmrMonitor> getMrMonitorDao() {
		return mrMonitorDao;
	}
	@Autowired
	public void setMrMonitorDao(BaseDaoI<TmrMonitor> mrMonitorDao) {
		this.mrMonitorDao = mrMonitorDao;
	}

	public BaseDaoI<Tmrhouse> getMrHouseDao() {
		return mrHouseDao;
	}

	@Autowired
	public void setMrHouseDao(BaseDaoI<Tmrhouse> mrHouseDao) {
		this.mrHouseDao = mrHouseDao;
	}

	public BaseDaoI<Tmonitor> getMonitorDao() {
		return monitorDao;
	}
	@Autowired
	public void setMonitorDao(BaseDaoI<Tmonitor> monitorDao) {
		this.monitorDao = monitorDao;
	}

	@Override
	public Monitor save(Monitor monitor) {
		Tmonitor t = new Tmonitor();
		// t的屬性拷貝到Monitor
		BeanUtils.copyProperties(monitor, t);
		t.setId(UUID.randomUUID().toString());
		monitorDao.save(t);
		if (monitor.getMrHouseIds() != null) {
			String mrHouseNames = "";
			for (String id : monitor.getMrHouseIds().split(",")) {
				Tmrhouse r = mrHouseDao.get(Tmrhouse.class, id);
				if (r != null) {
					TmrMonitor rr = new TmrMonitor();
					rr.setId(UUID.randomUUID().toString());
					rr.setTmrhouse(r);
					rr.setTmonitor(t);
					mrMonitorDao.save(rr);

					if (mrHouseNames.length() > 0) {
						mrHouseNames += ",";
					}
					mrHouseNames += r.getMrHouseName();
				}
			}
			monitor.setMrHouseNames(mrHouseNames);
		}
		monitor.setId(t.getId());
		
		// BeanUtils.copyProperties(t, monitor);
		monitor.setId(t.getId());
		return monitor;
	}

	@Override
	public DataGrid datagrid(Monitor monitor) {
		DataGrid dg = new DataGrid();
		String hql = "from Tmonitor t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(monitor, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(monitor, hql);
		List<Tmonitor> l = monitorDao.find(hql, params, monitor.getPage(),
				monitor.getRows());
		List<Monitor> nl = new ArrayList<Monitor>();
		changeModel(l, nl);
		dg.setTotal(monitorDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Tmonitor> l, List<Monitor> nl) {
		if (l != null && l.size() > 0) {
			for (Tmonitor t : l) {
				Monitor u = new Monitor();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(Monitor monitor, String hql) {
		if (monitor.getSort() != null) {
			hql += " order by " + monitor.getSort() + " " + monitor.getOrder();
		}
		return hql;
	}

	private String addWhere(Monitor monitor, String hql,
			Map<String, Object> params) {
		// 模糊查询
		if (monitor.getMonitorName() != null
				&& !monitor.getMonitorName().trim().equals("")) {
			hql += "where t.name like :name";
			params.put("name", "%%" + monitor.getMonitorName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String[] nids = ids.split(",");
		String hql = "delete Tmonitor t where t.id in(";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		monitorDao.executeHql(hql);
		// for (String id : ids.split(",")) {
		// Tmonitor u = monitorDao.get(Tmonitor.class, id);
		// if (u != null) {
		// monitorDao.delete(u);
		// }
		// }
	}

	@Override
	public Monitor edit(Monitor monitor) {
		Tmonitor t = monitorDao.get(Tmonitor.class, monitor.getId());
		BeanUtils.copyProperties(monitor, t);
		// t.setPwd(Encrypt.e(monitor.getPwd()));

		return monitor;
	}

}
