package RunCode;

import Simulation.*;

public class BusRouteRunner {

	public static void main(String[] args) {
		BusCoordinator coord = new BadCoordinator(); // Replace this with your coordinator.
		// BusSimulator arguments: coordinator, width, height, # passengers, # stations, # busses, bus capacity, RNG seed (optional)
		BusSimulator sim = new BusSimulator(coord, 100, 100, 157, 20, 6, 8);
		int time = sim.run();
		System.out.println("Completed in " + time + " timesteps");
	}

}
