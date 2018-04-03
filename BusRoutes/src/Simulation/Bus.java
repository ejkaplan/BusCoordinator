package Simulation;

/**
 * A class representing a bus, which can carry a finite number of people between
 * stations.
 * 
 * @author Eliot J. Kaplan
 *
 */
public class Bus extends Location {

	private int capacity;
	private Station destination;
	private int arrivalTime;

	Bus(BusSimulator sim, int capacity, Station startLoc) {
		super(sim);
		this.capacity = capacity;
		destination = startLoc;
		arrivalTime = 0;
	}

	/**
	 * 
	 * @return The maximum number of people who can fit on this bus.
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Returns the station that this bus is on its way towards. If the bus has
	 * already arrived and is waiting to be redeployed, this is the bus' current
	 * station.
	 * 
	 * @return This bus' destination, or this bus' current location if its arrival
	 *         time is <= the current time.
	 */
	public Station getDestination() {
		return destination;
	}

	/**
	 * Returns the station that this bus is currently at. If the bus is currently
	 * between stations, returns null.
	 * 
	 * @return This bus's current station or null if the bus is between stations.
	 */
	public Station getStation() {
		if (atDestination())
			return destination;
		else return null;
	}

	/**
	 * 
	 * @return The time in timesteps since the start of the program when this bus
	 *         will arrive at its destination.
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * 
	 * @return The time in timesteps until this bus arrives at its destination. Will
	 *         be 0 if the bus is currently at a station.
	 */
	public int getTimeUntilArrival() {
		return Math.max(0, arrivalTime - sim.getTime());
	}

	/**
	 * Tells you if the bus has arrived at its destination. If it has not, you will
	 * not be able to issue instructions to it or have passengers get on or off.
	 * 
	 * @return true if the bus is currently at its destination, false otherwise.
	 */
	public boolean atDestination() {
		return getSim().getTime() >= arrivalTime;
	}

	/**
	 * Tells the bus what station to go to. The bus leaves as soon as you give this
	 * command, so after this command you will not be able to have passengers get on
	 * and off until the bus gets there. (So make sure that everyone is on the bus
	 * who should be BEFORE calling this command.)
	 * 
	 * @param s
	 *            The station to which you want the bus to go.
	 * @return true if the command was successfully given, false otherwise
	 */
	public boolean setDest(Station s) {
		if (atDestination() && s != destination) {
			arrivalTime = sim.getTime() + destination.manhattanDistance(s);
			destination = s;
			return true;
		}
		return false;
	}

	boolean addPassenger(Passenger p) {
		if (getPassengers().size() < capacity) {
			return super.addPassenger(p);
		}
		return false;
	}

	boolean removePassenger(Passenger p) {
		if (atDestination()) {
			return super.removePassenger(p);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Bus [capacity=" + capacity + ", destination=" + destination + ", arrivalTime=" + arrivalTime
				+ ", passenger count=" + getPassengers().size() + "]";
	}

}
