package jmse.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T13:21:50.514-0500")
@StaticMetamodel(NewsItem.class)
public class NewsItem_ {
	public static volatile SingularAttribute<NewsItem, Integer> id;
	public static volatile SingularAttribute<NewsItem, String> descriptionEn;
	public static volatile SingularAttribute<NewsItem, String> descriptionFr;
	public static volatile SingularAttribute<NewsItem, Day> day;
	public static volatile SingularAttribute<NewsItem, String> headline;
}
