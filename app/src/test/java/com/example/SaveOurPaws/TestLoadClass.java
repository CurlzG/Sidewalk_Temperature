package com.example.SaveOurPaws;

public class TestLoadClass {
    public double Tar = 0;
    public double WC = 0;
    public double Drt = 0;
    public double Grass = 0;
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

    public TestLoadClass(Double t, double wc, double d, double g,double tt, double fl,int p, int h, double dp, double uv, int c, int v, double ws, int wd){
        this.Tar = t;
        this.WC = wc;
        this.Drt = d;
        this.Grass = g;
        this.temp = tt;
        this.feels_like = fl;
        this.pressure = p;
        this.humidity = h;
        this.dew_point = dp;
        this.uvi = uv;
        this.clouds = c;
        this.visibility = v;
        this.wind_speed =ws;
        this.wind_deg = wd;
    }
    public double getT() {
        return Tar;
    }
    public double getWC() {
        return WC;
    }
    public double getD() {
        return Drt;
    }
    public double getG() {
        return Grass;
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
}
