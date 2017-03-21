package zxh.model;

import java.sql.Timestamp;
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
 * Tculinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tculinfo", catalog = "mushroom")
public class Tculinfo implements java.io.Serializable {

	// Fields

	private String id;
	private Timestamp culstarttime;
	private String chargeman;
	private String culstate;
	private Timestamp culendtime;
	private Set<TmrCulinfo> tmrCulinfos = new HashSet<TmrCulinfo>(0);
	private Set<TmrvarietyCulinfo> tmrvarietyCulinfos = new HashSet<TmrvarietyCulinfo>(
			0);

	// Constructors

	/** default constructor */
	public Tculinfo() {
	}

	/** minimal constructor */
	public Tculinfo(String id, Timestamp culstarttime, String chargeman,
			String culstate) {
		this.id = id;
		this.culstarttime = culstarttime;
		this.chargeman = chargeman;
		this.culstate = culstate;
	}

	/** full constructor */
	public Tculinfo(String id, Timestamp culstarttime, String chargeman,
			String culstate, Timestamp culendtime, Set<TmrCulinfo> tmrCulinfos,
			Set<TmrvarietyCulinfo> tmrvarietyCulinfos) {
		this.id = id;
		this.culstarttime = culstarttime;
		this.chargeman = chargeman;
		this.culstate = culstate;
		this.culendtime = culendtime;
		this.tmrCulinfos = tmrCulinfos;
		this.tmrvarietyCulinfos = tmrvarietyCulinfos;
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

	@Column(name = "culstarttime", nullable = false, length = 19)
	public Timestamp getCulstarttime() {
		return this.culstarttime;
	}

	public void setCulstarttime(Timestamp culstarttime) {
		this.culstarttime = culstarttime;
	}

	@Column(name = "chargeman", nullable = false, length = 30)
	public String getChargeman() {
		return this.chargeman;
	}

	public void setChargeman(String chargeman) {
		this.chargeman = chargeman;
	}

	@Column(name = "culstate", nullable = false, length = 30)
	public String getCulstate() {
		return this.culstate;
	}

	public void setCulstate(String culstate) {
		this.culstate = culstate;
	}

	@Column(name = "culendtime", length = 19)
	public Timestamp getCulendtime() {
		return this.culendtime;
	}

	public void setCulendtime(Timestamp culendtime) {
		this.culendtime = culendtime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tculinfo")
	public Set<TmrCulinfo> getTmrCulinfos() {
		return this.tmrCulinfos;
	}

	public void setTmrCulinfos(Set<TmrCulinfo> tmrCulinfos) {
		this.tmrCulinfos = tmrCulinfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tculinfo")
	public Set<TmrvarietyCulinfo> getTmrvarietyCulinfos() {
		return this.tmrvarietyCulinfos;
	}

	public void setTmrvarietyCulinfos(Set<TmrvarietyCulinfo> tmrvarietyCulinfos) {
		this.tmrvarietyCulinfos = tmrvarietyCulinfos;
	}

}