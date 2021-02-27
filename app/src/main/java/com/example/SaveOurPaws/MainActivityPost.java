package com.example.SaveOurPaws;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for Creating an image to post
 */
public class MainActivityPost extends AppCompatActivity {
    private EditText tVal, wcVal, dVal, gVal;
    private Button btnSend;
    private Date currentTime = Calendar.getInstance().getTime();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_main);
        tVal = findViewById(R.id.tarTemp);
        wcVal = findViewById(R.id.WCTemp);
        dVal = findViewById(R.id.dirtTemp);
        gVal = findViewById(R.id.dgRange);
        btnSend = findViewById(R.id.savePost);
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
                OutputStream outstream;
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                dir.mkdirs();
                File file = new File(dir,System.currentTimeMillis() + ".jpg");
                try {
                    Bitmap bmap = writeOnDrawable(tVal.getText().toString(),wcVal.getText().toString(),dVal.getText().toString(),gVal.getText().toString());
                    outstream = new FileOutputStream(file);
                    bmap.compress(Bitmap.CompressFormat.PNG, 100,outstream);
                    MediaStore.Images.Media.insertImage(getContentResolver(), bmap, "Ground Temp" , "This is the description");
                    outstream.flush();
                    outstream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
          }



    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }
    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }
    private static double getDouble(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getDouble(tagName);
    }
    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

    public Bitmap writeOnDrawable(String text1,String text2,String text3,String text4){
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.fbpost1).copy(Bitmap.Config.ARGB_8888, true);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        Canvas canvas = new Canvas(bm);
        paint.setTextSize(150);
        paint.setTypeface(Typeface.create("Arial Bold", Typeface.BOLD));
        if(Integer.parseInt(text1) < 49){
            paint.setColor(Color.GREEN);
            canvas.drawText(text1,800,180,paint);
        } else if(Integer.parseInt(text1) > 48 && Integer.parseInt(text1)  < 60 ){
            paint.setColor(Color.YELLOW);
            canvas.drawText(text1,800,180,paint);
        } else if(Integer.parseInt(text1) > 64){
            paint.setColor(Color.RED);
            canvas.drawText(text1,800,180,paint);
        }
        if(Integer.parseInt(text2) < 49){
            paint.setColor(Color.GREEN);
            canvas.drawText(text2,800,540,paint);
        } else if(Integer.parseInt(text2) > 48 && Integer.parseInt(text2)  < 60 ){
            paint.setColor(Color.YELLOW);
            canvas.drawText(text2,800,540,paint);
        } else if(Integer.parseInt(text2) > 64){
            paint.setColor(Color.RED);
            canvas.drawText(text2,800,540,paint);
        }
        if(Integer.parseInt(text3) < 49){
            paint.setColor(Color.GREEN);
            canvas.drawText(text3,800,900,paint);
        } else if(Integer.parseInt(text3) > 48 && Integer.parseInt(text3)  < 60 ){
            paint.setColor(Color.YELLOW);
            canvas.drawText(text3,800,900,paint);
        } else if(Integer.parseInt(text1) > 64){
            paint.setColor(Color.RED);
            canvas.drawText(text3,800,900,paint);
        }
        if(Integer.parseInt(text4) < 49){
            paint.setColor(Color.GREEN);
            canvas.drawText(text4,800,1300,paint);
        } else if(Integer.parseInt(text4) > 48 && Integer.parseInt(text4)  < 60 ){
            paint.setColor(Color.YELLOW);
            canvas.drawText(text4,800,1300,paint);
        } else if(Integer.parseInt(text4) > 64){
            paint.setColor(Color.RED);
            canvas.drawText(text4,800,1300,paint);
        }



        return bm;
    }

}
