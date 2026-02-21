package com.apps;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ---------------- UC6 ADDITION TESTS ----------------

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = feet.add(inches);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        Length feet = new Length(1.0, Length.LengthUnit.FEET);

        Length result = inches.add(feet);

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_Commutativity() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result1 = l1.add(l2);
        Length result2 = l2.add(l1);

        assertEquals(result1, result2);
    }

    @Test
    public void testAddition_WithZero() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length zero = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = l1.add(zero);

        assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NegativeValues() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_LargeValues() {
        Length l1 = new Length(1e6, Length.LengthUnit.FEET);
        Length l2 = new Length(1e6, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertEquals(new Length(2e6, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NullOperand() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);

        assertThrows(NullPointerException.class, () -> l1.add(null));
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = Length.add(l1, l2, Length.LengthUnit.FEET);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = Length.add(l1, l2, Length.LengthUnit.INCHES);

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

 

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result1 = Length.add(l1, l2, Length.LengthUnit.YARDS);
        Length result2 = Length.add(l2, l1, Length.LengthUnit.YARDS);

        assertEquals(result1, result2);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTarget() {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertThrows(NullPointerException.class, () ->
                Length.add(l1, l2, null));
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = Length.add(l1, l2, Length.LengthUnit.YARDS);

        assertEquals(0.666667, result.getValue(), 1e-6);
        assertEquals(Length.LengthUnit.YARDS, result.getUnit());
    }
}