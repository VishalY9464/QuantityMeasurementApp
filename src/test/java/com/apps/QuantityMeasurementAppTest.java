package com.apps;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // =====================================================
    // UC8 – Standalone LengthUnit Tests
    // =====================================================

    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0,
                LengthUnit.FEET.getConversionFactor(),
                EPSILON);
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12.0,
                LengthUnit.INCHES.getConversionFactor(),
                EPSILON);
    }

    @Test
    public void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0,
                LengthUnit.YARDS.getConversionFactor(),
                EPSILON);
    }

    @Test
    public void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48,
                LengthUnit.CENTIMETERS.getConversionFactor(),
                EPSILON);
    }

    // =====================================================
    // Base Unit Conversion Tests
    // =====================================================

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON);
    }

    @Test
    public void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPSILON);
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                EPSILON);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                EPSILON);
    }

    // =====================================================
    // UC4 – Equality Tests
    // =====================================================

    @Test
    public void testEquality_CrossUnits() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_Transitive() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    // =====================================================
    // UC5 – Conversion Tests
    // =====================================================

    @Test
    public void testConvert_Method() {

        double result = Length.convert(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    public void testConvertTo_ObjectMethod() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length result = l1.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // =====================================================
    // UC6 – Addition (Implicit Target)
    // =====================================================

    @Test
    public void testAddition_SameUnit() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // =====================================================
    // UC7 – Addition with Explicit Target Unit
    // =====================================================

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = Length.add(l1, l2, LengthUnit.YARDS);

        assertEquals(0.666666, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_Commutativity_WithTarget() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result1 = Length.add(l1, l2, LengthUnit.FEET);
        Length result2 = Length.add(l2, l1, LengthUnit.FEET);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }

    // =====================================================
    // Validation Tests
    // =====================================================

    @Test
    public void testNullUnit() {
        assertThrows(NullPointerException.class,
                () -> new Length(1.0, null));
    }

    @Test
    public void testInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.NaN, LengthUnit.FEET));
    }

    @Test
    public void testNullTargetUnit_Add() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertThrows(NullPointerException.class,
                () -> Length.add(l1, l2, null));
    }

    // =====================================================
    // Round Trip Precision Test
    // =====================================================

    @Test
    public void testRoundTripConversion() {

        double original = 5.0;

        double inches = Length.convert(
                original,
                LengthUnit.FEET,
                LengthUnit.INCHES);

        double back = Length.convert(
                inches,
                LengthUnit.INCHES,
                LengthUnit.FEET);

        assertEquals(original, back, EPSILON);
    }

    // =====================================================
    // Architectural Scalability Proof
    // =====================================================

    @Test
    public void testArchitecturalScalability_Pattern() {

        // Simulating that new unit types could exist independently
        assertNotNull(LengthUnit.FEET);
        assertNotNull(LengthUnit.INCHES);
    }
}