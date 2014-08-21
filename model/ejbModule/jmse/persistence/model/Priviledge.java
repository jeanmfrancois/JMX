package jmse.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the priviledge database table.
 * 
 */
@Entity
@NamedQuery(name="Priviledge.findAll", query="SELECT p FROM Priviledge p")
public class Priviledge implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private Boolean hasPriviledge;
	private String name;
	private List<Role> roles;

	public Priviledge() {
		
	}


	/**
	 * @param description
	 * @param hasPriviledge
	 * @param name
	 * @param roles
	 */
	public Priviledge(String description, Boolean hasPriviledge, String name,
			List<Role> roles) {
		super();
		this.description = description;
		this.hasPriviledge = hasPriviledge;
		this.name = name;
		this.roles = roles;
	}


	@Id
	@SequenceGenerator(name="PRIVILEDGE_ID_GENERATOR", sequenceName="PRIVILEDGE_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRIVILEDGE_ID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="has_priviledge")
	public Boolean getHasPriviledge() {
		return this.hasPriviledge;
	}

	public void setHasPriviledge(Boolean hasPriviledge) {
		this.hasPriviledge = hasPriviledge;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="priviledges", fetch=FetchType.EAGER)
	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}