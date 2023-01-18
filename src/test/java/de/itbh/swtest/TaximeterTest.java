package de.itbh.swtest;

import static org.junit.jupiter.api.Assertions.*;

import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class TaximeterTest {
	Taximeter taximeter = new Taximeter();

	@Test
	void testClassExists() {
		//Arrange

		//Act

		//Assert
		assertNotNull(taximeter);
	}

	@Test
	void testImplementsITaximeter() {
		//Arrange

		//Act

		//Assert
		assertTrue(taximeter instanceof ITaximeter);
	}

	@ParameterizedTest(name = "{index}: startTime{0}, expected{1}")
	@MethodSource("testNightRideData")
	void testNightRide(LocalTime startTime, boolean expected) {
		//Arrange

		//Act
		boolean result = taximeter.isNightRide(startTime);

		//Assert
		assertEquals(expected, result);
	}

	static Stream<Arguments> testNightRideData() {
		return Stream.of(
				Arguments.of(LocalTime.parse("21:59:59"), false),
				Arguments.of(LocalTime.parse("22:00:00"), true),
				Arguments.of(LocalTime.parse("22:00:01"), true),
				Arguments.of(LocalTime.parse("05:59:59"), true),
				Arguments.of(LocalTime.parse("06:00:00"), false),
				Arguments.of(LocalTime.parse("06:00:01"), false)
		);
	}

	@ParameterizedTest(name = "{index}: distance={0}, startTime={1}, luggage={2}, expected={3}")
	@MethodSource("testCalculateData")
	void testRide(int distance, LocalTime startTime, boolean luggage, int expected) {
		//Arrange

		//Act
		int result = taximeter.calculate(distance, startTime, luggage);

		//Assert
		assertEquals(expected, result);
	}

	static Stream<Arguments> testCalculateData() {
		return Stream.of(
				Arguments.of(5, null, false, -1),
				Arguments.of(-5, LocalTime.parse("12:00:00"), false, -1),
				Arguments.of(0, LocalTime.parse("12:00:00"), false, -1),
				Arguments.of(9, LocalTime.parse("12:00:00"), false, 900),
				Arguments.of(10, LocalTime.parse("05:00:00"), false, 1140),
				Arguments.of(11, LocalTime.parse("12:00:00"), false, 1045),
				Arguments.of(19, LocalTime.parse("12:00:00"), true, 2090),
				Arguments.of(20, LocalTime.parse("12:00:00"), false, 1800),
				Arguments.of(21, LocalTime.parse("12:00:00"), false, 1890)
		);
	}
}
