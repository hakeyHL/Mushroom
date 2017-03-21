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
 * Tmrvariety entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tmrvariety", catalog = "mushroom")
public class Tmrvariety implements java.io.Serializable {

	// Fields

	private String id;
	private String mushroomname;
	private Integer culduration;
	private Set<TvarietyMrv> tvarietyMrvs = new HashSet<TvarietyMrv>(0);

	// Constructors

	/** default constructor */
	public Tmrvariety() {
	}

	/** minimal constructor */
	public Tmrvariety(String id, String mushroomname, Integer culduration) {
		this.id = id;
		this.mushroomname = mushroomname;
		this.culduration = culduration;
	}

	/** full constructor */
	public Tmrvariety(String id, String mushroomname, Integer culduration,
			Set<TvarietyMrv> tvarietyMrvs) {
		this.id = id;
		this.mushroomname = mushroomname;
		this.culduration = culduration;
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

	@Column(name = "mushroomname", nullable = false, length = 30)
	public String getMushroomname() {
		return this.mushroomname;
	}

	public void setMushroomname(String mushroomname) {
		this.mushroomname = mushroomname;
	}

	@Column(name = "culduration", nullable = false)
	public Integer getCulduration() {
		return this.culduration;
	}

	public void setCulduration(Integer culduration) {
		this.culduration = culduration;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tmrvariety")
	public Set<TvarietyMrv> getTvarietyMrvs() {
		return this.tvarietyMrvs;
	}

	public void setTvarietyMrvs(Set<TvarietyMrv> tvarietyMrvs) {
		this.tvarietyMrvs = tvarietyMrvs;
	}

}