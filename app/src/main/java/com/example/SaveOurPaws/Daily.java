package com.example.SaveOurPaws;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Daily {
	public int dt;
	public int sunrise;
	public int sunset;
	public Temp temp;
	public Temp feels_like;
	public int pressure;
	public int humidity;
	public double dew_point;
	public int visibility;
	public double wind_speed;
	public int wind_deg;
	public double wind_gust;
	public int clouds;
	public double pop;
	public double uvi;
	static DecimalFormat f = new DecimalFormat("0.00");
	public Daily() {
		
	}
	/**
	 * Calculating the Epoch time to read human time.
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date epochToTime(int time) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
		String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(Long.parseLong(String.valueOf(time))*1000)); 
		Date d = format.parse(date);
		return d;
	}
	
	private static double KtoC(double d) {
		return Double.parseDouble(f.format((d-273.15)));
	}
	
	@Override
	public String toString() {
		try {
			return "Time: " + epochToTime(dt) + " Sunrise: " +	epochToTime(sunrise) + " Sunset: " + epochToTime(sunset) + "\n Temp:" + temp.toString() + "\n Feels_Like: " + feels_like.toString() + "\n Pressure: " + pressure + " Humidity: " + humidity + " Dew_point: " + 
			dew_point + " Visibility: " + visibility + " \n windSpeed: " + wind_speed + " windDeg: " + wind_deg + " windGust: " + wind_gust+ " Clouds: " + clouds+ " Pop: " + pop + "  UV:" + uvi;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt + " Sunrise: " +	sunrise + " Sunset: " + sunset + "\nTemp " + temp.toString() + "\n Feels_like: "  + feels_like.toString() + "\n Pressure: " + pressure + " Humidity: " + humidity + " Dew_point " + 
		dew_point + " Visibility: " + visibility + " \n windSpeed: " + wind_speed + "  windDeg: " + wind_deg + " windGust: " + wind_gust+ " Clouds: " + clouds+ " Pop: " + pop + " UV: " + uvi;
	}
	
}
