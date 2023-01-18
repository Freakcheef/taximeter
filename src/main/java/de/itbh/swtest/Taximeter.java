package de.itbh.swtest;

import org.joda.time.LocalTime;

public class Taximeter implements ITaximeter {

    private static final LocalTime START_OF_NIGHT_RIDE = LocalTime.parse("21:59:59");
    private static final LocalTime END_OF_NIGHT_RIDE = LocalTime.parse("06:00:00");
    public static final int CENT_PER_KM = 100;
    private static final int FIRST_DISCOUNT_DISTANCE = 10;
    public static final double FIRST_DISCOUNT_PERCENT = 0.95;
    private static final int SECOND_DISCOUNT_DISTANCE = 20;
    public static final double SECOND_DISCOUNT_PERCENT = 0.9;
    public static final int LUGGAGE_FEE = 3;
    public static final double NITRIDE_PERCENT = 1.2;
    public static final int DEFAULT_ERROR_PRICE = -1;

    @Override
    public int calculate(int distance, LocalTime startTime, boolean luggage) {
        int price;
        if (startTime != null && distance > 0) {
            price = calculateBasePrice(distance, luggage);
            price = addDiscount(distance, price);
            price = addNightRideCharge(price, startTime);
        } else {
            price = DEFAULT_ERROR_PRICE;
        }
        return price;
    }

    @Override
    public boolean isNightRide(LocalTime startTime) {
        return startTime.isAfter(START_OF_NIGHT_RIDE) || startTime.isBefore(END_OF_NIGHT_RIDE);
    }

    private int calculateBasePrice(int distance, boolean luggage) {
        int price;
        if (luggage) {
            price = (distance + LUGGAGE_FEE) * CENT_PER_KM;
        } else {
            price = distance * CENT_PER_KM;
        }
        return price;
    }

    private int addDiscount(int distance, int price) {
        if (distance >= FIRST_DISCOUNT_DISTANCE) {
            if (distance >= SECOND_DISCOUNT_DISTANCE) {
                price = (int) (price * SECOND_DISCOUNT_PERCENT);
            } else {
                price = (int) (price * FIRST_DISCOUNT_PERCENT);
            }
        }
        return price;
    }

    private int addNightRideCharge(int price, LocalTime startTime) {
        if (isNightRide(startTime)) {
            price = (int) (price * NITRIDE_PERCENT);
        }
        return price;
    }
}
