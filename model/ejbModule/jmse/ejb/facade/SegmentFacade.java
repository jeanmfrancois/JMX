package jmse.ejb.facade;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jmse.persistence.model.Segment;
import jmse.persistence.model.Simulation;

/**
 * Session Bean implementation class SegmentFacade
 */
@Stateless
@Named
public class SegmentFacade {
	
	@PersistenceContext(unitName="em")
    EntityManager em;

    /**
     * Default constructor. 
     */
    public SegmentFacade() {
        // TODO Auto-generated constructor stub
    }
    
    public Segment createSegment(int index) {
    	Segment segment = new Segment();
    	segment.setIndex(index);
    	//TODO add after map
    	//segment.setSimulation(simulation);   
    	return segment;
    }

}
