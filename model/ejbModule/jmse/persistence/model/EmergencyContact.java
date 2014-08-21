package jmse.persistence.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the emergency_contact database table.
 * 
 */
@Entity
@Table(name="emergency_contact")
@NamedQuery(name="EmergencyContact.findAll", query="SELECT e FROM EmergencyContact e")
public class EmergencyContact implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String phoneNumber1;
	private String phoneNumber2;
	private JmseUser jmseUser;

	public EmergencyContact() {
		
	}


	/**
	 * @param name
	 * @param phoneNumber1
	 * @param phoneNumber2
	 * @param jmseUser
	 */
	public EmergencyContact(String name, String phoneNumber1,
			String phoneNumber2, JmseUser jmseUser) {
		super();
		this.name = name;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.jmseUser = jmseUser;
	}


	@Id
	@SequenceGenerator(name="EMERGENCY_CONTACT_ID_GENERATOR", sequenceName="EMERGENCY_CONTACT_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMERGENCY_CONTACT_ID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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


	//bi-directional one-to-one association to JmseUser
	@OneToOne(mappedBy="emergencyContact")
	public JmseUser getJmseUser() {
		return this.jmseUser;
	}

	public void setJmseUser(JmseUser jmseUser) {
		this.jmseUser = jmseUser;
	}

}