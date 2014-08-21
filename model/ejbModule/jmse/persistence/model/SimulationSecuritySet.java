package jmse.persistence.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the simulation_security_set database table.
 * 
 */
@Entity
@Table(name="simulation_security_set")
@NamedQuery(name="SimulationSecuritySet.findAll", query="SELECT s FROM SimulationSecuritySet s")
public class SimulationSecuritySet implements Serializable {
	private static final long serialVersionUID = 1L;
	private SimulationSecuritySetPK id;
	private Integer count;

	public SimulationSecuritySet() {
	}


	@EmbeddedId
	public SimulationSecuritySetPK getId() {
		return this.id;
	}

	public void setId(SimulationSecuritySetPK id) {
		this.id = id;
	}


	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}