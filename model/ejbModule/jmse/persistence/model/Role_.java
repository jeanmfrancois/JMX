package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T13:21:50.522-0500")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Integer> id;
	public static volatile ListAttribute<Role, JmseUser> jmseUsers;
	public static volatile ListAttribute<Role, Priviledge> priviledges;
	public static volatile SingularAttribute<Role, String> description;
	public static volatile SingularAttribute<Role, String> name;
}
