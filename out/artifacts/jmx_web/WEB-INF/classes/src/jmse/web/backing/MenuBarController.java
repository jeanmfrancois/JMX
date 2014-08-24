package jmse.web.backing;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import jmse.ejb.business.MenuBarLogicBean;
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
  private MenuBarLogicBean menuBarLogicBean;

  public MenuBarController() {

		
  }
	
  public List<Simulation> getSimulations() {
	  simulations = menuBarLogicBean.listSimulations();
	return simulations;
}



  
    
  public void addMessage(String summary) {  
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
      FacesContext.getCurrentInstance().addMessage(null, message);  
  }  
  
  
  
}  