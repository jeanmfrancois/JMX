package jmse.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private String name;
	private Integer teamRank;
	private Integer totalTeams;
	private List<Simulation> simulations;
	private List<JmseUser> jmseUsers;

	public Team() {
		
	}


	/**
	 * @param description
	 * @param name
	 * @param teamRank
	 * @param totalTeams
	 * @param simulations
	 * @param jmseUsers
	 */
	public Team(String description, String name, Integer teamRank,
			Integer totalTeams, List<Simulation> simulations,
			List<JmseUser> jmseUsers) {
		super();
		this.description = description;
		this.name = name;
		this.teamRank = teamRank;
		this.totalTeams = totalTeams;
		this.simulations = simulations;
		this.jmseUsers = jmseUsers;
	}


	@Id
	@SequenceGenerator(name="TEAM_ID_GENERATOR", sequenceName="TEAM_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEAM_ID_GENERATOR")
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


	@Column(name="team_rank")
	public Integer getTeamRank() {
		return this.teamRank;
	}

	public void setTeamRank(Integer teamRank) {
		this.teamRank = teamRank;
	}


	@Column(name="total_teams")
	public Integer getTotalTeams() {
		return this.totalTeams;
	}

	public void setTotalTeams(Integer totalTeams) {
		this.totalTeams = totalTeams;
	}


	//bi-directional many-to-many association to Simulation
	@ManyToMany(mappedBy="teams", fetch=FetchType.EAGER)
	public List<Simulation> getSimulations() {
		return this.simulations;
	}

	public void setSimulations(List<Simulation> simulations) {
		this.simulations = simulations;
	}


	//bi-directional many-to-many association to JmseUser
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="team_jmse_user"
		, joinColumns={
			@JoinColumn(name="team_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="jmse_user_id")
			}
		)
	public List<JmseUser> getJmseUsers() {
		return this.jmseUsers;
	}

	public void setJmseUsers(List<JmseUser> jmseUsers) {
		this.jmseUsers = jmseUsers;
	}

}