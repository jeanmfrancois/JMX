package jmse.ejb.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import jmse.ejb.facade.SimulationFacade;
import jmse.persistence.model.Simulation;





/**
 * Session Bean implementation class MenuBarBean
 */
@Stateful
@Named
public class MenuBarBean {
	
	@EJB
	private SimulationFacade simf;
	
	private Simulation loadedSimulation;
	  

    /**
     * Default constructor. 
     */
    public MenuBarBean() {
        // TODO Auto-generated constructor stub    	
    	
    }
    
    public String getMessage() {
    	return "Hello";
    }

	  
	public Simulation getLoadedSimulation() {
		loadedSimulation = new Simulation();
		return loadedSimulation;
	}

	
	public void setLoadedSimulation(Simulation loadedSimulation) {
		this.loadedSimulation = loadedSimulation;
	}

	public List<Simulation> listSimulations() {
		  List<Simulation> simulations = simf.getAllSimulations();	  	  
		return simulations;
	}

}
