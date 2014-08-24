package jmse.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the jmse_user database table.
 * 
 */
@Entity
@Table(name="jmse_user")
@NamedQuery(name="JmseUser.findAll", query="SELECT j FROM JmseUser j")
public class JmseUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private double balance;
	private Boolean canTrade;
	private String emailAddress;
	private String firstName;
	private Boolean hasAllergies;
	private Boolean hasTeam;
	private Boolean isActive;
	private String lastName;
	private String password;
	private String phoneNumber1;
	private String phoneNumber2;
	private Integer roleId;
	private String schoolName;
	private Integer teamRank;
	private Integer totalUsers;
	private Integer userRank;
	private String username;
	private String curCalcOpenCloseFifo;
	private Role role;
	private List<Team> teams;
	private EmergencyContact emergencyContact;

	public JmseUser() {
	}


	/**
	 * @param balance
	 * @param canTrade
	 * @param emailAddress
	 * @param firstName
	 * @param hasAllergies
	 * @param hasTeam
	 * @param isActive
	 * @param lastName
	 * @param password
	 * @param phoneNumber1
	 * @param phoneNumber2
	 * @param roleId
	 * @param schoolName
	 * @param teamRank
	 * @param totalUsers
	 * @param userRank
	 * @param username
	 * @param role
	 * @param teams
	 * @param emergencyContact
	 */
	public JmseUser(double balance, Boolean canTrade, String emailAddress,
			String firstName, Boolean hasAllergies, Boolean hasTeam,
			Boolean isActive, String lastName, String password,
			String phoneNumber1, String phoneNumber2, Integer roleId,
			String schoolName, Integer teamRank, Integer totalUsers,
			Integer userRank, String username, Role role, List<Team> teams,
			EmergencyContact emergencyContact) {
		super();
		this.balance = balance;
		this.canTrade = canTrade;
		this.emailAddress = emailAddress;
		this.firstName = firstName;
		this.hasAllergies = hasAllergies;
		this.hasTeam = hasTeam;
		this.isActive = isActive;
		this.lastName = lastName;
		this.password = password;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.roleId = roleId;
		this.schoolName = schoolName;
		this.teamRank = teamRank;
		this.totalUsers = totalUsers;
		this.userRank = userRank;
		this.username = username;
		this.role = role;
		this.teams = teams;
		this.emergencyContact = emergencyContact;
	}


	@Id
	@SequenceGenerator(name="JMSE_USER_ID_GENERATOR", sequenceName="JMSE_USER_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JMSE_USER_ID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	@Column(name="can_trade")
	public Boolean getCanTrade() {
		return this.canTrade;
	}

	public void setCanTrade(Boolean canTrade) {
		this.canTrade = canTrade;
	}


	@Column(name="email_address")
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	@Column(name="first_name")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Column(name="has_allergies")
	public Boolean getHasAllergies() {
		return this.hasAllergies;
	}

	public void setHasAllergies(Boolean hasAllergies) {
		this.hasAllergies = hasAllergies;
	}


	@Column(name="has_team")
	public Boolean getHasTeam() {
		return this.hasTeam;
	}

	public void setHasTeam(Boolean hasTeam) {
		this.hasTeam = hasTeam;
	}


	@Column(name="is_active")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	@Column(name="last_name")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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


	@Column(name="role_id")
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	@Column(name="school_name")
	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	@Column(name="team_rank")
	public Integer getTeamRank() {
		return this.teamRank;
	}

	public void setTeamRank(Integer teamRank) {
		this.teamRank = teamRank;
	}


	@Column(name="total_users")
	public Integer getTotalUsers() {
		return this.totalUsers;
	}

	public void setTotalUsers(Integer totalUsers) {
		this.totalUsers = totalUsers;
	}


	@Column(name="user_rank")
	public Integer getUserRank() {
		return this.userRank;
	}

	public void setUserRank(Integer userRank) {
		this.userRank = userRank;
	}


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="cur_calc_open_close_fifo")
	public String getCurCalcOpenCloseFifo() {
		return this.curCalcOpenCloseFifo;
	}

	public void setCurCalcOpenCloseFifo(String curCalcOpenCloseFifo) {
		this.curCalcOpenCloseFifo = curCalcOpenCloseFifo;
	}

	
	

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	//bi-directional many-to-many association to Team
	@ManyToMany(mappedBy="jmseUsers", fetch=FetchType.EAGER)
	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}


	//bi-directional one-to-one association to EmergencyContact
	@OneToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	public EmergencyContact getEmergencyContact() {
		return this.emergencyContact;
	}

	public void setEmergencyContact(EmergencyContact emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	
	/*
	   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	   	public ArrayList<Double> getActualPriceYCollection(Integer securityId) {
	   		Security sec = getSecurityById(securityId);
	   		ArrayList<Double> arrayList = new ArrayList<Double>();
	   		try {
	   			arrayList = Conversion.stringToDoubleArrayList(sec.getActualPriceYCollection());
	   		} 
	   		catch(Exception e) {
	   			System.out.println("Error processing array from database for getActualPriceYCollection()");
	   		}
	   		return arrayList;
	   	}
	   	
	       @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	   	public void setActualPriceYCollection(Integer securityId, ArrayList<Double> actualPriceYCollection) {
	   		Security sec = getSecurityById(securityId);
	   		try {
	   	    	sec.setActualPriceYCollection(Conversion.doubleArrayListToString(actualPriceYCollection));
	   		} 
	   		catch(Exception e) {
	   			System.out.println("Error processing array from database for setActualPriceYCollection()");
	   		}
	   	}
 */
}