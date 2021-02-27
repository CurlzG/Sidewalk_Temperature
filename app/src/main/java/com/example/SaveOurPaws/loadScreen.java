package com.example.SaveOurPaws;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * This is the method for the Load Screen on the Save Our Paws App
 */
public class loadScreen extends AppCompatActivity {
    ProgressBar pgb;
    TextView pTxt;
    String content = "";
    private  JSONObject jsonObject;
    private String textValue = "";
    private static double lat;
    private static double lon;
    private static String api = "";
    private static DecimalFormat f = new DecimalFormat("0.0000000");
    String value = "";
    class Weather extends AsyncTask<String,Void,String> {
        /**
         * Loads the Api and returns the infromation got from open API weather website
         * @param strings
         * @return
         */
        @Override
        protected String doInBackground(String... strings) {
            //String... means multiple address can be send. It acts as array
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //Establish connection with address
                connection.connect();

                //retrieve data from url
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                //Retrieve data and return it as String
                int data = isr.read();
                String content = "";
                char ch;
                while (data != -1){
                    ch = (char) data;
                    content = content + ch;
                    data = isr.read();
                }
                return content;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    /**
     * This is the method for creating everything on after the load screen ends
     * @param savedInstanceState
     */
    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_activity);
        pgb = (ProgressBar) findViewById(R.id.progressBar);
        pTxt = findViewById(R.id.loadpre);
        getLocation();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView img = (ImageView) findViewById(R.id.pawImg);
        img.setBackgroundResource(R.drawable.loading);
        AnimationDrawable anime = (AnimationDrawable) img.getBackground();
        anime.start();
        pgb.setMax(100);
        pgb.setScaleY(3f);
        Weather wea = new Weather();
        try {
            content = wea.execute("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=alerts&appid=" + api).get();
            jsonObject = new JSONObject(content);
            JSONObject curr = getObject("current", jsonObject);
            value = predGrade(curr);
            progressAnimation(value,content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * From the JSONobject get any object from jObj with the String tagName
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    /**
     * Get String object from the jObj Object
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    /**
     * Get Double Object from the jObj Object
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static double getDouble(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getDouble(tagName);
    }

    /**
     * Get Int Object from the jObj Object
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }


    /**
     * 3 Grades
     * 1 -->   Less than 49 Degrees
     * 2 -->   Between 49 to 60 Degrees Possible Damage over time
     * 3 -->   Between 60 to 65 Degrees Damage will happen
     * 3 -->   Higher than 65 Degrees Serve Damage Will Happen.
     *
     *
     */
    private static String predGrade(JSONObject curr) throws JSONException {
        String less = "1";
        String kinda = "2";
        String will = "3";
        String serve = "4";
        double temp = KtoC(getDouble("temp",curr));
        double wSpeed = getDouble("wind_speed",curr);
        int clouds = getInt("clouds",curr);
        double uv = getDouble("uvi",curr);

        if((temp*2) < 49 && clouds > 50){
            return less;
        } if(temp > 20 && wSpeed < 2 && (clouds < 40 && clouds > 20) ){
            return kinda;
        } if(temp > 23 && wSpeed < 1 && clouds > 20){
            return will;
        } if(temp > 24 && wSpeed < 1 && clouds > 5 & uv > 5){
            return serve;
        }
        return "GLITCH IN THE MATRIX";

        //return less;
    }

    /**
     * Convert Kelvin value to Celsuis value
     * @param d
     * @return
     */
    private static double KtoC(double d) {
        return Double.parseDouble(f.format((d-273.15)));
    }
    /**
     * Calculating relative Humidity
     * @param c
     * @return
     */
    private static double rHum(Current c) {
        double temp = c.getTemp();
        double dew_point = c.getDew();
        temp = temp - 273.15;
        dew_point = dew_point - 273.15;
        temp = Double.parseDouble(f.format(temp));
        dew_point = Double.parseDouble(f.format(dew_point));
        double rh = 100*(Math.exp((17.625*dew_point)/(243.04+dew_point))/Math.exp((17.625*temp)/(243.04+temp))); //https://bmcnoldy.rsmas.miami.edu/Humidity.html
        return Double.parseDouble(f.format(rh));
    }

    /**
     * Gets lat and lon based on the locations of the phone
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        this.lat = Double.parseDouble(f.format(location.getLatitude()));
        this.lon = Double.parseDouble(f.format(location.getLongitude()));
    }

    /**
     *  Creates the Loading Screen
     * @param a
     * @param b
     */
    private void progressAnimation(String a,String b) {
        ProgressAnimation amin = new ProgressAnimation(a,b,this,pgb,pTxt,0f,100f);
        amin.setDuration(6000);
        pgb.setAnimation(amin);

    }


}
