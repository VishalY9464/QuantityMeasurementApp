package com.apps;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println(l1.convertTo(LengthUnit.INCHES));
        System.out.println(Length.add(l1, l2, LengthUnit.YARDS));
        System.out.println(new Length(36.0, LengthUnit.INCHES)
                .equals(new Length(1.0, LengthUnit.YARDS)));
    }
}