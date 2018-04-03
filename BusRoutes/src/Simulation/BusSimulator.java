package Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BusSimulator {

	private List<Station> stations;
	private List<Bus> busses;
	private List<Passenger> passengers;
	private int time;
	private boolean done;
	private int cutoff;
	private BusCoordinator coordinator;

	public BusSimulator(BusCoordinator coordinator, int width, int height, int nPassengers, int nStations, int nBusses,
			int busCapacity) {
		this(coordinator, width, height, nPassengers, nStations, nBusses, busCapacity, new Random());
	}

	public BusSimulator(BusCoordinator coordinator, int width, int height, int nPassengers, int nStations, int nBusses,
			int busCapacity, long seed) {
		this(coordinator, width, height, nPassengers, nStations, nBusses, busCapacity, new Random(seed));
	}

	private BusSimulator(BusCoordinator coordinator, int width, int height, int nPassengers, int nStations, int nBusses,
			int busCapacity, Random r) {
		time = -1;
		stations = new ArrayList<Station>();
		busses = new ArrayList<Bus>();
		passengers = new ArrayList<Passenger>();
		done = false;
		cutoff = nBusses * nStations;
		this.coordinator = coordinator;
		this.coordinator.setSimulation(this);
		// Place the stations randomly, without overlapping.
		for (int i = 0; i < nStations; i++) {
			Station s = null;
			boolean good = false;
			while (!good) {
				good = true;
				s = new Station(this, r.nextInt(width), r.nextInt(height));
				for (Station x : stations) {
					if (x.getX() == s.getX() && x.getY() == s.getY()) {
						good = false;
						break;
					}
				}
			}
			stations.add(s);
		}
		// Place the busses at the first n stations.
		for (int i = 0; i < nBusses; i++) {
			busses.add(new Bus(this, busCapacity, stations.get(i % nStations)));
		}
		// Place the passengers randomly, with random desired stations.
		for (int i = 0; i < nPassengers; i++) {
			Station orig = stations.get(r.nextInt(nStations));
			Station dest;
			do {
				dest = stations.get(r.nextInt(nStations));
			} while (orig == dest);
			passengers.add(new Passenger(orig, dest));
		}
	}

	/**
	 * 
	 * @return The number of time steps that have elapsed since the program started
	 *         (t=0)
	 */
	public final int getTime() {
		return time;
	}

	/**
	 * @return A list of all of the station objects.
	 */
	public final List<Station> getStations() {
		return new ArrayList<Station>(stations);
	}

	/**
	 * @return A list of all of the bus objects.
	 */
	public final List<Bus> getBusses() {
		return new ArrayList<Bus>(busses);
	}

	/**
	 * @return A list of all of the passenger objects.
	 */
	public final List<Passenger> getPassengers() {
		return new ArrayList<Passenger>(passengers);
	}

	private boolean busAvailable() {
		for (Bus b : busses) {
			if (b.atDestination())
				return true;
		}
		return false;
	}

	/**
	 * Runs the simulation to termination, seeking input from the player at every
	 * timestep where there is a bus at a station, ready to accept commands.
	 * 
	 * @return The number of timesteps it took to get every passenger to their
	 *         destination or Integer.MAX_VALUE if the program failed to deliver
	 *         everybody.
	 */
	public final int run() {
		if (done) // If the simulation has already been run, it can't run again.
			return Integer.MAX_VALUE;
		int noProgress = 0;
		while (passengers.size() > 0) {
			// Jump ahead to whenever the next bus will be available.
			int nextTime = Integer.MAX_VALUE;
			for (Bus b : busses) {
				if (b.getArrivalTime() < nextTime)
					nextTime = b.getArrivalTime();
			}
			if (nextTime == time)
				time++;
			else time = nextTime;
			boolean progress = false;
			if (busAvailable()) { // If all busses are in transit, there's nothing to do.
				try {
					coordinator.go(); // Get instructions from the AI.
				} catch (Exception e) {
					e.printStackTrace();
					return Integer.MAX_VALUE;
				}
				List<Passenger> arrived = new ArrayList<Passenger>();
				for (Passenger p : passengers) {
					if (p.getLocation() == p.getDestination())
						arrived.add(p);
				}
				for (Passenger p : arrived) {
					progress = true;
					p.getLocation().removePassenger(p);
					passengers.remove(p);
				}
			}
			if (progress)
				noProgress = 0;
			else noProgress++;
			if (noProgress > cutoff) { // End the program if nobody has been delivered in a long time.
				System.err.println(
						"No passengers delivered for too long. " + passengers.size() + " undelivered passengers.");
				return Integer.MAX_VALUE;
			}
		}
		done = true;
		return time;
	}
}
