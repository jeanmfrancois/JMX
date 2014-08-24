package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T13:21:50.488-0500")
@StaticMetamodel(JmseUser.class)
public class JmseUser_ {
	public static volatile SingularAttribute<JmseUser, Integer> id;
	public static volatile SingularAttribute<JmseUser, Boolean> canTrade;
	public static volatile SingularAttribute<JmseUser, String> emailAddress;
	public static volatile SingularAttribute<JmseUser, String> firstName;
	public static volatile SingularAttribute<JmseUser, Boolean> hasAllergies;
	public static volatile SingularAttribute<JmseUser, Boolean> hasTeam;
	public static volatile SingularAttribute<JmseUser, Boolean> isActive;
	public static volatile SingularAttribute<JmseUser, String> lastName;
	public static volatile SingularAttribute<JmseUser, String> phoneNumber1;
	public static volatile SingularAttribute<JmseUser, String> phoneNumber2;
	public static volatile SingularAttribute<JmseUser, Integer> roleId;
	public static volatile SingularAttribute<JmseUser, String> schoolName;
	public static volatile SingularAttribute<JmseUser, Integer> teamRank;
	public static volatile SingularAttribute<JmseUser, Integer> totalUsers;
	public static volatile SingularAttribute<JmseUser, Integer> userRank;
	public static volatile SingularAttribute<JmseUser, Role> role;
	public static volatile ListAttribute<JmseUser, Team> teams;
	public static volatile SingularAttribute<JmseUser, EmergencyContact> emergencyContact;
	public static volatile SingularAttribute<JmseUser, Double> balance;
	public static volatile SingularAttribute<JmseUser, String> password;
	public static volatile SingularAttribute<JmseUser, String> username;
}
