package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T13:21:50.556-0500")
@StaticMetamodel(Sponsor.class)
public class Sponsor_ {
	public static volatile SingularAttribute<Sponsor, Integer> id;
	public static volatile SingularAttribute<Sponsor, String> logoIcon;
	public static volatile SingularAttribute<Sponsor, String> logoLarge;
	public static volatile SingularAttribute<Sponsor, String> logoSmall;
	public static volatile SingularAttribute<Sponsor, String> phoneNumber1;
	public static volatile SingularAttribute<Sponsor, String> phoneNumber2;
	public static volatile ListAttribute<Sponsor, Simulation> simulations;
	public static volatile SingularAttribute<Sponsor, String> description;
	public static volatile SingularAttribute<Sponsor, String> name;
}
