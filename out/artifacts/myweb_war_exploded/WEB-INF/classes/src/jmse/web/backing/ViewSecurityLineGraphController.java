package jmse.web.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import jmse.ejb.facade.SecurityFacade;
import jmse.persistence.model.Security;
import jmse.utils.Params;

import org.primefaces.component.notificationbar.NotificationBar;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 * Session Bean implementation class ViewSecurityLineGraphController
 */
@ManagedBean
@SessionScoped
public class ViewSecurityLineGraphController implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private String				pageTitle;
	private String				title;
	private int					minX, maxX;
	private int					minY, maxY;
	private String				legendPosition;
	private String				seriesColor;
	// private NotificationBar newsHeader;
	private String				newsHeader;
	
	
	
	private Integer				selectedSecurityId;
	private Security			selectedSecurity;
	
	//private ArrayList<Double>	securityPriceArray;
	//private ArrayList<Double>	securityTimeArray;
	//private CartesianChartModel	securityChartCanvas;
	//private LineChartSeries		securityValues;
	
	
	@EJB
	SecurityFacade				secf;
	
	private int					tempCount;

	private String	diffPos = " ";
	private String diffNeg = " ";

	
	
	
	

	/**
	 * Default constructor.
	 */
	public ViewSecurityLineGraphController() {

		initParams();
		initVars();
	}
	
	public void initPage() {	
	}
	
	
	public void initParams() {
		//setSelectedSecurityId(Params.initIntParameter("secId"));
	}
	
	public void initVars() {
		if (Params.initIntParameter("secId")!= 0) {
			setSelectedSecurityId(Params.initIntParameter("secId"));
		System.out.println("Init vars...");
		setSelectedSecurity(secf.getSecurityById(getSelectedSecurityId()));
		this.setPageTitle(getSelectedSecurity().getName());		
		this.setTitle("Current Value: " + (int) getSelectedSecurity().getCurPrice());
		this.setSeriesColor(getSelectedSecurity().getColor());
		this.setMinY((int) getSelectedSecurity().getMinPricePerDay());
		this.setMaxY((int) getSelectedSecurity().getMaxPricePerDay());
		this.setLegendPosition("nw");
		
		this.setMinX(0);
		this.setMaxX(getSelectedSecurity().getSimulation().getTotalDays());
		
		this.setNewsHeader(getSelectedSecurity().getSimulation().getCurNewsItem());
		}
	}
	
	//initialize the models
	/*public void initModel() {
		
		System.out.println("VIEW GRAPH ---- " + "init model ONLY 1 time..");
		
		securityChartCanvas = new CartesianChartModel();
		securityValues = new LineChartSeries();
		securityValues.setLabel(getSelectedSecurity().getSymbol());
		securityValues.set(0.0, this.getSelectedSecurity().getPrevPrice());
		securityChartCanvas.addSeries(securityValues);
		
	}*/
	
	public CartesianChartModel getChartModel() {
		CartesianChartModel chartModel = new CartesianChartModel();
		LineChartSeries securityValues = new LineChartSeries();		
		securityValues.setLabel(getSelectedSecurity().getSymbol());
		
		// set the new values
		
		String valueString;
		
		double difference = secf.getSecurityById(selectedSecurity.getId()).getDiffPricePerDay();
		
		if (difference < 0) {
			valueString = "-" + ((int) Math.abs(difference));
			diffNeg = valueString;
			diffPos = " ";
			
		} else if (difference > 0) {
			valueString = "+" + ((int) difference);
			diffNeg = " ";
			diffPos = valueString;
			
		} else {
			diffNeg = " ";
			diffPos = " ";
		}
		
		//System.out.println("DIFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF " + difference);
		
		this.setTitle("Current Value: " + (int) secf.getSecurityById(selectedSecurity.getId()).getCurPrice());
		this.setNewsHeader(getSelectedSecurity().getSimulation().getCurNewsItem());
		
		try {
			ArrayList<Double> securityTimeArray = secf.getActualPriceXCollection(getSelectedSecurityId());
			ArrayList<Double> securityPriceArray = secf.getActualPriceYCollection(getSelectedSecurityId());
			
			
			
			for (int i = 0; i < securityPriceArray.size(); i++) {				
				//add all values to the data set
				securityValues.set(securityTimeArray.get(i), securityPriceArray.get(i));
			}

			chartModel.addSeries(securityValues);
			
			
			
		} catch (Exception e) {
		}
		
		return chartModel;
	}
	
	

	// check for updates to know if you should return a new model...
	/*public void checkForUpdate() {
System.out.println("checking for values..");
		securityPriceArray = secf.getActualPriceYCollection(selectedSecurityId);
		
		//if (securityPriceArray.size() >= securityValues.getData().size()) {
			updateLineGraph();
		
		//} else {
		// System.out.print("no update");
		 //}
	}
	
	public void updateLineGraph() {
		System.out.println("Finally updating l;ine graph!");
		securityTimeArray =  secf.getActualPriceXCollection(selectedSecurityId);
		
		try {
		if(securityTimeArray.size() > 1 && securityPriceArray.size()> 1) {
			System.out.println("updating line graph... POINT:(" + securityTimeArray.get(securityTimeArray.size()-1) + "," + securityPriceArray.get(securityPriceArray.size()-1) + ")." );
		
		
		
			addSecurityValues(securityTimeArray.get(securityTimeArray.size()-1), securityPriceArray.get(securityPriceArray.size()-1));
		} else {
			System.out.println("bad try");
		}
		}
		catch(Exception e) {
			System.out.println("index arrays issue");
			e.printStackTrace();
		}
		
	}
	
	public void updateBarGraph() {
		System.out.println("updating bar chart...");
	}
	
	public CartesianChartModel getUpdatedSecurityChartCanvas() {
		System.out.println("Updated content... " + "Canvas: " + Arrays.deepToString(securityChartCanvas.getSeries().get(0).getData().values().toArray()));
		
		System.out.println("Updated content... Values: " + Arrays.deepToString(securityValues.getData().values().toArray()));
		
		this.checkForUpdate();
		// add new points
		//updateLineGraph();
		
		return securityChartCanvas;
	}
	
	public void addSecurityValues(Double timeElapsed, Double securityValue) {
		
		this.securityValues.getData().put(timeElapsed, securityValue);
		
		// set my stored map to the value of the charts current data
		// this.securityValuesMap =
		// securityChartCanvas.getSeries().get(0).getData();
		
		
		// this.securityValuesMap.put(timeElapsed,securityValue);
		
		// set the value of the series data to my Map
		// securityValues.setData(this.securityValuesMap);
		
		// this.securityChartCanvas.getSeries().set(0, this.securityValues);
		
		// //securityChartCanvas.getSeries().get(0).getData().put(timeElapsed,
		// securityValue);
		// double securityDay =(int)
		// (linearModel.getSeries().get(0).getData().size() + Math.random());
		// double securityValue = (Math.random() * 2000);
		// series1.getData().put(securityDay,securityValue);
	}*/
	
	/******** GETTERS AND SETTERS *****************************************************************************************************************/
	
	/*
	 * public NotificationBar getNewsHeader() { return newsHeader; }
	 * 
	 * public void setNewsHeader(NotificationBar notificationBar) {
	 * this.newsHeader = notificationBar; }
	 */
	public void changeNews() {
		newsHeader = "A massive pirate ship has landing on the western coast, handing tons of Gold to villagers. - Item:"
				+ (int) (Math.random() * 100);
	}
	
	public String getNewsHeader() {
		return newsHeader;
	}
	
	public void setNewsHeader(String news) {
		this.newsHeader = news;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getMinX() {
		return minX;
	}
	
	public void setMinX(int minX) {
		this.minX = minX;
	}
	
	public int getMaxX() {
		return maxX;
	}
	
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}
	
	public int getMinY() {
		return minY;
	}
	
	public void setMinY(int minY) {
		this.minY = minY;
	}
	
	public int getMaxY() {
		return maxY;
	}
	
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	
	public String getLegendPosition() {
		return legendPosition;
	}
	
	public void setLegendPosition(String legendPosition) {
		this.legendPosition = legendPosition;
	}
	
	public String getSeriesColor() {
		return seriesColor;
	}
	
	public void setSeriesColor(String seriesColor) {
		this.seriesColor = seriesColor;
	}
	
	public Integer getSelectedSecurityId() {
		return selectedSecurityId;
	}
	
	public void setSelectedSecurityId(Integer selectedSecurityId) {
		this.selectedSecurityId = selectedSecurityId;
	}
	
	public Security getSelectedSecurity() {
		return selectedSecurity;
	}
	
	public void setSelectedSecurity(Security selectedSecurity) {
		this.selectedSecurity = selectedSecurity;
	}
	
	public String getPageTitle() {
		return pageTitle;
	}
	
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getDiffPos() {
		return diffPos;
	}

	
	public void setDiffPos(String diffPos) {
		this.diffPos = diffPos;
	}

	
	public String getDiffNeg() {
		return diffNeg;
	}

	
	public void setDiffNeg(String diffNeg) {
		this.diffNeg = diffNeg;
	}
	
}
