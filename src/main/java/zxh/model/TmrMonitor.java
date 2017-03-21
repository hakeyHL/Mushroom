package zxh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TmrMonitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tmr_monitor", catalog = "mushroom")
public class TmrMonitor implements java.io.Serializable {

	// Fields

	private String id;
	private Tmrhouse tmrhouse;
	private Tmonitor tmonitor;

	// Constructors

	/** default constructor */
	public TmrMonitor() {
	}

	/** minimal constructor */
	public TmrMonitor(String id) {
		this.id = id;
	}

	/** full constructor */
	public TmrMonitor(String id, Tmrhouse tmrhouse, Tmonitor tmonitor) {
		this.id = id;
		this.tmrhouse = tmrhouse;
		this.tmonitor = tmonitor;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mrHouse_id")
	public Tmrhouse getTmrhouse() {
		return this.tmrhouse;
	}

	public void setTmrhouse(Tmrhouse tmrhouse) {
		this.tmrhouse = tmrhouse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "monitor_id")
	public Tmonitor getTmonitor() {
		return this.tmonitor;
	}

	public void setTmonitor(Tmonitor tmonitor) {
		this.tmonitor = tmonitor;
	}

}