package jmse.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the day database table.
 * 
 */
@Entity
@NamedQuery(name="Day.findAll", query="SELECT d FROM Day d")
public class Day implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
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
	public Day(Boolean hasNews, Integer totalSegmentTrades, Segment segment,
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


	//bi-directional many-to-one association to Segment
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id", insertable=false, updatable=false)
	public Segment getSegment() {
		return this.segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}


	//bi-directional one-to-one association to NewsItem
	@OneToOne(mappedBy="day")
	public NewsItem getNewsItem() {
		return this.newsItem;
	}

	public void setNewsItem(NewsItem newsItem) {
		this.newsItem = newsItem;
	}


	//bi-directional many-to-one association to Trade
	@OneToMany(mappedBy="day", fetch=FetchType.EAGER)
	public List<Trade> getTrades() {
		return this.trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
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