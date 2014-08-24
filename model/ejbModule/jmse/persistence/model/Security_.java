package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T13:21:50.527-0500")
@StaticMetamodel(Security.class)
public class Security_ {
	public static volatile SingularAttribute<Security, Integer> id;
	public static volatile SingularAttribute<Security, Double> curPrice;
	public static volatile SingularAttribute<Security, Double> prevPrice;
	public static volatile SingularAttribute<Security, Simulation> simulation;
	public static volatile SingularAttribute<Security, String> description;
	public static volatile SingularAttribute<Security, String> name;
	public static volatile SingularAttribute<Security, String> symbol;
}
