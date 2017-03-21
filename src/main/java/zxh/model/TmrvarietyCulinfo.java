package zxh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TmrvarietyCulinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tmrvariety_culinfo", catalog = "mushroom")
public class TmrvarietyCulinfo implements java.io.Serializable {

	// Fields

	private String id;
	private Tculinfo tculinfo;
	private Tmrvariety tmrvariety;

	// Constructors

	/** default constructor */
	public TmrvarietyCulinfo() {
	}

	/** minimal constructor */
	public TmrvarietyCulinfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public TmrvarietyCulinfo(String id, Tculinfo tculinfo, Tmrvariety tmrvariety) {
		this.id = id;
		this.tculinfo = tculinfo;
		this.tmrvariety = tmrvariety;
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
	@JoinColumn(name = "culinfoid")
	public Tculinfo getTculinfo() {
		return this.tculinfo;
	}

	public void setTculinfo(Tculinfo tculinfo) {
		this.tculinfo = tculinfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mrvarityid")
	public Tmrvariety getTmrvariety() {
		return this.tmrvariety;
	}

	public void setTmrvariety(Tmrvariety tmrvariety) {
		this.tmrvariety = tmrvariety;
	}

}