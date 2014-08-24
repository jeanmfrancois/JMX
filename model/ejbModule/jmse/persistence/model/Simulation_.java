package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T15:39:14.868-0500")
@StaticMetamodel(Simulation.class)
public class Simulation_ {
	public static volatile SingularAttribute<Simulation, Integer> id;
	public static volatile SingularAttribute<Simulation, Integer> curDayId;
	public static volatile SingularAttribute<Simulation, Integer> curSegmentId;
	public static volatile SingularAttribute<Simulation, Integer> durationInMinutes;
	public static volatile SingularAttribute<Simulation, Integer> durationInSeconds;
	public static volatile SingularAttribute<Simulation, Long> durationInMilliseconds;
	public static volatile SingularAttribute<Simulation, Long> elapsedDurationInMilliseconds;
	public static volatile SingularAttribute<Simulation, Boolean> isActive;
	public static volatile SingularAttribute<Simulation, Integer> totalDays;
	public static volatile SingularAttribute<Simulation, Integer> totalSegments;
	public static volatile SingularAttribute<Simulation, Integer> totalSimulationTrades;
	public static volatile SingularAttribute<Simulation, Integer> totalTeams;
	public static volatile SingularAttribute<Simulation, Integer> totalUsers;
	public static volatile ListAttribute<Simulation, Segment> segments;
	public static volatile SingularAttribute<Simulation, Simulation> simulation;
	public static volatile ListAttribute<Simulation, Sponsor> sponsors;
	public static volatile ListAttribute<Simulation, Team> teams;
	public static volatile SingularAttribute<Simulation, String> description;
	public static volatile SingularAttribute<Simulation, String> name;
}
