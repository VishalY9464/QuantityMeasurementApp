package com.apps;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ---------- UC4 Equality Tests ----------

    @Test
    public void testEquality_CrossUnit() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        assertEquals(feet, inches);
    }

    @Test
    public void testEquality_Transitive() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);

        assertEquals(yard, feet);
        assertEquals(feet, inches);
        assertEquals(yard, inches);
    }

    @Test
    public void testEquality_Different() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);
        assertNotEquals(l1, l2);
    }

    // ---------- UC5 Conversion Tests ----------

    @Test
    public void testConvert_FeetToInches() {
        assertEquals(12.0,
                Length.convert(1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConvert_YardsToFeet() {
        assertEquals(9.0,
                Length.convert(3.0,
                        Length.LengthUnit.YARDS,
                        Length.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    public void testConvert_InchesToYards() {
        assertEquals(1.0,
                Length.convert(36.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    public void testConvert_SameUnit() {
        assertEquals(5.0,
                Length.convert(5.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    public void testConvert_RoundTrip() {

        double original = 2.5;

        double inches = Length.convert(original,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);

        double back = Length.convert(inches,
                Length.LengthUnit.INCHES,
                Length.LengthUnit.FEET);

        assertEquals(original, back, EPSILON);
    }

    @Test
    public void testConvert_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () ->
                Length.convert(Double.NaN,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES));
    }
}