package jmse.ejb.facade;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jmse.persistence.model.Day;
import jmse.persistence.model.Security;
import jmse.persistence.model.Segment;

/**
 * Session Bean implementation class DayFacade
 */
@Stateless
@Named
public class DayFacade {
	
	@PersistenceContext(unitName="em")
    EntityManager em;

    /**
     * Default constructor. 
     */
    public DayFacade() {
        // TODO Auto-generated constructor stub
    }
    
    public Day createDay(int index) {
    	//TODO add after mapped
    	Day day = new Day();
    	day.setIndex(index);
    	//day.setSegment(segment);
    	return day;
    }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Day getDayById(int id) {
     	Query q = em.createNamedQuery("Day.findById");
         q.setParameter("id", id);
         Day day = (Day) q.getSingleResult();
         return day;
     }
    
	
	
	
	

}
