package com.apps;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Equality Demo:");
        System.out.println(l1 + " == " + l2 + " ? " + l1.equals(l2));

        System.out.println("\nConversion Demo:");

        System.out.println("1 FEET -> INCHES = " +
                Length.convert(1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES));

        System.out.println("3 YARDS -> FEET = " +
                Length.convert(3.0,
                        Length.LengthUnit.YARDS,
                        Length.LengthUnit.FEET));

        System.out.println("36 INCHES -> YARDS = " +
                Length.convert(36.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.YARDS));

        System.out.println("1 CM -> INCHES = " +
                Length.convert(1.0,
                        Length.LengthUnit.CENTIMETERS,
                        Length.LengthUnit.INCHES));
    }
}