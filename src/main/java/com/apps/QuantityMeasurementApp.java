package com.apps;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("UC7 Demo:");

        System.out.println(
                Length.add(l1, l2, Length.LengthUnit.FEET));

        System.out.println(
                Length.add(l1, l2, Length.LengthUnit.INCHES));

        System.out.println(
                Length.add(l1, l2, Length.LengthUnit.YARDS));
    }
}