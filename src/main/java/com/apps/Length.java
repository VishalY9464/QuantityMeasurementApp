package com.apps;

import java.util.Objects;

/**
 * Immutable Value Object representing Length.
 * Base Unit = INCHES
 */
public final class Length {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

    // ---------------- ENUM ----------------

    public enum LengthUnit {

        INCHES(1.0),
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

    // ---------------- CONSTRUCTOR ----------------

    public Length(double value, LengthUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite.");

        this.unit = Objects.requireNonNull(unit, "Unit cannot be null.");
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // ---------------- BASE NORMALIZATION ----------------

    private double toBaseUnit() {
        return unit.toInches(value);
    }

    // ---------------- ROUNDING UTILITY ----------------

    private static double round(double value) {
        return Math.round(value * 1_000_000d) / 1_000_000d;
    }

    // ---------------- UC5 : CONVERSION ----------------

    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite.");

        Objects.requireNonNull(source, "Source unit cannot be null.");
        Objects.requireNonNull(target, "Target unit cannot be null.");

        double inches = source.toInches(value);
        double result = target.fromInches(inches);

        return round(result);
    }

    public Length convertTo(LengthUnit targetUnit) {

        Objects.requireNonNull(targetUnit, "Target unit cannot be null.");

        double inches = toBaseUnit();
        double converted = targetUnit.fromInches(inches);

        return new Length(round(converted), targetUnit);
    }

    // ---------------- PRIVATE ADDITION CORE ----------------

    private static Length addInternal(Length l1,
                                      Length l2,
                                      LengthUnit targetUnit) {

        double sumInBase = l1.toBaseUnit() + l2.toBaseUnit();
        double resultValue = targetUnit.fromInches(sumInBase);

        return new Length(round(resultValue), targetUnit);
    }

    // ---------------- UC6 ----------------

    public Length add(Length other) {

        Objects.requireNonNull(other, "Second operand cannot be null.");

        return addInternal(this, other, this.unit);
    }

    // ---------------- UC7 ----------------

    public static Length add(Length l1,
                             Length l2,
                             LengthUnit targetUnit) {

        Objects.requireNonNull(l1, "First operand cannot be null.");
        Objects.requireNonNull(l2, "Second operand cannot be null.");
        Objects.requireNonNull(targetUnit, "Target unit cannot be null.");

        return addInternal(l1, l2, targetUnit);
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
        return String.format("Quantity(%.6f, %s)", value, unit);
    }
}