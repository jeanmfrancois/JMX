package jmse.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the simulation database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Simulation.findAll", query="SELECT s FROM Simulation s"),
    @NamedQuery(name = "Simulation.findById", query = "SELECT s FROM Simulation s WHERE s.id = :id")})
public class Simulation implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer curDayId;
	private Integer curSegmentId;
	private String description;
	private Integer durationInMinutes;
	private Integer durationInSeconds;
	private Long durationInMilliseconds;
	private Long elapsedDurationInMilliseconds;
	private Boolean isActive;
	private String name;
	private Integer totalDays;
	private Integer totalSegments;
	private Integer totalSimulationTrades;
	private Integer totalTeams;
	private Integer totalUsers;
	
	private List<Segment> segments;
	private SecuritySet securitySet;
	private List<Sponsor> sponsors;
	private List<Team> teams;

	public Simulation() {
		
	}	
	

	/**
	 * @param id
	 * @param curDayId
	 * @param curSegmentId
	 * @param description
	 * @param durationInMinutes
	 * @param durationInSeconds
	 * @param durationInMilliseconds
	 * @param elapsedDurationInMilliseconds
	 * @param isActive
	 * @param name
	 * @param totalDays
	 * @param totalSegments
	 * @param totalSimulationTrades
	 * @param totalTeams
	 * @param totalUsers
	 * @param segments
	 * @param securitySet
	 * @param sponsors
	 * @param teams
	 */
	public Simulation(Integer curDayId, Integer curSegmentId,
			String description, Integer durationInMinutes,
			Integer durationInSeconds, Long durationInMilliseconds, 
			Long elapsedDurationInMilliseconds, Boolean isActive, String name,
			Integer totalDays, Integer totalSegments,
			Integer totalSimulationTrades, Integer totalTeams,
			Integer totalUsers, List<Segment> segments,
			SecuritySet securitySet, List<Sponsor> sponsors, List<Team> teams) {
		super();
		this.curDayId = curDayId;
		this.curSegmentId = curSegmentId;
		this.description = description;
		this.durationInMinutes = durationInMinutes;
		this.durationInSeconds = durationInSeconds;
		this.durationInMilliseconds = durationInMilliseconds;
		this.elapsedDurationInMilliseconds = elapsedDurationInMilliseconds;
		this.isActive = isActive;
		this.name = name;
		this.totalDays = totalDays;
		this.totalSegments = totalSegments;
		this.totalSimulationTrades = totalSimulationTrades;
		this.totalTeams = totalTeams;
		this.totalUsers = totalUsers;
		this.segments = segments;
		this.securitySet = securitySet;
		this.sponsors = sponsors;
		this.teams = teams;
	}

	@Id
	@SequenceGenerator(name="SIMULATION_ID_GENERATOR", sequenceName="SIMULATION_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SIMULATION_ID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="cur_day_id")
	public Integer getCurDayId() {
		return this.curDayId;
	}

	public void setCurDayId(Integer curDayId) {
		this.curDayId = curDayId;
	}


	@Column(name="cur_segment_id")
	public Integer getCurSegmentId() {
		return this.curSegmentId;
	}

	public void setCurSegmentId(Integer curSegmentId) {
		this.curSegmentId = curSegmentId;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="duration_in_minutes")
	public Integer getDurationInMinutes() {
		return this.durationInMinutes;
	}

	public void setDurationInMinutes(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}


	@Column(name="duration_in_seconds")
	public Integer getDurationInSeconds() {
		return this.durationInSeconds;
	}

	public void setDurationInSeconds(Integer durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}
	
	@Column(name="duration_in_milliseconds")
	public Long getDurationInMilliseconds() {
		return this.durationInMilliseconds;
	}

	public void setDurationInMilliseconds(Long durationInMilliseconds) {
		this.durationInMilliseconds = durationInMilliseconds;
	}

	@Column(name="elapsed_duration_in_milliseconds")
	public Long getElapsedDurationInMilliseconds() {
		return elapsedDurationInMilliseconds;
	}
	
	public void setElapsedDurationInMilliseconds(Long elapsedDurationInMilliseconds) {
		this.elapsedDurationInMilliseconds = elapsedDurationInMilliseconds;
	}


	@Column(name="is_active")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name="total_days")
	public Integer getTotalDays() {
		return this.totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}


	@Column(name="total_segments")
	public Integer getTotalSegments() {
		return this.totalSegments;
	}

	public void setTotalSegments(Integer totalSegments) {
		this.totalSegments = totalSegments;
	}


	@Column(name="total_simulation_trades")
	public Integer getTotalSimulationTrades() {
		return this.totalSimulationTrades;
	}

	public void setTotalSimulationTrades(Integer totalSimulationTrades) {
		this.totalSimulationTrades = totalSimulationTrades;
	}


	@Column(name="total_teams")
	public Integer getTotalTeams() {
		return this.totalTeams;
	}

	public void setTotalTeams(Integer totalTeams) {
		this.totalTeams = totalTeams;
	}


	@Column(name="total_users")
	public Integer getTotalUsers() {
		return this.totalUsers;
	}

	public void setTotalUsers(Integer totalUsers) {
		this.totalUsers = totalUsers;
	}


	//bi-directional many-to-one association to Segment
	@OneToMany(mappedBy="simulation", fetch=FetchType.EAGER)
	public List<Segment> getSegments() {
		return this.segments;
	}

	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	public Segment addSegment(Segment segment) {
		getSegments().add(segment);
		segment.setSimulation(this);

		return segment;
	}

	public Segment removeSegment(Segment segment) {
		getSegments().remove(segment);
		segment.setSimulation(null);

		return segment;
	}


	//bi-directional one-to-one association to SecuritySet
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id", insertable=false, updatable=false)
	public SecuritySet getSecuritySet() {
		return this.securitySet;
	}

	public void setSecuritySet(SecuritySet securitySet) {
		this.securitySet = securitySet;
	}


	//bi-directional many-to-many association to Sponsor
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="simulation_sponsor"
		, joinColumns={
			@JoinColumn(name="simulation_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="sponsor_id")
			}
		)
	public List<Sponsor> getSponsors() {
		return this.sponsors;
	}

	public void setSponsors(List<Sponsor> sponsors) {
		this.sponsors = sponsors;
	}


	//bi-directional many-to-many association to Team
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinTable(
		name="simulation_team"
		, joinColumns={
			@JoinColumn(name="simulation_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="team_id")
			}
		)
	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public long getDayDuration() {
		System.out.println("GET DAY PROGRESS... (CALC DURATION)");
		long totalTime = getDurationInMilliseconds();
		long divisions = (long) getTotalDays();
		
		long durationTime = totalTime/divisions;
		/////System.out.println(name + " getdayduration " + durationTime);
		return durationTime;
	}
	
	public long getSegmentDuration() {
		long totalTime = getDurationInMilliseconds();
		long divisions = (long) getTotalSegments();
		
		long durationTime = totalTime/divisions;
		/////System.out.println(name + " getsegmentduration " + durationTime);
		return durationTime;
	}
	
	public int getDayProgress() {
		System.out.println("GET DAY PROGRESS... E");
		long elapsedTime = getElapsedDurationInMilliseconds();
		long unitDuration = getDayDuration();
		long remainingTime = elapsedTime % unitDuration;

		/////System.out.println(name + " remaining " + remainingTime);

		/////System.out.println("remainingTime=  " + remainingTime);
		/////System.out.println("percent=  " + (remainingTime / unitDuration) + "x100 = " + Math.round((remainingTime / unitDuration)*100) );
		
		float progression = (float)remainingTime / (float)unitDuration;
		/////System.out.println("day progression =  " + progression);
		progression*=100;
		/////System.out.println("day progression UNIT =  " + progression);
		
		int progress = Math.round(progression);
		System.out.println(name + " getdaygprogress " + progress);
		return progress;
	}
	
	public int getSegmentProgress() {

		
		long elapsedTime = getElapsedDurationInMilliseconds();
		long unitDuration = getSegmentDuration();
		long remainingTime = elapsedTime % unitDuration;
		/////System.out.println(name + " remaining " + remainingTime);
		
		
		float progression = (float)remainingTime / (float)unitDuration;
		/////System.out.println("seg progression =  " + progression);
		progression*=100;
		/////System.out.println("seg progression UNIT =  " + progression);
		
		int progress = Math.round(progression);
		
		
		
		
		
		return progress;
	}
	
	public int getSimulationProgress() {
		long totalTime = getDurationInMilliseconds();
		long elapsedTime = getElapsedDurationInMilliseconds();
		
		
		float progression = (float)elapsedTime / (float)totalTime;
		/////System.out.println("sim progression =  " + progression);
		progression*=100;
		/////System.out.println("sim progression UNIT =  " + progression);
		
		int progress = Math.round(progression);
		
		return progress;
		
	}
	
public int getCurrentDayCountInSegment() {
		double elapsedTime = this.getElapsedDurationInMilliseconds();
		double curSegment = (double) (getCurrentSegmentCountInSimulation()-1.0);
		double dayDuration = this.getDayDuration();
		double segmentDuration = this.getSegmentDuration();
		double skippedTime = segmentDuration * curSegment;
		double elapsedDayTime = elapsedTime - skippedTime;		
		double curDay = Math.ceil(elapsedDayTime/dayDuration);
		System.out.println("Current day in segment as a double: " + curDay);	
		return (int) curDay;
	}



public int getCurrentSegmentCountInSimulation() {
	double elapsedTime = this.getElapsedDurationInMilliseconds();
	double unitDuration = this.getSegmentDuration();
	double curSegment = Math.ceil(elapsedTime/unitDuration);
	//System.out.println("Current segment as a double: " + curSegment);	
	return (int) curSegment;
}

public int getCurrentDayCountInSimulation() {
	double elapsedTime = this.getElapsedDurationInMilliseconds();
	double unitDuration = this.getDayDuration();
	double curDay = Math.ceil(elapsedTime/unitDuration);
	//System.out.println("Current day as a double: " + curDay);	
	return (int) curDay;
}


	
	

}