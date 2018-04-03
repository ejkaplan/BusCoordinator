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

}
