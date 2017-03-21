package zxh.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;


@Entity
//@Table(name="tuser",schema="mushroom", uniqueConstraints=@UniqueConstraint(columnNames="name"))
@Table(name="tuser",schema="mushroom")
public class Tuser implements Serializable {
	private String id;
	private String name;
	private String pwd;
	private String truename;
	private String usertype;
	private String tel;
	private Date createdatetime;
	private Date modifydatetime;
	public Tuser(){
		
	}
	public Tuser(String id, String name, String pwd, String truename,
			String usertype, String tel, Date createdatetime,
			Date modifydatetime) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.truename = truename;
		this.usertype = usertype;
		this.tel = tel;
		this.createdatetime = createdatetime;
		this.modifydatetime = modifydatetime;
	}
	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name="id",nullable=false,length=36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "name",unique = true, nullable = false, length = 30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "pwd", nullable = false, length = 32)
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdatetime", length = 7)
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifydatetime", length = 7)
	public Date getModifydatetime() {
		return modifydatetime;
	}
	public void setModifydatetime(Date modifydatetime) {
		this.modifydatetime = modifydatetime;
	}

}
