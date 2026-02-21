package com.apps;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityWeightTest {

    private static final double EPSILON = 1e-6;

    // ================= EQUALITY =================

    @Test
    public void testEquality_KgToKg() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    public void testEquality_KgToGram() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    public void testEquality_KgToPound() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.20462, WeightUnit.POUND)
        );
    }

    @Test
    public void testEquality_GramToPound() {
        assertEquals(
                new QuantityWeight(453.592, WeightUnit.GRAM),
                new QuantityWeight(1.0, WeightUnit.POUND)
        );
    }

    @Test
    public void testEquality_NullComparison() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(null));
    }

    // ================= CONVERSION =================

    @Test
    public void testConversion_KgToGram() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_PoundToKg() {
        QuantityWeight result =
                new QuantityWeight(2.20462, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    @Test
    public void testConversion_RoundTrip() {

        QuantityWeight original =
                new QuantityWeight(1.5, WeightUnit.KILOGRAM);

        QuantityWeight roundTrip =
                original.convertTo(WeightUnit.GRAM)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(original.getValue(),
                roundTrip.getValue(),
                EPSILON);
    }

    // ================= ADDITION =================

    @Test
    public void testAddition_SameUnit() {

        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit() {

        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTarget() {

        QuantityWeight result =
                QuantityWeight.add(
                        new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                        new QuantityWeight(1000.0, WeightUnit.GRAM),
                        WeightUnit.GRAM
                );

        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testAddition_Commutativity() {

        QuantityWeight w1 =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight r1 = w1.add(w2);
        QuantityWeight r2 = w2.add(w1);

        assertTrue(r1.equals(r2));
    }

    // ================= CATEGORY SAFETY =================

    @Test
    public void testWeightVsLength_Incompatible() {

        QuantityWeight weight =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        Length length =
                new Length(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }
}