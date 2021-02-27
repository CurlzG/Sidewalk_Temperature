package com.example.SaveOurPaws;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Hourly {
	public int dt;
	public double temp;
	public double feels_like;
	public int pressure;
	public int humidity;
	public double dew_point;
	public double uvi;
	public int clouds;
	public int visibility;
	public double wind_speed;
	public double wind_gust;
	public double wind_deg;
	public double pop;
	static DecimalFormat f = new DecimalFormat("0.00");
	public Hourly() {
		
	}
	public double getPop() {
		return temp;
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
			return "Time: " + epochToTime(dt).toString() + " Temp: " + KtoC(temp)  + " feels_Like: " + KtoC(feels_like) + " Pressure: " + pressure + "  Humidity: " + humidity + " dewPoint: " + dew_point + " UV: " + uvi + " Clouds: " + clouds + " visibility: " + visibility + " windSpeed: " + wind_speed + " windGust: " + wind_gust + " windDeg: " + wind_deg + " Pop: " + pop;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Time: " + dt + " Temp: " + KtoC(temp)  + " feels_Like: " + KtoC(feels_like) + " Pressure: " + pressure + "  Humidity: " + humidity + " dewPoint: " + dew_point + " UV: " + uvi + " Clouds: " + clouds + " visibility: " + visibility + " windSpeed: " + wind_speed + " windGust: " + wind_gust + " windDeg: " + wind_deg + " Pop: " + pop;
	}
}
