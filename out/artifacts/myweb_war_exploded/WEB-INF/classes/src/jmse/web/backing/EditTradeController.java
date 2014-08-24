package jmse.web.backing;

import jmse.ejb.facade.DayFacade;
import jmse.ejb.facade.SimulationFacade;
import jmse.ejb.facade.TradeFacade;
import jmse.persistence.model.Simulation;
import jmse.persistence.model.Trade;
import jmse.utils.Params;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Session Bean implementation class RunSimulationController
 */
@ManagedBean
@ViewScoped
public class EditTradeController implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private int selectedSimulationId;
	private Simulation selectedSimulation;
	
	private ArrayList<Trade> tradeCollection;
		
	
	@EJB
	SimulationFacade simf;
	
	@EJB
	TradeFacade traf;
	
	@EJB
	DayFacade dayf;
		

    /**
     * Default constructor. 
     */
    public EditTradeController() {
    	// TODO initialisation goes in initPage method
    	
    }    
    
    //executes in the pre-render page phase
    public void initPage() {
    	// in case it executes more than once
    	if (selectedSimulation == null) {
	    	initParams();
	    	initVars();
	    	initSimulation();
	    	initTrades();
    	} else {
    		System.out.println("WARNING -- retrieval of Simulation when it was previously initialized: Multiple Calls");
    	}
    }
    
    public void initParams() {    	
    		setSelectedSimulationId(Params.initIntParameter("simId"));	     	
    }
    
    public void initVars() {

    }
    
    public void initSimulation() {
    	System.out.println("Initialise Simulation (ID is.." + getSelectedSimulationId() + ")");
    	setSelectedSimulation(simf.getSimulationById(getSelectedSimulationId()));		
    }
    
    public void initTrades() {
    	System.out.println("Initialise trades...");
    	setTradeCollection(traf.getAllTrades());    	
    	System.out.println("trades initialised!!!!!!!!!!!!!!!!: " + Arrays.deepToString(tradeCollection.toArray()));
    	

    }
    
    
    
    
    /***GETTERS AMD SETTERS ******************************************************************************************************************************/
    
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

	
	public ArrayList<Trade> getTradeCollection() {
		return tradeCollection;
	}

	
	public void setTradeCollection(ArrayList<Trade> tradeCollection) {
		this.tradeCollection = tradeCollection;
	}
	
}
