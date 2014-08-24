package jmse.persistence.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-11-08T13:21:50.565-0500")
@StaticMetamodel(Trade.class)
public class Trade_ {
	public static volatile SingularAttribute<Trade, Integer> id;
	public static volatile SingularAttribute<Trade, Integer> adminUserId;
	public static volatile SingularAttribute<Trade, Integer> buyerUserId;
	public static volatile SingularAttribute<Trade, Date> creationDate;
	public static volatile SingularAttribute<Trade, Boolean> isValid;
	public static volatile SingularAttribute<Trade, Date> lastModifiedDate;
	public static volatile SingularAttribute<Trade, Double> pricePerShare;
	public static volatile SingularAttribute<Trade, Integer> securityValueId;
	public static volatile SingularAttribute<Trade, Integer> sellerUserId;
	public static volatile SingularAttribute<Trade, Double> totalTradeCost;
	public static volatile SingularAttribute<Trade, Integer> quantity;
}
