package jmse.persistence.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the simulation_security_set database table.
 * 
 */
@Embeddable
public class SimulationSecuritySetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Integer securitySetId;
	private Integer simulationId;

	public SimulationSecuritySetPK() {
	}

	@Column(name="security_set_id")
	public Integer getSecuritySetId() {
		return this.securitySetId;
	}
	public void setSecuritySetId(Integer securitySetId) {
		this.securitySetId = securitySetId;
	}

	@Column(name="simulation_id")
	public Integer getSimulationId() {
		return this.simulationId;
	}
	public void setSimulationId(Integer simulationId) {
		this.simulationId = simulationId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SimulationSecuritySetPK)) {
			return false;
		}
		SimulationSecuritySetPK castOther = (SimulationSecuritySetPK)other;
		return 
			this.securitySetId.equals(castOther.securitySetId)
			&& this.simulationId.equals(castOther.simulationId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.securitySetId.hashCode();
		hash = hash * prime + this.simulationId.hashCode();
		
		return hash;
	}
}