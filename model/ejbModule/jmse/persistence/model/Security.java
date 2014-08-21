package jmse.persistence.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the security database table.
 * 
 */
@Entity
@NamedQuery(name="Security.findAll", query="SELECT s FROM Security s")
public class Security implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private double curPrice;
	private double prevPrice;
	private String description;
	private String name;
	private String symbol;
	private SecuritySet securitySet;

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
			String symbol, SecuritySet securitySet) {
		super();
		
		this.curPrice = curPrice;
		this.prevPrice = prevPrice;
		this.description = description;
		this.name = name;
		this.symbol = symbol;
		this.securitySet = securitySet;
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


	@Column(name="cur_price")
	public double getCurPrice() {
		return this.curPrice;
	}

	public void setCurPrice(double curPrice) {
		this.curPrice = curPrice;
	}
	
	@Column(name="prev_price")
	public double getPrevPrice() {
		return this.prevPrice;
	}

	public void setPrevPrice(double prevPrice) {
		this.prevPrice = prevPrice;
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


	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	//bi-directional many-to-one association to SecuritySet
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	public SecuritySet getSecuritySet() {
		return this.securitySet;
	}

	public void setSecuritySet(SecuritySet securitySet) {
		this.securitySet = securitySet;
	}

}