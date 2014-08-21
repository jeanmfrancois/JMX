package jmse.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the security_set database table.
 * 
 */
@Entity
@Table(name="security_set")
@NamedQuery(name="SecuritySet.findAll", query="SELECT s FROM SecuritySet s")
public class SecuritySet implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private double profitLoss;
	private List<Security> securities;
	private Simulation simulation;

	public SecuritySet() {
		
	}


	/**
	 * @param profitLoss
	 * @param securities
	 * @param simulation
	 */
	public SecuritySet(double profitLoss, List<Security> securities,
			Simulation simulation) {
		super();
		this.profitLoss = profitLoss;
		this.securities = securities;
		this.simulation = simulation;
	}


	@Id
	@SequenceGenerator(name="SECURITY_SET_ID_GENERATOR", sequenceName="SECURITY_SET_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SECURITY_SET_ID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="profit_loss")
	public double getProfitLoss() {
		return this.profitLoss;
	}

	public void setProfitLoss(double profitLoss) {
		this.profitLoss = profitLoss;
	}


	//bi-directional many-to-one association to Security
	@OneToMany(mappedBy="securitySet", fetch=FetchType.EAGER)
	public List<Security> getSecurities() {
		return this.securities;
	}

	public void setSecurities(List<Security> securities) {
		this.securities = securities;
	}

	public Security addSecurity(Security security) {
		getSecurities().add(security);
		security.setSecuritySet(this);

		return security;
	}

	public Security removeSecurity(Security security) {
		getSecurities().remove(security);
		security.setSecuritySet(null);

		return security;
	}


	//bi-directional one-to-one association to Simulation
	@OneToOne(mappedBy="securitySet", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	public Simulation getSimulation() {
		return this.simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}