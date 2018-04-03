package Coordinators;

import Simulation.*;

public class BusRouteRunner {

	public static void main(String[] args) {
		BusCoordinator coord = new BadCoordinator();
		BusSimulator sim = new BusSimulator(coord, 100, 100, 200, 40, 6, 8);
		int time = sim.run();
		System.out.println("Completed in " + time + " timesteps");
	}

}
