package jmse.web.backing;

import jmse.ejb.facade.SecurityFacade;
import jmse.ejb.facade.SimulationFacade;
import jmse.persistence.model.Security;
import jmse.persistence.model.Simulation;
import jmse.utils.Params;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Session Bean implementation class RunSimulationController
 */
@ManagedBean
@ViewScoped
public class SecurityPathController implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private int selectedSimulationId;
	private int selectedSecurityId;
	private Simulation selectedSimulation;
	private Security selectedSecurity;
	
	
	private ArrayList<Double> yPlotPoints;
	
	

	FacesContext facesContext = FacesContext.getCurrentInstance();
	Map<String,String> params;
	
	
	@EJB
	SimulationFacade simf;
	
	@EJB
	SecurityFacade secf;
		

    /**
     * Default constructor. 
     */
    public SecurityPathController() {    
    	System.out.println("Init Here");
    	ArrayList<Double> newList = new ArrayList<Double>();
    	for (int i=10; i<20; i++) {
    		newList.add(new Double(i*10.0));
    	}
    	
    	this.setyPlotPoints(newList);
    	
    	
    	
    	//setyPlotPoints(secf.getProjectedPlotPointsYCollection(getSelectedSecurityId()));
    	//setyPlotPoints(new ArrayList<Double> ());
    }    
    
    public void initPage() {
    	
    	System.out.println("Init Page");
    	initParams();
    	initVars();
    	initSecurity();
    }
    
    public void initParams() {
    	setSelectedSimulationId(Params.initIntParameter("simId"));	   
    	setSelectedSecurityId(Params.initIntParameter("secId"));	   
    }
    
    public void initVars() {

    }
    
    public void initSecurity() {
    	
    	try {
    		if (selectedSimulationId > 0 && selectedSecurityId > 0) {
    			setSelectedSimulation(simf.getSimulationById(this.selectedSimulationId));
    			setSelectedSecurity(secf.getSecurityById(this.selectedSecurityId));
    			setyPlotPoints(secf.getProjectedPlotPointsYCollection(getSelectedSecurityId()));
    			System.out.println("Values" + Arrays.deepToString(getyPlotPoints().toArray()));
    		} else {
    			System.out.println("Simulation Id or Security Id are not valid");
    		}
    	}
    	catch(Exception e) {
    		System.out.println("Simulation, Security, or PlotPoints initialise problem.");
    	}    	
    }
    
    
    
    public void reset() {
    	
    }
    
    public void save() {
    	System.out.println("saved values");
    	System.out.println("Id: " + getSelectedSecurityId());
    	System.out.println("Plot points" + Arrays.deepToString(getyPlotPoints().toArray()));
    	secf.setProjectedPlotPointsYCollection(getSelectedSecurityId(), getyPlotPoints());
    	
    	FacesContext context = FacesContext.getCurrentInstance();  	          
        context.addMessage(null, new FacesMessage("Saved New Values", "Simulation Can Be Run Now"));
    
    	
    }
    
    /*public void onSlideEnd(Integer passedIndex, Double passedValue) {
    	Integer index = passedIndex;
    	Double curValue =  passedValue;
    	
    	System.out.println("saved index: " + passedIndex + " and value: " + curValue);
    	//System.out.println("Id: " + getSelectedSecurityId());
    	//System.out.println("Plot points: " + passedIndex + " " + Arrays.deepToString(getyPlotPoints().toArray()));
    	secf.setProjectedPlotPointsYCollection(getSelectedSecurityId(), getyPlotPoints());
    	
    	FacesContext context = FacesContext.getCurrentInstance();  	          
        context.addMessage(null, new FacesMessage("Saved New Values:", "Simulation Can Be Run Now"));
    
    	
    }*/

	public int getSelectedSimulationId() {
		return selectedSimulationId;
	}
	
	public void setSelectedSimulationId(int selectedSimulationId) {
		this.selectedSimulationId = selectedSimulationId;
	}
	

	
	public int getSelectedSecurityId() {
		return selectedSecurityId;
	}

	
	public void setSelectedSecurityId(int selectedSecurityId) {
		this.selectedSecurityId = selectedSecurityId;
	}
	
	public Security getSelectedSecurity() {
		return selectedSecurity;
	}

	
	public void setSelectedSecurity(Security selectedSecurity) {
		this.selectedSecurity = selectedSecurity;
	}

	
	public Simulation getSelectedSimulation() {
		return selectedSimulation;
	}

	
	public void setSelectedSimulation(Simulation selectedSimulation) {
		this.selectedSimulation = selectedSimulation;
	}

	
	/*public String setSelectedSimulationInfo() {
		////System.out.println("Setting Simulation Info");
		
		if(selectedSimulationId != 0) {
			selectedSimulationInfo = selectedSim.getName() + " is currently selected.";			
		} else {
			selectedSimulationInfo = "No simulation has been selected yet.";
		}
		return selectedSimulationInfo;
	}

	
	public String getSelectedSimulationInfo() {
		return selectedSimulationInfo;
	}
	*/
	
	

	public ArrayList<Double> getyPlotPoints() {
		return yPlotPoints;
	}

	
	public void setyPlotPoints(ArrayList<Double> yPlotPoints) {
		this.yPlotPoints = yPlotPoints;
	}
	
	
	
	
	
	
}
