package jmse.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private String name;
	private List<JmseUser> jmseUsers;
	private List<Priviledge> priviledges;

	public Role() {
		
	}

	
	/**
	 * @param description
	 * @param name
	 * @param jmseUsers
	 * @param priviledges
	 */
	public Role(String description, String name, List<JmseUser> jmseUsers,
			List<Priviledge> priviledges) {
		super();
		this.description = description;
		this.name = name;
		this.jmseUsers = jmseUsers;
		this.priviledges = priviledges;
	}


	@Id
	@SequenceGenerator(name="ROLE_ID_GENERATOR", sequenceName="ROLE_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_ID_GENERATOR")
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


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to JmseUser
	@OneToMany(mappedBy="role", fetch=FetchType.EAGER)
	public List<JmseUser> getJmseUsers() {
		return this.jmseUsers;
	}

	public void setJmseUsers(List<JmseUser> jmseUsers) {
		this.jmseUsers = jmseUsers;
	}

	public JmseUser addJmseUser(JmseUser jmseUser) {
		getJmseUsers().add(jmseUser);
		jmseUser.setRole(this);

		return jmseUser;
	}

	public JmseUser removeJmseUser(JmseUser jmseUser) {
		getJmseUsers().remove(jmseUser);
		jmseUser.setRole(null);

		return jmseUser;
	}


	//bi-directional many-to-many association to Priviledge
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="role_priviledge"
		, joinColumns={
			@JoinColumn(name="role_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="priviledge_id")
			}
		)
	public List<Priviledge> getPriviledges() {
		return this.priviledges;
	}

	public void setPriviledges(List<Priviledge> priviledges) {
		this.priviledges = priviledges;
	}

}