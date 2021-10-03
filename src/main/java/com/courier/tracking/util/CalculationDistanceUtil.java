package com.courier.tracking.util;

import org.apache.lucene.util.SloppyMath;

public final class CalculationDistanceUtil {

	private CalculationDistanceUtil() {
	}

	public double calculateDistanceInMeters(double lat1, double long1, double lat2, double long2) {
		return SloppyMath.haversinMeters(lat1, long1, lat2, long2);
	}
}
