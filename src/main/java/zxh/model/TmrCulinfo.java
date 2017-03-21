package zxh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TmrCulinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tmr_culinfo", catalog = "mushroom")
public class TmrCulinfo implements java.io.Serializable {

	// Fields

	private String id;
	private Tmrhouse tmrhouse;
	private Tculinfo tculinfo;

	// Constructors

	/** default constructor */
	public TmrCulinfo() {
	}

	/** minimal constructor */
	public TmrCulinfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public TmrCulinfo(String id, Tmrhouse tmrhouse, Tculinfo tculinfo) {
		this.id = id;
		this.tmrhouse = tmrhouse;
		this.tculinfo = tculinfo;
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
	@JoinColumn(name = "mrHouseid")
	public Tmrhouse getTmrhouse() {
		return this.tmrhouse;
	}

	public void setTmrhouse(Tmrhouse tmrhouse) {
		this.tmrhouse = tmrhouse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mrculinfoid")
	public Tculinfo getTculinfo() {
		return this.tculinfo;
	}

	public void setTculinfo(Tculinfo tculinfo) {
		this.tculinfo = tculinfo;
	}

}