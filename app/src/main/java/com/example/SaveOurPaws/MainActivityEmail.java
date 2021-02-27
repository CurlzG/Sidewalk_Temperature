package com.example.SaveOurPaws;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

/**
 * Class for Sending Emails for the purpose of future debugging and testing
 */
public class MainActivityEmail extends AppCompatActivity {
    private EditText tVal, wcVal, dVal, gVal;
    private Button btnSend;
    private String textValue = "";

    private Date currentTime = Calendar.getInstance().getTime();

    /**
     * On Create creating the email page
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_main);
        tVal = findViewById(R.id.tarTemp);
        wcVal = findViewById(R.id.WCTemp);
        dVal = findViewById(R.id.dirtTemp);
        gVal = findViewById(R.id.dgRange);
        btnSend = findViewById(R.id.emLoad);

        tVal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                   tVal.setText("");
                }
            }
        });
        wcVal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    wcVal.setText("");
                }
            }
        });
        dVal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dVal.setText("");
                }
            }
        });
        gVal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    gVal.setText("");
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textValue += "Tarmac: " + tVal.getText() + "C \n" + "White Concrete: " + wcVal.getText() + "C\n" + "Dirt: " + dVal.getText() + "C\n" + "Grass: " + gVal.getText() + "C\n";
                try {
                    String text = "";
                    Intent it = getIntent();
                    String sessionId = it.getStringExtra("JSONVALUE");
                    JSONObject jsonObject = new JSONObject(sessionId);
                    JSONObject curr = getObject("current",jsonObject);
                    text += "Tarmac: " + tVal.getText() + "\n White Concrete: "  + wcVal.getText() + "\n Dirt: " + dVal.getText() + "\n Grass: " +gVal.getText() + "\n";
                    text += "Value For The Ground\n Temp:" + getDouble("temp",curr) + "\nFeels_like: " + getDouble("feels_like",curr) + "\nPressure: " + getInt("pressure",curr) + "\nHumidity: " + getInt("humidity",curr)  + "\nDewPoint: " + getDouble("dew_point",curr)
                            +"\n UVI: " + getDouble("uvi",curr) + "\nClouds: " +  getInt("clouds",curr) + "\nVisibility:  " + getInt("visibility",curr)  + "\nWindSpeed: " + getDouble("wind_speed",curr) + "\nWindDirection: " + getInt("wind_deg",curr) + "\n";

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "saveourpaws0@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Update: " + currentTime);
                    intent.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
          }

    /**
     * Returns an object from JsonObject can be selected from the String tagname
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
     * Returns a String object selected from jObj Object using the String tagname
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    /**
     *Returns a Double object rom jObj Object from String tagname
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static double getDouble(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getDouble(tagName);
    }

    /**
     * Returns a Int object from jObj Object From a string tagName
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
