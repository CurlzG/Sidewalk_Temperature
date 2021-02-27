package com.example.SaveOurPaws;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Current {
	public int dt;
	public int sunrise;
	public int sunset;
	public double temp;
	public double feels_like;
	public int pressure;
	public int humidity;
	public double dew_point;
	public double uvi;
	public int clouds;
	public int visibility;
	public double wind_speed;
	public int wind_deg;
	public double wind_gust;
	static DecimalFormat f = new DecimalFormat("0.00");
	public int getDt() {
		return dt;
	}
	
	public int getRise() {
		return sunrise;
	}
	public int getSet() {
		return sunset;
	}
	public double getTemp() {
		return temp;
	}
	public double getFeel() {
		return feels_like;
	}
	public int getPress() {
		return pressure;
	}
	public int getHum() {
		return humidity;
	}
	public double getDew() {
		return dew_point;
	}
	public double getUvi() {
		return uvi;
	}
	public int getClouds() {
		return clouds;
	}
	public int getV() {
		return visibility;
	}
	public double get_Wspeed() {
		return wind_speed;
	}
	public int get_Wd() {
		return wind_deg;
	}
	public double get_Wg() {
		return wind_gust;
	}
	private static double KtoC(double d) {
		return Double.parseDouble(f.format((d-273.15)));
	}
	
	/**
	 * Calculating the Epoch time to read human time.
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date epochToTime(int time) throws ParseException {
		DateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
		String date = new SimpleDateFormat("HH:mm:ss").format(new Date(Long.parseLong(String.valueOf(time))*1000)); 
		Date d = format.parse(date);
		return d;
	}
	@Override
	public String toString() {
		try {
			return "\n Current Class \n dt: " + epochToTime(dt) + " \n sunrise: " + epochToTime(sunrise) + " sunset: " + epochToTime(sunset) + "\n temp: " + KtoC(temp) + "C feels_like: " + KtoC(feels_like) + "C \n pressure: " + pressure + " humidity: " + humidity + "  dew_point: " + dew_point + " uvi: " + uvi + "\n clouds: " + clouds + " visibility: " +visibility + " \n wind_speed: " + wind_speed + " wind_deg: " + wind_deg + " wind_gust: " +  wind_gust;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return "\n Current Class \n dt: " + dt + " \n sunrise: " + sunrise + " sunset: " + sunset + "\n temp: " + temp + " feels_like: " + feels_like + " \n pressure: " + pressure + " humidity: " + humidity + "  dew_point: " + dew_point + " uvi: " + uvi + "\n clouds: " + clouds + " visibility: " +visibility + " \n wind_speed: " + wind_speed + " wind_deg: " + wind_deg + " wind_gust: " +  wind_gust;
	}
}
