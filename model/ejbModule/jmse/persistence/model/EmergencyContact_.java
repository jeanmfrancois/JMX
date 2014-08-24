package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T13:21:50.483-0500")
@StaticMetamodel(EmergencyContact.class)
public class EmergencyContact_ {
	public static volatile SingularAttribute<EmergencyContact, Integer> id;
	public static volatile SingularAttribute<EmergencyContact, String> phoneNumber1;
	public static volatile SingularAttribute<EmergencyContact, String> phoneNumber2;
	public static volatile SingularAttribute<EmergencyContact, JmseUser> jmseUser;
	public static volatile SingularAttribute<EmergencyContact, String> name;
}
