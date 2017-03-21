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
 * Tmrhouse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tmrhouse", catalog = "mushroom")
public class Tmrhouse implements java.io.Serializable {

	// Fields

	private String id;
	private String mrHouseName;
	private String mrHouseState;
	private Set<TmrMonitor> tmrMonitors = new HashSet<TmrMonitor>(0);
	private Set<TmrCollector> tmrCollectors = new HashSet<TmrCollector>(0);

	// Constructors

	/** default constructor */
	public Tmrhouse() {
	}

	/** minimal constructor */
	public Tmrhouse(String id, String mrHouseName) {
		this.id = id;
		this.mrHouseName = mrHouseName;
	}

	/** full constructor */
	public Tmrhouse(String id, String mrHouseName, String mrHouseState,
			Set<TmrMonitor> tmrMonitors, Set<TmrCollector> tmrCollectors) {
		this.id = id;
		this.mrHouseName = mrHouseName;
		this.mrHouseState = mrHouseState;
		this.tmrMonitors = tmrMonitors;
		this.tmrCollectors = tmrCollectors;
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

	@Column(name = "mrHouseName",unique = true, nullable = false, length = 30)
	public String getMrHouseName() {
		return this.mrHouseName;
	}

	public void setMrHouseName(String mrHouseName) {
		this.mrHouseName = mrHouseName;
	}

	@Column(name = "mrHouseState")
	public String getMrHouseState() {
		return this.mrHouseState;
	}

	public void setMrHouseState(String mrHouseState) {
		this.mrHouseState = mrHouseState;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tmrhouse")
	public Set<TmrMonitor> getTmrMonitors() {
		return this.tmrMonitors;
	}

	public void setTmrMonitors(Set<TmrMonitor> tmrMonitors) {
		this.tmrMonitors = tmrMonitors;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tmrhouse")
	public Set<TmrCollector> getTmrCollectors() {
		return this.tmrCollectors;
	}

	public void setTmrCollectors(Set<TmrCollector> tmrCollectors) {
		this.tmrCollectors = tmrCollectors;
	}

}