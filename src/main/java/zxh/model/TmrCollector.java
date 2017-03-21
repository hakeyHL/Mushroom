package zxh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TmrCollector entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tmr_collector", catalog = "mushroom")
public class TmrCollector implements java.io.Serializable {

	// Fields

	private String id;
	private Tcollector tcollector;
	private Tmrhouse tmrhouse;

	// Constructors

	/** default constructor */
	public TmrCollector() {
	}

	/** minimal constructor */
	public TmrCollector(String id) {
		this.id = id;
	}

	/** full constructor */
	public TmrCollector(String id, Tcollector tcollector, Tmrhouse tmrhouse) {
		this.id = id;
		this.tcollector = tcollector;
		this.tmrhouse = tmrhouse;
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
	@JoinColumn(name = "collector_id")
	public Tcollector getTcollector() {
		return this.tcollector;
	}

	public void setTcollector(Tcollector tcollector) {
		this.tcollector = tcollector;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mrHouse_id")
	public Tmrhouse getTmrhouse() {
		return this.tmrhouse;
	}

	public void setTmrhouse(Tmrhouse tmrhouse) {
		this.tmrhouse = tmrhouse;
	}

}