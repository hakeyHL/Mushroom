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
 * Tcollector entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tcollector", catalog = "mushroom")
public class Tcollector implements java.io.Serializable {

	// Fields

	private String id;
	private String collectorIp;
	private String collectorName;
	private Set<TmrCollector> tmrCollectors = new HashSet<TmrCollector>(0);

	// Constructors

	/** default constructor */
	public Tcollector() {
	}

	/** minimal constructor */
	public Tcollector(String id, String collectorIp, String collectorName) {
		this.id = id;
		this.collectorIp = collectorIp;
		this.collectorName = collectorName;
	}

	/** full constructor */
	public Tcollector(String id, String collectorIp, String collectorName,
			Set<TmrCollector> tmrCollectors) {
		this.id = id;
		this.collectorIp = collectorIp;
		this.collectorName = collectorName;
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

	@Column(name = "collectorIP", nullable = false, length = 20)
	public String getCollectorIp() {
		return this.collectorIp;
	}

	public void setCollectorIp(String collectorIp) {
		this.collectorIp = collectorIp;
	}

	@Column(name = "collectorName", nullable = false, length = 30)
	public String getCollectorName() {
		return this.collectorName;
	}

	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tcollector")
	public Set<TmrCollector> getTmrCollectors() {
		return this.tmrCollectors;
	}

	public void setTmrCollectors(Set<TmrCollector> tmrCollectors) {
		this.tmrCollectors = tmrCollectors;
	}

}