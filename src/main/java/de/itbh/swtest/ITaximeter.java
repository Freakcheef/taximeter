package de.itbh.swtest;

import org.joda.time.LocalTime;

public interface ITaximeter {

	/**
	 * 
	 * Calculates the price for taxis in cent. Every kilometer costs 1 EUR. You get
	 * 5% discount for distances 10km and more. You get 10% discount for 20km and
	 * more Nightrides which started between 2200 and 0600 are 20% more expensive.
	 * Baseprice is 3 Euro if you have luggage.
	 * 
	 * Priorities for calculation: Baseprice (3 Euro) -> Discount (5% and 10%
	 * discount) -> Upcharge (20% for nightrides)
	 * 
	 * @param distance
	 * @return price for the ride
	 */
	public int calculate(int distance, LocalTime startTime, boolean luggage);

	/**
	 * Calculates if a given starttime results in a nightride
	 * 
	 * @param startTime
	 * @return if nightride
	 */
	public boolean isNightRide(LocalTime startTime);
}