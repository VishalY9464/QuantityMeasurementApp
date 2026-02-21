package com.apps;

import java.util.Objects;

/**
 * Immutable Value Object representing Length.
 * Conversion responsibility delegated to LengthUnit.
 */
public final class Length {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

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
        return unit.convertToBaseUnit(value);
    }

    // ---------------- UC5 : CONVERSION ----------------

    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        Objects.requireNonNull(source);
        Objects.requireNonNull(target);

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite.");

        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    public Length convertTo(LengthUnit targetUnit) {

        Objects.requireNonNull(targetUnit);

        double base = toBaseUnit();
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Length(converted, targetUnit);
    }

    // ---------------- ADDITION CORE ----------------

    private static Length addInternal(Length l1,
                                      Length l2,
                                      LengthUnit targetUnit) {

        double sumBase = l1.toBaseUnit() + l2.toBaseUnit();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Length(result, targetUnit);
    }

    // UC6
    public Length add(Length other) {
        Objects.requireNonNull(other);
        return addInternal(this, other, this.unit);
    }

    // UC7
    public static Length add(Length l1,
                             Length l2,
                             LengthUnit targetUnit) {

        Objects.requireNonNull(l1);
        Objects.requireNonNull(l2);
        Objects.requireNonNull(targetUnit);

        return addInternal(l1, l2, targetUnit);
    }

    // ---------------- EQUALITY ----------------

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