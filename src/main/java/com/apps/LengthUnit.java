package com.apps;

/**
 * Standalone enum responsible for unit conversions.
 * Base Unit = FEET
 */
public enum LengthUnit {

    FEET(1.0),                  // Base unit
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    /**
     * Convert value in this unit to base unit (FEET)
     */
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    /**
     * Convert base unit (FEET) to this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }

    public double getConversionFactor() {
        return toFeetFactor;
    }
}