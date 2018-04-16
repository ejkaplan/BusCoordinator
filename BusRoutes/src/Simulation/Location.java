package Simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing anything that can contain Passengers. Intended to be
 * parent to Bus and Station.
 * 
 * @author Eliot J. Kaplan
 *
 */
public abstract class Location {

	protected List<Passenger> passengers;
	protected BusSimulator sim;

	Location(BusSimulator sim) {
		passengers = new ArrayList<Passenger>();
		this.sim = sim;
	}

	/**
	 * Tells you which Passengers are currently at this Location. The returned List
	 * is a copy, so modifying it does not actually change who is at the Location.
	 * 
	 * @return A List of the Passenger objects who are currently at this location.
	 */
	public List<Passenger> getPassengers() {
		return new ArrayList<Passenger>(passengers);
	}

	boolean addPassenger(Passenger p) {
		if (!passengers.contains(p)) {
			passengers.add(p);
			return true;
		}
		return false;
	}

	boolean removePassenger(Passenger p) {
		if (passengers.contains(p)) {
			passengers.remove(p);
			return true;
		}
		return false;
	}

	BusSimulator getSim() {
		return sim;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((passengers == null) ? 0 : passengers.hashCode());
		result = prime * result + ((sim == null) ? 0 : sim.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (passengers == null) {
			if (other.passengers != null)
				return false;
		} else if (!passengers.equals(other.passengers))
			return false;
		if (sim == null) {
			if (other.sim != null)
				return false;
		} else if (!sim.equals(other.sim))
			return false;
		return true;
	}

}
