package zxh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TvarietyMrv entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tvariety_mrv", catalog = "mushroom")
public class TvarietyMrv implements java.io.Serializable {

	// Fields

	private String id;
	private Tmrvariety tmrvariety;
	private Tvariety tvariety;

	// Constructors

	/** default constructor */
	public TvarietyMrv() {
	}

	/** full constructor */
	public TvarietyMrv(String id, Tmrvariety tmrvariety, Tvariety tvariety) {
		this.id = id;
		this.tmrvariety = tmrvariety;
		this.tvariety = tvariety;
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
	@JoinColumn(name = "mrvid", nullable = false)
	public Tmrvariety getTmrvariety() {
		return this.tmrvariety;
	}

	public void setTmrvariety(Tmrvariety tmrvariety) {
		this.tmrvariety = tmrvariety;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "varityid", nullable = false)
	public Tvariety getTvariety() {
		return this.tvariety;
	}

	public void setTvariety(Tvariety tvariety) {
		this.tvariety = tvariety;
	}

}