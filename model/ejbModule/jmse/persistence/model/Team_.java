package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T13:21:50.561-0500")
@StaticMetamodel(Team.class)
public class Team_ {
	public static volatile SingularAttribute<Team, Integer> id;
	public static volatile SingularAttribute<Team, Integer> teamRank;
	public static volatile SingularAttribute<Team, Integer> totalTeams;
	public static volatile ListAttribute<Team, Simulation> simulations;
	public static volatile ListAttribute<Team, JmseUser> jmseUsers;
	public static volatile SingularAttribute<Team, String> description;
	public static volatile SingularAttribute<Team, String> name;
}
