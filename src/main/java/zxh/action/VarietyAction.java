package zxh.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import zxh.pageModel.Variety;
import zxh.pageModel.DataGrid;
import zxh.pageModel.Json;
import zxh.service.VarietyServiceI;

import com.opensymphony.xwork2.ModelDriven;

//@ParentPackage(value = "basePackage")
@Namespace("/")
@Action(value = "varietyAction")
public class VarietyAction<T> extends BaseAction implements ModelDriven<Variety>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(VarietyAction.class);
	
	Variety variety =new Variety();
	private VarietyServiceI varietyService;
	@Autowired
	public VarietyServiceI getVarietyService() {
		return varietyService;
	}
	public void setVarietyService(VarietyServiceI varietyService) {
		this.varietyService = varietyService;
	}
	public void add(){
//		logger.info(variety.getVarietyName());
//		logger.info(variety.getVarietyIp());
		Json j=new Json();
		try {
			Variety u=varietyService.save(variety);
			j.setSuccess(true);
			j.setMsg("增加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	@Override
	public Variety getModel() {
		return variety;
	}
	public void datagrid(){
		super.writeJson(varietyService.datagrid(variety));
	}
	public void remove(){
		Json j=new Json();
		try{
			varietyService.remove(variety.getIds());
			j.setSuccess(true);
			j.setMsg("删除成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	public void edit(){
		Variety u=varietyService.edit(variety);
		Json j=new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(u);
		super.writeJson(j);
	}
	public void combogrid() {
		super.writeJson(varietyService.datagrid(variety));
	}
}
