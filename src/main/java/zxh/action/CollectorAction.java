package zxh.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import zxh.pageModel.Collector;
import zxh.pageModel.DataGrid;
import zxh.pageModel.Json;
import zxh.service.CollectorServiceI;

import com.opensymphony.xwork2.ModelDriven;

//@ParentPackage(value = "basePackage")
@Namespace("/")
@Action(value = "collectorAction")
public class CollectorAction<T> extends BaseAction implements ModelDriven<Collector>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CollectorAction.class);
	
	Collector collector =new Collector();
	private CollectorServiceI collectorService;
	@Autowired
	public CollectorServiceI getCollectorService() {
		return collectorService;
	}
	public void setCollectorService(CollectorServiceI collectorService) {
		this.collectorService = collectorService;
	}
	public void add(){
//		logger.info(collector.getCollectorName());
//		logger.info(collector.getCollectorIp());
		Json j=new Json();
		try {
			Collector u=collectorService.save(collector);
			j.setSuccess(true);
			j.setMsg("增加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	@Override
	public Collector getModel() {
		return collector;
	}
	public void datagrid(){
		super.writeJson(collectorService.datagrid(collector));
	}
	public void remove(){
		Json j=new Json();
		
//		//logger.info(collector.getNames());
//		String [] names=collector.getCollectorName().split(",");
//		for(int i=0;i<names.length;i++){
//			if(names[i].equals("zxh")){
//				j.setSuccess(false);
//				j.setMsg("用户\"zxh\"不能删除！");
//				super.writeJson(j);
//				return;
//			}
//		}
		collectorService.remove(collector.getIds());
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}
	public void edit(){
		Collector u=collectorService.edit(collector);
		Json j=new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(u);
		super.writeJson(j);
	}
	public void combogrid() {
		super.writeJson(collectorService.datagrid(collector));
	}
}
