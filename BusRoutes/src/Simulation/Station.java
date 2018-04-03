package Simulation;

/**
 * A class representing a bus station. Passengers start randomly distributed
 * among the stations and are only removed from the simulation once they arrive
 * at their station of choice.
 * 
 * @author Eliot J. Kaplan
 *
 */
public class Station extends Location {

	private int x, y;

	Station(BusSimulator sim, int x, int y) {
		super(sim);
		this.x = x;
		this.y = y;
	}

	/**
	 * Determines the manhattan distance from this station to another one. Because
	 * busses move one space per timestep, it will take this many steps to get from
	 * this station to the other one.
	 * 
	 * @param other
	 *            The station to measure distance to
	 * @return the manhattan distance from this station to other.
	 */
	public int manhattanDistance(Station other) {
		return Math.abs(x - other.x) + Math.abs(y - other.y);
	}

	/**
	 * 
	 * @return The x-coordinate where this station is located
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return The y-coordinate where this station is located
	 */
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Station [x=" + x + ", y=" + y + "]";
	}

}
