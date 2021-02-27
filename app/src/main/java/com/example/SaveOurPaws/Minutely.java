package com.example.SaveOurPaws;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Minutely {
	public int dt;
	public int precipitation;
	
	public Minutely() {
	
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
	
	public int getP() {
		return precipitation;
	}
	@Override
	public String toString() {
		try {
			return "Time: " + epochToTime(dt).toString() + " precipitation: " + precipitation;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Time: " + dt + " Precipitation:" + precipitation;
	}
}
