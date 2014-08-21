package jmse.web.backing;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import jmse.ejb.business.MenuBarBean;
import jmse.persistence.model.Simulation;

@ManagedBean
@SessionScoped
public class MenuBarController implements Serializable {
	
  /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

private List<Simulation> simulations;
  /*TODO
    should be removed in SessionScoped JSF Bean*/
  
  @EJB
  private MenuBarBean menuBarBean;

  public MenuBarController() {

		
  }
	
  public List<Simulation> getSimulations() {
	  simulations = menuBarBean.listSimulations();
	return simulations;
}



private void setSimulations(List<Simulation> simulations) {
	this.simulations = simulations;
}


public Simulation getLoadedSimulation() {
	Simulation sim = menuBarBean.getLoadedSimulation();
	if (sim!=null) {
		System.out.println("SIM LOADED");
	} else {
		sim = new Simulation();
		sim.setName("No Simulation Loaded - " + (Math.random()*100));
		menuBarBean.setLoadedSimulation(sim);
		System.out.println("DEFAULT SIM LOADED");
	}
	return sim;
}
  
	public String loadSimulation() {
		Simulation sim = new Simulation();
		sim.setName("Tester 202:" + Math.ceil(Math.random()*5000));
	    addMessage("Loaded Simulation: " + sim.getName());	    
	    menuBarBean.setLoadedSimulation(sim);
	    return "simulation-page";
	}

	public String loadSimulation(Simulation simulation) {
	    addMessage("Loaded Simulation: " + simulation.getName());
	    menuBarBean.setLoadedSimulation(simulation);
	    return "simulation-page";
	}
    
  public void addMessage(String summary) {  
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
      FacesContext.getCurrentInstance().addMessage(null, message);  
  }  
  
  
  
}  