package jmse.ejb.business;

import java.awt.Event;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.inject.Named;

import jmse.ejb.facade.SimulationFacade;
import jmse.persistence.model.Simulation;

/**
 * Session Bean implementation class MenuBarLogicBean
 */
@Stateless
@Named
public class MenuBarLogicBean implements Serializable {

	
	@EJB
	SimulationFacade simf;
	
    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
     * Default constructor. 
     */
    public MenuBarLogicBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void handleClose() {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed: OK");  
          
        addMessage(message);  
    }
    
    public void handleToggle() {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Toggled", "Status: OK");  
          
        addMessage(message);  
    } 

	public List<Simulation> listSimulations() {
		// TODO Auto-generated method stub
		List<Simulation> simulations = simf.getAllSimulations();
		return simulations;
	}
	
	private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 

}
