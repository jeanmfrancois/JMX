package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T15:39:40.281-0500")
@StaticMetamodel(Segment.class)
public class Segment_ {
	public static volatile SingularAttribute<Segment, Integer> id;
	public static volatile SingularAttribute<Segment, Integer> totalSegmentTrades;
	public static volatile ListAttribute<Segment, Day> days;
}
