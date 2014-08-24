package jmse.persistence.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the news_item database table.
 * 
 */
@Entity
@Table(name="news_item")
@NamedQuery(name="NewsItem.findAll", query="SELECT n FROM NewsItem n")
public class NewsItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descriptionEn;
	private String descriptionFr;
	private String headline;
	private Day day;

	public NewsItem() {
		
	}
	
	


	/**
	 * @param descriptionEn
	 * @param descriptionFr
	 * @param headline
	 * @param day
	 */
	public NewsItem(String descriptionEn, String descriptionFr,
			String headline, Day day) {
		super();
		this.descriptionEn = descriptionEn;
		this.descriptionFr = descriptionFr;
		this.headline = headline;
		this.day = day;
	}




	@Id
	@SequenceGenerator(name="NEWS_ITEM_ID_GENERATOR", sequenceName="NEWS_ITEM_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NEWS_ITEM_ID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="description_en")
	public String getDescriptionEn() {
		return this.descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}


	@Column(name="description_fr")
	public String getDescriptionFr() {
		return this.descriptionFr;
	}

	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}


	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}


	//bi-directional one-to-one association to Day
	//@OneToOne(mappedBy="newsItem", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	//TODO change false and trueness
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(insertable = true, updatable = true)
	public Day getDay() {
		return this.day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

}