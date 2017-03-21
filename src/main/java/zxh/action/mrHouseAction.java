package zxh.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import zxh.pageModel.MrHouse;
import zxh.pageModel.Json;
import zxh.service.MrHouseServiceI;

import com.opensymphony.xwork2.ModelDriven;

//@ParentPackage(value = "basePackage")
@Namespace("/")
@Action(value = "mrHouseAction")
public class mrHouseAction<T> extends BaseAction implements ModelDriven<MrHouse>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(mrHouseAction.class);
	
	MrHouse mrHouse =new MrHouse();
	private MrHouseServiceI mrHouseService;
	@Autowired
	public MrHouseServiceI getMrHouseService() {
		return mrHouseService;
	}
	public void setMrHouseService(MrHouseServiceI mrHouseService) {
		this.mrHouseService = mrHouseService;
	}
	public void add(){
//		logger.info(mrHouse.getMrHouseName());
//		logger.info(mrHouse.getMrHouseState());
		Json j=new Json();
		try {
			MrHouse u=mrHouseService.save(mrHouse);
			j.setSuccess(true);
			j.setMsg("增加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	@Override
	public MrHouse getModel() {
		return mrHouse;
	}
	public void datagrid(){
		super.writeJson(mrHouseService.datagrid(mrHouse));
	}
	public void remove(){
		Json j=new Json();
		try{
			mrHouseService.remove(mrHouse.getIds());
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	public void edit(){
		MrHouse u=mrHouseService.edit(mrHouse);
		Json j=new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(u);
		super.writeJson(j);
	}
	public void combogrid() {
		super.writeJson(mrHouseService.datagrid(mrHouse));
	}
}
