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
 * Tvariety entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tvariety", catalog = "mushroom")
public class Tvariety implements java.io.Serializable {

	// Fields

	private String id;
	private String culdays;
	private Integer maxtemp;
	private Integer mintemp;
	private Integer maxhumi;
	private Integer minhumi;
	private Integer maxco2;
	private Integer minco2;
	private Integer ligthtime;
	private Set<TvarietyMrv> tvarietyMrvs = new HashSet<TvarietyMrv>(0);

	// Constructors

	/** default constructor */
	public Tvariety() {
	}

	/** minimal constructor */
	public Tvariety(String id, String culdays, Integer maxtemp,
			Integer mintemp, Integer maxhumi, Integer minhumi, Integer maxco2,
			Integer minco2, Integer ligthtime) {
		this.id = id;
		this.culdays = culdays;
		this.maxtemp = maxtemp;
		this.mintemp = mintemp;
		this.maxhumi = maxhumi;
		this.minhumi = minhumi;
		this.maxco2 = maxco2;
		this.minco2 = minco2;
		this.ligthtime = ligthtime;
	}

	/** full constructor */
	public Tvariety(String id, String culdays, Integer maxtemp,
			Integer mintemp, Integer maxhumi, Integer minhumi, Integer maxco2,
			Integer minco2, Integer ligthtime, Set<TvarietyMrv> tvarietyMrvs) {
		this.id = id;
		this.culdays = culdays;
		this.maxtemp = maxtemp;
		this.mintemp = mintemp;
		this.maxhumi = maxhumi;
		this.minhumi = minhumi;
		this.maxco2 = maxco2;
		this.minco2 = minco2;
		this.ligthtime = ligthtime;
		this.tvarietyMrvs = tvarietyMrvs;
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

	@Column(name = "culdays", nullable = false, length = 8)
	public String getCuldays() {
		return this.culdays;
	}

	public void setCuldays(String culdays) {
		this.culdays = culdays;
	}

	@Column(name = "maxtemp", nullable = false)
	public Integer getMaxtemp() {
		return this.maxtemp;
	}

	public void setMaxtemp(Integer maxtemp) {
		this.maxtemp = maxtemp;
	}

	@Column(name = "mintemp", nullable = false)
	public Integer getMintemp() {
		return this.mintemp;
	}

	public void setMintemp(Integer mintemp) {
		this.mintemp = mintemp;
	}

	@Column(name = "maxhumi", nullable = false)
	public Integer getMaxhumi() {
		return this.maxhumi;
	}

	public void setMaxhumi(Integer maxhumi) {
		this.maxhumi = maxhumi;
	}

	@Column(name = "minhumi", nullable = false)
	public Integer getMinhumi() {
		return this.minhumi;
	}

	public void setMinhumi(Integer minhumi) {
		this.minhumi = minhumi;
	}

	@Column(name = "maxco2", nullable = false)
	public Integer getMaxco2() {
		return this.maxco2;
	}

	public void setMaxco2(Integer maxco2) {
		this.maxco2 = maxco2;
	}

	@Column(name = "minco2", nullable = false)
	public Integer getMinco2() {
		return this.minco2;
	}

	public void setMinco2(Integer minco2) {
		this.minco2 = minco2;
	}

	@Column(name = "ligthtime", nullable = false)
	public Integer getLigthtime() {
		return this.ligthtime;
	}

	public void setLigthtime(Integer ligthtime) {
		this.ligthtime = ligthtime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tvariety")
	public Set<TvarietyMrv> getTvarietyMrvs() {
		return this.tvarietyMrvs;
	}

	public void setTvarietyMrvs(Set<TvarietyMrv> tvarietyMrvs) {
		this.tvarietyMrvs = tvarietyMrvs;
	}

}