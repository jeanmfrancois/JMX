package jmse.web.backing;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import jmse.ejb.facade.SimulationFacade;
import jmse.ejb.facade.TradeFacade;
import jmse.persistence.model.Simulation;

/**
 * Session Bean implementation class CreateTradeController
 */
@ManagedBean
@SessionScoped
public class CreateTradeController implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private int selectedSimulationId;
	private Simulation selectedSimulation;
	private String selectedSimulationInfo;
	private String tradePanelName;
	private boolean tradePanelDisabled;
	private boolean tradeButtonDisabled;
	
	private int security;
	private int seller;
	private int buyer;
	
	@Min(value=1, message="Total number of contracts must be at least one.") @Max(value=25, message="Can not have more than 25 contracts.")
	private int numContracts;
	
	private int pricePerContract;	
	
	@EJB
	private SimulationFacade simf;
	
	@EJB
	private TradeFacade traf;

    /**
     * Default constructor. 
     */
    public CreateTradeController() {
        // TODO Auto-generated constructor stub
    	setTradeButtonDisabled(true);
    	setTradePanelDisabled(true);
    	setTradePanelName("Select Simulation");
    	setSelectedSimulationId(0);
    }
    
    public void enableTradePanel() {
    	updateSelectedSimulationInfo();
    	setTradePanelDisabled(false);
    	setTradePanelName(getSelectedSimulation().getName());
    	System.out.println("enable trade");
    	
    }
    
    private void updateSelectedSimulationInfo() {
    	if (selectedSimulationId!=0) {
    		setSelectedSimulation(simf.getSimulationById(getSelectedSimulationId())); 
    		setSelectedSimulationInfo("* Note trades will be performed for the simulation: '" + getSelectedSimulation().getName() + "'");		
    	} else {
    		setSelectedSimulationInfo("No simulation has been selected.");		
    	}
	}
    
    public void checkRequiredFields() {
    	/*if (getSecurity()>0) {
    		System.out.println("security - " + getSecurity() + "-not null");    	*/
	    	if (getSeller()>0) {
	    		System.out.println("seller - " + getSeller() + "-not null");
	    		if (getBuyer()>0) {
	        		System.out.println("buyer - " + getBuyer() + "-not null");
	        		
	        		if (getNumContracts() > 0) {
	            		System.out.println("contracts - " + getNumContracts() + "- more than 0");
	            	
	            		if (getPricePerContract() >= 500 && getPricePerContract() <= 3500) {
	                		System.out.println("price - " + getPricePerContract() + "- more than 0!");
	                		
	                		System.out.println("All fields valid...");
	                		setTradeButtonDisabled(false);
	            		
	            		} else {
	                		System.out.println("price - " + getPricePerContract() + " - 0 :( ");  
	                		setTradeButtonDisabled(true);
	                	}
	        		
	        		
	        		} else {
	            		System.out.println("contracts - " + getNumContracts() + "-0 :(");
	            		setTradeButtonDisabled(true);
	            	}
	    		
	    		
	    		} else {
	        		System.out.println("buyer - " + getBuyer() + "-null :(");
	        		setTradeButtonDisabled(true);
	        	}
	    		
	    		
	    	} else {
	    		System.out.println("seller - " + getSeller() + "-null :(");
	    		setTradeButtonDisabled(true);
	    	}
    	/*} else {
    		System.out.println("security - " + getSecurity() + "-null :(");
    		setTradeButtonDisabled(true);
    	}*/
    	

    	
    	
    	
    }
    
    public void performTrade() {
    	System.out.println("Performed Trade!");
    	traf.createTrade(this.getSecurity(), getBuyer(), getSeller(), getNumContracts(), getPricePerContract()* 1.0, getNumContracts() * getPricePerContract() * 1.0);    	
    	addInfo();
    }

	public List<Simulation> getSimulations() {
		return simf.getAllSimulations();
	}
    
	public int getSelectedSimulationId() {
		return selectedSimulationId;
	}
	
	public void setSelectedSimulationId(int selectedSimulationId) {
		this.selectedSimulationId = selectedSimulationId;
	}
	
	public Simulation getSelectedSimulation() {
		return selectedSimulation;
	}


	
	public void setSelectedSimulation(Simulation selectedSimulation) {
		this.selectedSimulation = selectedSimulation;
	}

	
	public String getSelectedSimulationInfo() {
		return selectedSimulationInfo;
	}

	
	public void setSelectedSimulationInfo(String selectedSimulationInfo) {
		this.selectedSimulationInfo = selectedSimulationInfo;
	}

	
	public int getSecurity() {
		return security;
	}

	
	public void setSecurity(int security) {
		this.security = security;
	}

	
	public int getSeller() {
		return seller;
	}

	
	public void setSeller(int seller) {
		this.seller = seller;
	}

	
	public int getBuyer() {
		return buyer;
	}

	
	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}

	
	public int getNumContracts() {
		return numContracts;
	}

	
	public void setNumContracts(int numContracts) {
		this.numContracts = numContracts;
	}

	
	public int getPricePerContract() {
		return pricePerContract;
	}

	
	public void setPricePerContract(int pricePerContract) {
		this.pricePerContract = pricePerContract;
	}

	
	
	public String getTradePanelName() {
		return tradePanelName;
	}

	
	public void setTradePanelName(String tradePanelName) {
		this.tradePanelName = tradePanelName;
	}

	public boolean isTradePanelDisabled() {
		return tradePanelDisabled;
	}

	
	public void setTradePanelDisabled(boolean tradePanelDisabled) {
		this.tradePanelDisabled = tradePanelDisabled;
	}

	public boolean isTradeButtonDisabled() {
		return tradeButtonDisabled;
	}

	
	public void setTradeButtonDisabled(boolean tradeButtonDisabled) {
		this.tradeButtonDisabled = tradeButtonDisabled;
	}
	
	public void addInfo() {  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Trade has been create", "Move On!"));  
    }


	
}
