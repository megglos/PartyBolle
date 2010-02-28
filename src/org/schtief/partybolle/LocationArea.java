package org.schtief.partybolle;

public class LocationArea {
	private int longWest;
	private int longEast;
	private int latNorth;
	private int latSouth;
	/**
	 * @param longWest
	 * @param longEast
	 * @param latNorth
	 * @param latSouth
	 */
	public LocationArea(int longWest, int longEast, int latNorth, int latSouth) {
		super();
		this.longWest = longWest;
		this.longEast = longEast;
		this.latNorth = latNorth;
		this.latSouth = latSouth;
	}
	/**
	 * @return the longWest
	 */
	public int getLongWest() {
		return longWest;
	}
	/**
	 * @return the longEast
	 */
	public int getLongEast() {
		return longEast;
	}
	/**
	 * @return the latNorth
	 */
	public int getLatNorth() {
		return latNorth;
	}
	/**
	 * @return the latSouth
	 */
	public int getLatSouth() {
		return latSouth;
	}
	
	

}
