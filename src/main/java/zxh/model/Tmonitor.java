package zxh.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tmonitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tmonitor", catalog = "mushroom")
public class Tmonitor implements java.io.Serializable {

	// Fields

	private String id;
	private String monitorIp;
	private String monitorName;
	private Set<TmrMonitor> tmrMonitors = new HashSet<TmrMonitor>(0);

	// Constructors

	/** default constructor */
	public Tmonitor() {
	}

	/** minimal constructor */
	public Tmonitor(String id, String monitorIp, String monitorName) {
		this.id = id;
		this.monitorIp = monitorIp;
		this.monitorName = monitorName;
	}

	/** full constructor */
	public Tmonitor(String id, String monitorIp, String monitorName,
			Set<TmrMonitor> tmrMonitors) {
		this.id = id;
		this.monitorIp = monitorIp;
		this.monitorName = monitorName;
		this.tmrMonitors = tmrMonitors;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "monitorIP", nullable = false, length = 20)
	public String getMonitorIp() {
		return this.monitorIp;
	}

	public void setMonitorIp(String monitorIp) {
		this.monitorIp = monitorIp;
	}

	@Column(name = "monitorName", nullable = false, length = 30)
	public String getMonitorName() {
		return this.monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tmonitor")
	public Set<TmrMonitor> getTmrMonitors() {
		return this.tmrMonitors;
	}

	public void setTmrMonitors(Set<TmrMonitor> tmrMonitors) {
		this.tmrMonitors = tmrMonitors;
	}

}