package jmse.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.*;


/**
 * The persistent class for the security database table.
 * 
 */

@Entity
@Table(name="Security")
@NamedQueries({
	@NamedQuery(name="Security.findAll", query="SELECT s FROM Security s"),
	@NamedQuery(name = "Security.findById", query = "SELECT s FROM Security s WHERE s.id = :id")})

public class Security implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private double curPrice;
	private double prevPrice;
	private String description;
	private String name;
	private String symbol;
	private Simulation simulation;
	private Integer simulationId;
	private String color;

	
	
	//added columns to database: Collections
	private String actualPriceXCollection;
	private String actualPriceYCollection;
	private String closePriceOnDayCollection;
	private String contractsCountOnDayCollection;
	private String highPriceOnDayCollection;
	private String lowPriceOnDayCollection;
	private String avgPriceOnDayCollection;
	private String openPriceOnDayCollection;
	private String projectedPlotPointsY;
	
	
	
	//added columns to database: value stores
	private Integer maxContractsPerDay;	
	private Integer minContractsPerDay;
	private double minPricePerDay;
	private double maxPricePerDay;
	private double lowPricePerDay;
	private double highPricePerDay;
	private double diffPricePerDay;
	private double deviation;
	
	
	
	public Security() {
		
	}


	/**
	 * @param curPrice
	 * @param description
	 * @param name
	 * @param symbol
	 * @param securitySet
	 */
	public Security(double curPrice, double prevPrice, String description, String name,
			String symbol, Simulation simulation) {
		super();
		
		this.curPrice = curPrice;
		this.prevPrice = prevPrice;
		this.description = description;
		this.name = name;
		this.symbol = symbol;
		this.simulation = simulation;
	}


	@Id
	@SequenceGenerator(name="SECURITY_ID_GENERATOR", sequenceName="SECURITY_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SECURITY_ID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	
	@Column(name="symbol")
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	

	@Column(name="projected_plot_points_y_collection")
	public String getProjectedPlotPointsYCollection() {
		return projectedPlotPointsY;
	}

	public void setProjectedPlotPointsYCollection(String projectedPlotPointsY) {
		this.projectedPlotPointsY = projectedPlotPointsY;
	}
	
	@Column(name="close_price_on_day_collection")
	public String getClosePriceOnDayCollection() {
		return this.closePriceOnDayCollection;
	}

	public void setClosePriceOnDayCollection(String closePriceOnDayCollection) {
		this.closePriceOnDayCollection = closePriceOnDayCollection;
	}

	@Column(name="contracts_count_on_day_collection")
	public String getContractsCountOnDayCollection() {
		return this.contractsCountOnDayCollection;
	}

	public void setContractsCountOnDayCollection(String newBaseObject) {
		this.contractsCountOnDayCollection = newBaseObject;
	}


	@Column(name="cur_price")
	public double getCurPrice() {
		return this.curPrice;
	}

	public void setCurPrice(double curPrice) {
		this.curPrice = curPrice;
	}
	
	@Column(name="deviation")
	public double getDeviation() {
		return this.deviation;
	}

	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}

	@Column(name="description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="high_price_on_day_collection")
	public String getHighPriceOnDayCollection() {
		return this.highPriceOnDayCollection;
	}

	public void setHighPriceOnDayCollection(String highPriceOnDayCollection) {
		this.highPriceOnDayCollection = highPriceOnDayCollection;
	}


	@Column(name="low_price_on_day_collection")
	public String getLowPriceOnDayCollection() {
		return this.lowPriceOnDayCollection;
	}

	public void setLowPriceOnDayCollection(String lowPriceOnDayCollection) {
		this.lowPriceOnDayCollection = lowPriceOnDayCollection;
	}


	@Column(name="max_contracts_per_day")
	public Integer getMaxContractsPerDay() {
		return this.maxContractsPerDay;
	}

	public void setMaxContractsPerDay(Integer maxContractsPerDay) {
		this.maxContractsPerDay = maxContractsPerDay;
	}


	@Column(name="max_price_per_day")
	public double getMaxPricePerDay() {
		return this.maxPricePerDay;
	}

	public void setMaxPricePerDay(double maxPricePerDay) {
		this.maxPricePerDay = maxPricePerDay;
	}


	@Column(name="min_contracts_per_day")
	public Integer getMinContractsPerDay() {
		return this.minContractsPerDay;
	}

	public void setMinContractsPerDay(Integer minContractsPerDay) {
		this.minContractsPerDay = minContractsPerDay;
	}


	@Column(name="min_price_per_day")
	public double getMinPricePerDay() {
		return this.minPricePerDay;
	}

	public void setMinPricePerDay(double minPricePerDay) {
		this.minPricePerDay = minPricePerDay;
	}

	@Column(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name="open_price_on_day_collection")
	public String getOpenPriceOnDayCollection() {
		return this.openPriceOnDayCollection;
	}

	public void setOpenPriceOnDayCollection(String openPriceOnDayCollection) {
		this.openPriceOnDayCollection = openPriceOnDayCollection;
	}
	
	@Column(name="actual_price_x_collection")
	public String getActualPriceXCollection() {
		return this.actualPriceXCollection;
	}

	public void setActualPriceXCollection(String actualPriceXCollection) {
		this.actualPriceXCollection = actualPriceXCollection;
	}
	
	@Column(name="actual_price_y_collection")
	public String getActualPriceYCollection() {
		return this.actualPriceYCollection;
	}

	public void setActualPriceYCollection(String actualPriceYCollection) {
		this.actualPriceYCollection = actualPriceYCollection;
	}
	
	@Column(name="avg_price_on_day_collection")
	public String getAvgPriceOnDayCollection() {
		return avgPriceOnDayCollection;
	}


	public void setAvgPriceOnDayCollection(String avgPriceOnDayCollection) {
		this.avgPriceOnDayCollection = avgPriceOnDayCollection;
	}



	@Column(name="prev_price")
	public double getPrevPrice() {
		return this.prevPrice;
	}

	public void setPrevPrice(double prevPrice) {
		this.prevPrice = prevPrice;
	}


	//bi-directional many-to-one association to Simulation
	@ManyToOne(cascade = { PERSIST, MERGE, REFRESH })
	@JoinColumn(name="simulation_id")
	public Simulation getSimulation() {
		return this.simulation;
	}

	public void setSimulation (Simulation simulation) {
		this.simulation = simulation;
	}


	@Column(name="low_price_per_day")
	public double getLowPricePerDay() {
		return lowPricePerDay;
	}


	
	public void setLowPricePerDay(double lowPricePerDay) {
		this.lowPricePerDay = lowPricePerDay;
	}


	@Column(name="high_price_per_day")
	public double getHighPricePerDay() {
		return highPricePerDay;
	}


	
	public void setHighPricePerDay(double highPricePerDay) {
		this.highPricePerDay = highPricePerDay;
	}

	@Column(name="diff_price_per_day")
	public double getDiffPricePerDay() {
		return diffPricePerDay;
	}


	public void setDiffPricePerDay(double diffPricePerDay) {
		this.diffPricePerDay = diffPricePerDay;
	}
	
	@Column(name="color")
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	

	
}