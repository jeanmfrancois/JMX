package jmse.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.FetchType.EAGER;


/**
 * The persistent class for the day database table.
 * 
 */
@Entity
@Table(name="Day")
@NamedQueries({
	@NamedQuery(name="Day.findAll", query="SELECT d FROM Day d"),
	@NamedQuery(name = "Day.findById", query = "SELECT d FROM Day d WHERE d.id = :id")})
public class Day implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer index;
	private Boolean hasNews;
	private Integer totalSegmentTrades;
	private Segment segment;
	private NewsItem newsItem;
	private List<Trade> trades;

	public Day() {
		
	}


	/**
	 * @param hasNews
	 * @param totalSegmentTrades
	 * @param segment
	 * @param newsItem
	 * @param trades
	 */
	public Day(Boolean hasNews, Integer index, Integer totalSegmentTrades, Segment segment,
			NewsItem newsItem, List<Trade> trades) {
		super();
		this.hasNews = hasNews;
		this.totalSegmentTrades = totalSegmentTrades;
		this.segment = segment;
		this.newsItem = newsItem;
		this.trades = trades;
	}


	@Id
	@SequenceGenerator(name="DAY_ID_GENERATOR", sequenceName="DAY_ID_SEQ", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DAY_ID_GENERATOR")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="has_news")
	public Boolean getHasNews() {
		return this.hasNews;
	}

	public void setHasNews(Boolean hasNews) {
		this.hasNews = hasNews;
	}


	@Column(name="total_segment_trades")
	public Integer getTotalSegmentTrades() {
		return this.totalSegmentTrades;
	}

	public void setTotalSegmentTrades(Integer totalSegmentTrades) {
		this.totalSegmentTrades = totalSegmentTrades;
	}

	//TODO add mapped
	/*bi-directional many-to-one association to Segment
	@ManyToOne
	@JoinColumn(name = "segment_id", referencedColumnName = "id", insertable = true, updatable = true)
	public Segment getSegment() {
		return this.segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}*/


	//bi-directional one-to-one association to NewsItem
	@OneToOne(mappedBy="day", cascade={CascadeType.ALL})
	public NewsItem getNewsItem() {
		return this.newsItem;
	}

	public void setNewsItem(NewsItem newsItem) {
		this.newsItem = newsItem;
	}


	//bi-directional many-to-one association to Trade
	@OneToMany(mappedBy="day",cascade={CascadeType.ALL}, fetch = EAGER)
	public List<Trade> getTrades() {
		return this.trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	@Column(name="index")
	public Integer getIndex() {
		return index;
	}


	
	public void setIndex(Integer index) {
		this.index = index;
	}


	public Trade addTrade(Trade trade) {
		getTrades().add(trade);
		trade.setDay(this);

		return trade;
	}

	public Trade removeTrade(Trade trade) {
		getTrades().remove(trade);
		trade.setDay(null);

		return trade;
	}

}