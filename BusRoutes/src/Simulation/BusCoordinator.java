package Simulation;

import java.util.List;

/**
 * A class that coordinates the movements of passengers and busses. You will
 * make a subclass of BusCoordinator and write your own go() method. This class
 * includes inherited public functions for accessing the bus, station and
 * passenger objects, and you can call their public functions to get information
 * about them or give them orders to get on or off busses (in the case of the
 * passengers) or move between stations (in the case of the busses).
 * 
 * @author Eliot J. Kaplan
 *
 */
public abstract class BusCoordinator {

	private BusSimulator simulation;

	void setSimulation(BusSimulator simulation) {
		this.simulation = simulation;
	}

	/**
	 * Controls Passengers getting on and off busses and busses moving between
	 * stations. This is the function that you write! You can call any of the public
	 * methods in any of the classes in Simulation.
	 */
	public abstract void go();

	/**
	 * 
	 * @return The number of time steps that have elapsed since the program started
	 *         (t=0)
	 */
	public final int getTime() {
		return simulation.getTime();
	}

	/**
	 * @return A list of all of the station objects.
	 */
	public final List<Station> getStations() {
		return simulation.getStations();
	}

	/**
	 * @return A list of all of the bus objects.
	 */
	public final List<Bus> getBusses() {
		return simulation.getBusses();
	}

	/**
	 * @return A list of all of the passenger objects.
	 */
	public final List<Passenger> getPassengers() {
		return simulation.getPassengers();
	}

}
