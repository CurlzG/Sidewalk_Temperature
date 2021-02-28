package com.example.SaveOurPaws;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static DecimalFormat f = new DecimalFormat("0.0000000");
    /**
     * This test is testing the Tarmac temperature on this record reading
     */
    @Test
    public void Test_01_T() {
        TestLoadClass tlc = new TestLoadClass(24.00,22.00,21.00,21.00,289.7,288.28,1012,77,285.66,1.59,100,10000,3.13,129);
        double val = predTar(tlc.getTemp(),tlc.getFeel(),tlc.getPress(),tlc.getHum(),tlc.getDew(),tlc.getUvi(),tlc.getClouds(),tlc.getV(),tlc.get_Wspeed(),tlc.get_Wd());
        assertEquals(val, tlc.getT(),0);
    }
    /**
     * This test is testing the Tarmac temperature on this record reading
     */
    @Test
    public void Test_02_T() {
        TestLoadClass tlc = new TestLoadClass(34.00,33.00,20.00,22.00,287.25,276.74,1007,78,283.48,4.11,100,10000,15.2,152);
        double val = predTar(tlc.getTemp(),tlc.getFeel(),tlc.getPress(),tlc.getHum(),tlc.getDew(),tlc.getUvi(),tlc.getClouds(),tlc.getV(),tlc.get_Wspeed(),tlc.get_Wd());
        assertEquals(val, tlc.getT(),0);
    }

    /**
     * Testing Tarmac Estimated Value Range
     * Because of this application been created for New Zealand envrioment I will be working the in Celsuis
     * @param tt ground temp
     * @param fl feels like temp
     * @param p pressure
     * @param h humimidty
     * @param dp dew point
     * @param uv uvi
     * @param c clouds
     * @param v visibility
     * @param ws wind speed
     * @param wd wind direction
     * @return
     */
    private double predTar(double tt, double fl,int p, int h, double dp, double uv, int c, int v, double ws, int wd) {
        double value = 0;
        double temp = KtoC(tt);
        double feels_temp = KtoC(fl);
        int pressure = p;
        int humidity = h;
        double dewPoint = dp;
        double uvi = uv;
        int clouds = c;
        int visi = v;
        double windspeed = ws;
        int windDirection = wd;

        value += temp*2;
        System.out.println(value);
        //high UV
        if(uvi >= 6){
            value += value * 0.15;
        }
        //Very High UV
        else if(uvi >= 8){
            value += value * 0.25;
        }
        //Extreme UV
        else if(uvi >= 12){
            value += value * 0.50;
        }

        if(clouds == 100){
            value -= value * 0.05;
        } else if( clouds > 20){
            value += value * 0.15;
        }
        if(windspeed < 4) {
            value += value * 0.15;
        } else if(windspeed > 4){
            value -= value *0.25;
         }else if(windspeed < 2){
            value += value *0.15;
        }

        return value;
    }

    /**
     * Convert Kelvin value to Celsuis value
     * @param d
     * @return
     */
    private static double KtoC(double d) {
        return Double.parseDouble(f.format((d-273.15)));
    }


}
