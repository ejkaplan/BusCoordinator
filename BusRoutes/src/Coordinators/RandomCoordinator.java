package Coordinators;

import java.util.List;
import java.util.Random;

import Simulation.Bus;
import Simulation.BusCoordinator;
import Simulation.Passenger;
import Simulation.Station;

/**
 * An extremely bad coordinator provided as an example of how one might use the
 * provided functions to make a coordinator that does something. This one just
 * shuttles people around totally at random without regard for where they want
 * to go.
 */
public class RandomCoordinator extends BusCoordinator {

	@Override
	public void go() {
		Random r = new Random();
		// Loop through each bus
		for (Bus b : this.getBusses()) {
			// A bus that isn't at its destination yet won't listen to commands, so skip it.
			if (!b.atDestination())
				continue;
			// Each passenger on the bus has a 50% chance of exiting.
			List<Passenger> passengers = b.getPassengers();
			for (Passenger p : passengers) {
				if (r.nextBoolean())
					p.exitBus();
			}
			// Each passenger at the station has a 50% chance of entering the bus.
			Station s = b.getStation();
			for (Passenger p : s.getPassengers()) {
				if (!passengers.contains(p)) // Make sure that a passenger who just got off doesn't get back on.
					p.enterBus(b);
			}
			// Send the bus over to a random other station.
			List<Station> stations = getStations();
			b.setDest(stations.get(r.nextInt(stations.size())));
		}
	}

}
