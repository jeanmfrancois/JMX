package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T13:21:50.518-0500")
@StaticMetamodel(Priviledge.class)
public class Priviledge_ {
	public static volatile SingularAttribute<Priviledge, Integer> id;
	public static volatile SingularAttribute<Priviledge, Boolean> hasPriviledge;
	public static volatile ListAttribute<Priviledge, Role> roles;
	public static volatile SingularAttribute<Priviledge, String> description;
	public static volatile SingularAttribute<Priviledge, String> name;
}
