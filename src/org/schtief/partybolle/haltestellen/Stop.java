package org.schtief.partybolle.haltestellen;

public class Stop {
	private int lat;
	private int lon;
	private int stationId;
	private int mastId;
	private String name;
	
	
	/**
	 * @param lat
	 * @param lon
	 * @param stationId
	 * @param mastId
	 * @param name
	 */
	public Stop(int lat, int lon, String name, int stationId, int mastId) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.stationId = stationId;
		this.mastId = mastId;
		this.name = name;
	}


	/**
	 * @return the lat
	 */
	public int getLat() {
		return lat;
	}


	/**
	 * @return the lon
	 */
	public int getLon() {
		return lon;
	}


	/**
	 * @return the stationId
	 */
	public int getStationId() {
		return stationId;
	}


	/**
	 * @return the mastId
	 */
	public int getMastId() {
		return mastId;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
	
}
