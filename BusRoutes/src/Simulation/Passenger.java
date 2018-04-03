package Simulation;

public class Passenger {

	private Station destination;
	private Location location;
	private int ID;
	private static int passengerCount = 0;

	Passenger(Station start, Station dest) {
		location = start;
		this.destination = dest;
		location.addPassenger(this);
		ID = passengerCount;
		passengerCount++;
	}

	/**
	 * @return The station that this Passenger wants to go to.
	 */
	public Station getDestination() {
		return destination;
	}

	/**
	 * Tells you where this passenger is right now. Note that since this is being
	 * returned as a Location object, you may need to cast to a Bus or Station as
	 * circumstances dictate.
	 * 
	 * @return The Location where the passenger is currently.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Makes this passenger board a bus. Will only occur if the passenger is at a
	 * station and the bus is also at that station and the bus has room for the
	 * passenger.
	 * 
	 * @param b
	 *            The bus you'd like the passenger to board.
	 * @return true if the passenger successfully entered the bus, false otherwise.
	 */
	public boolean enterBus(Bus b) {
		if (b.getDestination() == location && b.atDestination() && b.addPassenger(this)) {
			location.removePassenger(this);
			location = b;
			return true;
		}
		return false;
	}

	/**
	 * Makes this passenger exit the bus it is currently on. Will only occur if the
	 * passenger is currently on a bus and that bus is currently at a station and
	 * not en route. After exiting, the customer's location will be the station.
	 * 
	 * @return true if the customer was able to exit, false otherwise.
	 */
	public boolean exitBus() {
		if (!(location instanceof Bus)) {
			return false;
		}
		Bus myBus = (Bus) location;
		if (myBus.atDestination()) {
			myBus.removePassenger(this);
			location = myBus.getDestination();
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Passenger [destination=" + destination + ", location=" + location + ", ID=" + ID + "]";
	}

}
