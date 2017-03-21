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
import zxh.model.Tculinfo;
import zxh.model.TmrCulinfo;
import zxh.model.Tmrhouse;
import zxh.model.Tmrvariety;
import zxh.model.TmrvarietyCulinfo;
import zxh.pageModel.Culinfo;
import zxh.pageModel.DataGrid;
import zxh.service.CulinfoServiceI;

@Service(value = "culinfoService")
public class CulinfoServiceImpl implements CulinfoServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CulinfoServiceImpl.class);
	private BaseDaoI<Tculinfo> culinfoDao;
	private BaseDaoI<TmrvarietyCulinfo> mrvarietyCulinfoDao;
	private BaseDaoI<TmrCulinfo> mrCulinfoDao;
	private BaseDaoI<Tmrhouse> mrHouseDao;
	private BaseDaoI<Tmrvariety> mrVarietyDao;
	
	

	public BaseDaoI<Tmrvariety> getMrVarietyDao() {
		return mrVarietyDao;
	}
	@Autowired
	public void setMrVarietyDao(BaseDaoI<Tmrvariety> mrVarietyDao) {
		this.mrVarietyDao = mrVarietyDao;
	}
	public BaseDaoI<Tculinfo> getCulinfoDao() {
		return culinfoDao;
	}
	@Autowired
	public void setCulinfoDao(BaseDaoI<Tculinfo> culinfoDao) {
		this.culinfoDao = culinfoDao;
	}
	public BaseDaoI<TmrvarietyCulinfo> getMrvarietyCulinfoDao() {
		return mrvarietyCulinfoDao;
	}
	@Autowired
	public void setMrvarietyCulinfoDao(
			BaseDaoI<TmrvarietyCulinfo> mrvarietyCulinfoDao) {
		this.mrvarietyCulinfoDao = mrvarietyCulinfoDao;
	}
	public BaseDaoI<Tmrhouse> getMrHouseDao() {
		return mrHouseDao;
	}
	@Autowired
	public void setMrHouseDao(BaseDaoI<Tmrhouse> mrHouseDao) {
		this.mrHouseDao = mrHouseDao;
	}
	public BaseDaoI<TmrCulinfo> getMrCulinfoDao() {
		return mrCulinfoDao;
	}
	@Autowired
	public void setMrCulinfoDao(BaseDaoI<TmrCulinfo> mrCulinfoDao) {
		this.mrCulinfoDao = mrCulinfoDao;
	}


	@Override
	public Culinfo save(Culinfo culinfo) {
		Tculinfo t = new Tculinfo();
		BeanUtils.copyProperties(culinfo, t);
		t.setId(UUID.randomUUID().toString());
		t.setCulstate("未运行");
		culinfoDao.save(t);
		
		//操作关联的菇房表
		if (culinfo.getMrHouseIds() != null) {
			String mrHouseIds = "";
			String mrHouseNames = "";
			for (String id : culinfo.getMrHouseIds().split(",")) {
				Tmrhouse r = mrHouseDao.get(Tmrhouse.class, id);
				if (r != null) {
					TmrCulinfo rr = new TmrCulinfo();
					rr.setId(UUID.randomUUID().toString());
					rr.setTmrhouse(r);
					rr.setTculinfo(t);
					mrCulinfoDao.save(rr);

					if (mrHouseNames.length() > 0) {
						mrHouseNames += ",";
					}
					mrHouseIds+=r.getId();
					mrHouseNames += r.getMrHouseName();
				}
			}
			culinfo.setMrHouseNames(mrHouseNames);
			culinfo.setMrHouseIds(mrHouseIds);
		}
		//操作关联的食用菌品种表
		if (culinfo.getMrvarietyIds() != null) {
			String mrVarietyIds = "";
			String mrVarietyNames = "";
			for (String id : culinfo.getMrvarietyIds().split(",")) {
				Tmrvariety r = mrVarietyDao.get(Tmrvariety.class, id);
				if (r != null) {
					TmrvarietyCulinfo rr = new TmrvarietyCulinfo();
					rr.setId(UUID.randomUUID().toString());
					rr.setTmrvariety(r);
					rr.setTculinfo(t);
					mrvarietyCulinfoDao.save(rr);

					if (mrVarietyNames.length() > 0) {
						mrVarietyNames += ",";
					}
					mrVarietyIds+=r.getId();
					mrVarietyNames += r.getMushroomname();
				}
			}
			culinfo.setMrvarietyNames(mrVarietyNames);
			culinfo.setMrvarietyIds(mrVarietyIds);
		}		
		//culinfo.setId(t.getId());
		BeanUtils.copyProperties(t, culinfo);
		return culinfo;
	}
	@Override
	public DataGrid datagrid(Culinfo culinfo) {
		DataGrid dg = new DataGrid();
		String hql = "from Tculinfo t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(culinfo, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(culinfo, hql);
		//logger.info(hql);
		List<Tculinfo> l = culinfoDao.find(hql, params, culinfo.getPage(),culinfo.getRows());
		List<Culinfo> nl = new ArrayList<Culinfo>();
		changeModel(l, nl);
		dg.setTotal(culinfoDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<Tculinfo> l, List<Culinfo> nl) {
		if (l != null && l.size() > 0) {
			for (Tculinfo t : l) {
				Culinfo m = new Culinfo();
				BeanUtils.copyProperties(t, m);

				Set<TmrCulinfo> s = t.getTmrCulinfos();
				if (s != null && !s.isEmpty()) {
					String mrHouseIds = "";
					String mrHouseNames = "";

					for (TmrCulinfo tt : s) {
						if (mrHouseIds.length() > 0) {
							mrHouseIds += ",";
							mrHouseNames += ",";
						}
						mrHouseIds += tt.getTmrhouse().getId();
						mrHouseNames += tt.getTmrhouse().getMrHouseName();
					}

					m.setMrHouseIds(mrHouseIds);
					m.setMrHouseNames(mrHouseNames);;
				}
				
				Set<TmrvarietyCulinfo> mm = t.getTmrvarietyCulinfos();
				if (mm != null && !mm.isEmpty()) {
					String mrvarietyIds = "";
					String mrvarietyNames = "";

					for (TmrvarietyCulinfo tt : mm) {
						if (mrvarietyIds.length() > 0) {
							mrvarietyIds += ",";
							mrvarietyNames += ",";
						}
						mrvarietyIds += tt.getTmrvariety().getId();
						mrvarietyNames += tt.getTmrvariety().getMushroomname();
					}

					m.setMrvarietyIds(mrvarietyIds);
					m.setMrvarietyNames(mrvarietyNames);;
				}
				

				nl.add(m);
			}
		}
	}

	private String addOrder(Culinfo culinfo, String hql) {
		if (culinfo.getSort() != null) {
			hql += "order by " + culinfo.getSort() + " " + culinfo.getOrder();
		}
		return hql;
	}

	private String addWhere(Culinfo culinfo, String hql, Map<String, Object> params) {
		// 模糊查询
		if (culinfo.getMrHouseNames() != null && !culinfo.getMrHouseNames().trim().equals("")) {
			hql += "where t.name like :name";
			params.put("name", "%%" + culinfo.getMrHouseNames().trim() + "%%");
		}
		return hql;
	}

	@Override
	public void remove(String ids) {
		String [] nids=ids.split(",");
		for (String id : ids.split(",")) {
			Tculinfo m = culinfoDao.get(Tculinfo.class, id);
//			TmrCollector mc=mrCollectorDao.get(TmrCollector.class,id);
			if (m != null) {
				culinfoDao.delete(m);
			}
		}
//		String [] nids=ids.split(",");
//		String hql="delete Tculinfo t where t.id in(";
//		for(int j=0;j<nids.length;j++){
//			Tculinfo t=culinfoDao.get(Tculinfo.class,nids[j]);
//			if("栽培中".equals(t.getCulstate())){
//				return false;
//			}
//				
//		}
//		for(int i=0;i<nids.length;i++){
//			if(i>0){
//				hql+=",";
//			}
//			hql+="'"+nids[i]+"'";
//		}
//		hql+=")";
//		culinfoDao.executeHql(hql);
	}

	@Override
	public Culinfo edit(Culinfo culinfo) {
		Tculinfo t=culinfoDao.get(Tculinfo.class,culinfo.getId());
		BeanUtils.copyProperties(culinfo,t);
		//t.setPwd(Encrypt.e(culinfo.getPwd()));
		
		return culinfo;
	}


}
