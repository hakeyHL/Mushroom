package zxh.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;

@ParentPackage(value = "basePackage")
@Namespace("/")
//@Action(value = "userAction")
public class BaseAction {
	//写往前台信息进行Json封装
	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType(
					"text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
