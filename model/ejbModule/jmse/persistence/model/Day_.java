package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T15:45:20.677-0500")
@StaticMetamodel(Day.class)
public class Day_ {
	public static volatile SingularAttribute<Day, Integer> id;
	public static volatile SingularAttribute<Day, Boolean> hasNews;
	public static volatile SingularAttribute<Day, Integer> totalSegmentTrades;
	public static volatile SingularAttribute<Day, NewsItem> newsItem;
	public static volatile ListAttribute<Day, Trade> trades;
}
