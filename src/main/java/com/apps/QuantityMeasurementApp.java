package com.apps;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Equality Demo:");
        System.out.println(l1 + " == " + l2 + " ? " + l1.equals(l2));

        System.out.println("\nAddition Demo:");

        Length result1 = l1.add(l2);
        System.out.println("1 FEET + 12 INCHES = " + result1);

        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);

        Length result2 = yard.add(feet);
        System.out.println("1 YARD + 3 FEET = " + result2);

        Length result3 = Length.add(l1, l2, Length.LengthUnit.INCHES);
        System.out.println("Static add result in INCHES = " + result3);
    }
}