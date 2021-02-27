package com.example.SaveOurPaws;

public class Info {

	public String lat;
    public String lon;
    public String timezone;              
    public int timezone_offset;
    public Current current;
    public Minutely[] minutely;
    public Hourly[] hourly;
    public Daily[] daily;
    public String getLat() {
    	return lat;
    }
    public String getLon() {
    	return lon;
    }
    public String getTz() {
    	return timezone;
    }
    public int getTzo() {
    	return timezone_offset;
    }
    public Current getC() {
    	return current;
    }
    
   
    public Daily[] getDaily() {
    	return daily;
    }
    public Hourly[] getHourly() {
    	return hourly;
    }
    
    /**
     * Return the average amount of precipitation over the next coming hour
     * @return
     */
    public int getMinP() {
    	int num = 0;
    	for(int i = 0; i < minutely.length;i++) {
    		num += minutely[i].getP();
    	}
    	num = num/minutely.length;
    	return num;
    }
    
    @Override
    public String toString() {
    	String minute = "--------------Minuntely--------------\n";
    	String hour = "--------------Hourly--------------\n";
    	String dail = "--------------Daily--------------\n";
    	for(int i = 0; i < minutely.length; i++ ) {
    		minute += i + " " + minutely[i].toString() + " \n";
    	}
    	for(int i = 0; i < hourly.length; i++ ) {
    		hour += i + " " + hourly[i].toString() + " \n";
    	}
    	for(int i = 0; i < daily.length; i++ ) {
    		dail += i + " " + daily[i].toString() + " \n";
    	}
    	
    	return "\n Info Class \n lat: " + lat + " lon: " + lon + " timezone: " + timezone + " timezoneoffset: " + timezone_offset + "\n" + current.toString()+ "\n" + minute + "\n" + hour + "\n" + dail ;
    			
    }
    
    
}
