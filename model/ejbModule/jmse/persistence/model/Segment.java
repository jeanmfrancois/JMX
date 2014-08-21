package jmse.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the segment database table.
 * 
 */
@Entity
@NamedQuery(name="Segment.findAll", query="SELECT s FROM Segment s")
public class Segment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer totalSegmentTrades;
	private List<Day> days;
	private Simulation simulation;

	public Segment() {
		
	}


	/**
	 * @param totalSegmentTrades
	 * @param days
	 * @param simulation
	 */
	public Segment(Integer totalSegmentTrades, List<Day> days,
			Simulation simulation) {
		super();
		this.totalSegmentTrades = totalSegmentTrades;
		this.days = days;
		this.simulation = simulation;
	}


	@Id
	@SequenceGenerator(name="SEGMENT_ID_GENERATOR", sequenceName="SEGMENT_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEGMENT_ID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="total_segment_trades")
	public Integer getTotalSegmentTrades() {
		return this.totalSegmentTrades;
	}

	public void setTotalSegmentTrades(Integer totalSegmentTrades) {
		this.totalSegmentTrades = totalSegmentTrades;
	}


	//bi-directional many-to-one association to Day
	@OneToMany(mappedBy="segment", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	public List<Day> getDays() {
		return this.days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	public Day addDay(Day day) {
		getDays().add(day);
		day.setSegment(this);

		return day;
	}

	public Day removeDay(Day day) {
		getDays().remove(day);
		day.setSegment(null);

		return day;
	}


	//bi-directional many-to-one association to Simulation
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	public Simulation getSimulation() {
		return this.simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}