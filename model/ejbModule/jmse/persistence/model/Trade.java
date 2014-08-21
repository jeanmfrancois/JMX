package jmse.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the trade database table.
 * 
 */
@Entity
@NamedQuery(name="Trade.findAll", query="SELECT t FROM Trade t")
public class Trade implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer adminUserId;
	private Integer buyerUserId;
	private Date creationDate;
	private Boolean isValid;
	private Date lastModifiedDate;
	private double pricePerShare;
	private Integer quantity;
	private Integer securityValueId;
	private Integer sellerUserId;
	private double totalTradeCost;
	private Day day;

	public Trade() {
		
	}
	
	

	/**
	 * @param adminUserId
	 * @param buyerUserId
	 * @param creationDate
	 * @param isValid
	 * @param lastModifiedDate
	 * @param pricePerShare
	 * @param quantity
	 * @param securityValueId
	 * @param sellerUserId
	 * @param totalTradeCost
	 * @param day
	 */
	public Trade(Integer adminUserId, Integer buyerUserId, Date creationDate,
			Boolean isValid, Date lastModifiedDate, double pricePerShare,
			Integer quantity, Integer securityValueId, Integer sellerUserId,
			double totalTradeCost, Day day) {
		super();
		this.adminUserId = adminUserId;
		this.buyerUserId = buyerUserId;
		this.creationDate = creationDate;
		this.isValid = isValid;
		this.lastModifiedDate = lastModifiedDate;
		this.pricePerShare = pricePerShare;
		this.quantity = quantity;
		this.securityValueId = securityValueId;
		this.sellerUserId = sellerUserId;
		this.totalTradeCost = totalTradeCost;
		this.day = day;
	}



	@Id
	@SequenceGenerator(name="TRADE_ID_GENERATOR", sequenceName="TRADE_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRADE_ID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="admin_user_id")
	public Integer getAdminUserId() {
		return this.adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}


	@Column(name="buyer_user_id")
	public Integer getBuyerUserId() {
		return this.buyerUserId;
	}

	public void setBuyerUserId(Integer buyerUserId) {
		this.buyerUserId = buyerUserId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="creation_date")
	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	@Column(name="is_valid")
	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="last_modified_date")
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	@Column(name="price_per_share")
	public double getPricePerShare() {
		return this.pricePerShare;
	}

	public void setPricePerShare(double pricePerShare) {
		this.pricePerShare = pricePerShare;
	}


	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	@Column(name="security_value_id")
	public Integer getSecurityValueId() {
		return this.securityValueId;
	}

	public void setSecurityValueId(Integer securityValueId) {
		this.securityValueId = securityValueId;
	}


	@Column(name="seller_user_id")
	public Integer getSellerUserId() {
		return this.sellerUserId;
	}

	public void setSellerUserId(Integer sellerUserId) {
		this.sellerUserId = sellerUserId;
	}


	@Column(name="total_trade_cost")
	public double getTotalTradeCost() {
		return this.totalTradeCost;
	}

	public void setTotalTradeCost(double totalTradeCost) {
		this.totalTradeCost = totalTradeCost;
	}


	//bi-directional many-to-one association to Day
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id", insertable=false, updatable=false)
	public Day getDay() {
		return this.day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

}