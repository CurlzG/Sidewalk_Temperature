package com.example.SaveOurPaws;

import java.text.DecimalFormat;

public class Temp {
public double day;
public double min;
public double max;
public double night;
public double eve;
public double morn;
static DecimalFormat f = new DecimalFormat("0.00");
public Temp() {
	
}
private static double KtoC(double d) {
	return Double.parseDouble(f.format((d-273.15)));
}

@Override 
public String toString() {
	return "Day: " + KtoC(day) + " Min: "+ KtoC(min) + " Max: " + KtoC(max) + " Night: " + KtoC(night) + " Eve: " + eve + " Morn: " + KtoC(morn);   
}
}
