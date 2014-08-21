package jmse.web.backing;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

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
	
	private CartesianChartModel linearModel;
	private String title;
	private int minX, maxX;
	private int minY, maxY;
	private String legendPosition;
	private String seriesColor;
	//private NotificationBar newsHeader;
	private String newsHeader;
	
	private LineChartSeries series1;
	//private LineChartSeries series2;

    /**
     * Default constructor. 
     */
    public ViewSecurityLineGraphController() {
    	this.setTitle("Security: AU");
    	this.setSeriesColor("e5c100");
    	this.setMinY(0);
    	this.setMaxY(2000);
    	this.setMinX(0);
    	this.setMaxX(350);
    	this.setLegendPosition("nw");
    	this.setNewsHeader("A massive pirate ship has landing on the western coast, handing tons of Gold to villagers.");
		
        createModel();
    }
    
    public void createModel() {
    	linearModel = new CartesianChartModel();    	  
        series1 = new LineChartSeries();  
        series1.setLabel("Gold");  
        linearModel.addSeries(series1);          	
    }

	
	public CartesianChartModel getLinearModel() {
		return linearModel;
	}

	
	public void setLinearModel(CartesianChartModel linearModel) {
		this.linearModel = linearModel;
	}
	
	public CartesianChartModel getSecurityData() {
		//int random1 = (int)(Math.random() * 2000);		
		//int category = linearModel.getSeries().get(0).getData().size() + 1;		
		//linearModel.getSeries().get(0).getData().put(Integer.toString(category), random1);
		//linearModel.getSeries().get(0).getData().put(Integer.toString(category), random1);
		double securityDay = linearModel.getSeries().get(0).getData().size() + Math.random();	
		double securityValue = (Math.random() * 2000);
		series1.getData().put(securityDay,securityValue);
		return linearModel;
	}
	
	/*public NotificationBar getNewsHeader() {
	return newsHeader;		
}

public void setNewsHeader(NotificationBar notificationBar) {
	this.newsHeader = notificationBar;
}*/
	public void changeNews() {
		newsHeader = "A massive pirate ship has landing on the western coast, handing tons of Gold to villagers. - Item:" + (int)(Math.random()*100); 
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
    
    

}
