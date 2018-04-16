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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + x;
		result = prime * result + y;
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
