package de.itbh.swtest;

import org.joda.time.LocalTime;

public class Taximeter implements ITaximeter {

	@Override
	public int calculate(int distance, LocalTime startTime, boolean luggage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isNightride(LocalTime startTime) {
		// TODO Auto-generated method stub
		return false;
	}

}
