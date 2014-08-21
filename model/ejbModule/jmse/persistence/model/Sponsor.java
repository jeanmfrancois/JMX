package jmse.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the sponsor database table.
 * 
 */
@Entity
@NamedQuery(name="Sponsor.findAll", query="SELECT s FROM Sponsor s")
public class Sponsor implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String description;
	private String logoIcon;
	private String logoLarge;
	private String logoSmall;
	private String name;
	private String phoneNumber1;
	private String phoneNumber2;
	private List<Simulation> simulations;

	public Sponsor() {
		
	}


	/**
	 * @param description
	 * @param logoIcon
	 * @param logoLarge
	 * @param logoSmall
	 * @param name
	 * @param phoneNumber1
	 * @param phoneNumber2
	 * @param simulations
	 */
	public Sponsor(String description, String logoIcon, String logoLarge,
			String logoSmall, String name, String phoneNumber1,
			String phoneNumber2, List<Simulation> simulations) {
		super();
		this.description = description;
		this.logoIcon = logoIcon;
		this.logoLarge = logoLarge;
		this.logoSmall = logoSmall;
		this.name = name;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.simulations = simulations;
	}


	@Id
	@SequenceGenerator(name="SPONSOR_ID_GENERATOR", sequenceName="SPONSOR_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SPONSOR_ID_GENERATOR")
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


	@Column(name="logo_icon")
	public String getLogoIcon() {
		return this.logoIcon;
	}

	public void setLogoIcon(String logoIcon) {
		this.logoIcon = logoIcon;
	}


	@Column(name="logo_large")
	public String getLogoLarge() {
		return this.logoLarge;
	}

	public void setLogoLarge(String logoLarge) {
		this.logoLarge = logoLarge;
	}


	@Column(name="logo_small")
	public String getLogoSmall() {
		return this.logoSmall;
	}

	public void setLogoSmall(String logoSmall) {
		this.logoSmall = logoSmall;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name="phone_number_1")
	public String getPhoneNumber1() {
		return this.phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}


	@Column(name="phone_number_2")
	public String getPhoneNumber2() {
		return this.phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}


	//bi-directional many-to-many association to Simulation
	@ManyToMany(mappedBy="sponsors", fetch=FetchType.EAGER)
	public List<Simulation> getSimulations() {
		return this.simulations;
	}

	public void setSimulations(List<Simulation> simulations) {
		this.simulations = simulations;
	}

}