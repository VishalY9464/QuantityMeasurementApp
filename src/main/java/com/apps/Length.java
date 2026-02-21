package com.apps;

import java.util.Objects;

/**
 * Immutable value object representing a length.
 * Base unit = INCHES.
 */
public final class Length {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;
    
    /**
     * Enum storing conversion factors relative to INCHES.
     */
    public enum LengthUnit {

        INCHES(1.0),        // Base unit
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double toInchesFactor;

        LengthUnit(double toInchesFactor) {
            this.toInchesFactor = toInchesFactor;
        }

        public double toInches(double value) {
            return value * toInchesFactor;
        }

        public double fromInches(double inches) {
            return inches / toInchesFactor;
        }
    }

    public Length(double value, LengthUnit unit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite.");
        }

        this.unit = Objects.requireNonNull(unit, "Unit cannot be null.");
        this.value = value;
    }

    public double getValue() {
        return value;
    }
    
    public LengthUnit getUnit() {
        return unit;
    }

    // Normalize to base unit
    private double toBaseUnit() {
        return unit.toInches(value);
    }

    // ---------------- UC5 STATIC CONVERSION ----------------

    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite.");

        Objects.requireNonNull(source, "Source unit cannot be null.");
        Objects.requireNonNull(target, "Target unit cannot be null.");

        double inches = source.toInches(value);
        return target.fromInches(inches);
    }

    // Instance conversion
    public Length convertTo(LengthUnit targetUnit) {

        Objects.requireNonNull(targetUnit, "Target unit cannot be null.");

        double inches = toBaseUnit();
        double converted = targetUnit.fromInches(inches);

        return new Length(converted, targetUnit);
    }

    // ---------------- UC4 EQUALITY ----------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Length)) return false;

        Length other = (Length) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit);
    }
}