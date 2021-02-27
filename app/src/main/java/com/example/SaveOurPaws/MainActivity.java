package com.example.SaveOurPaws;

import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * This class is for the main home page of the application
 */
public class MainActivity extends AppCompatActivity {


    private static DecimalFormat f = new DecimalFormat("0.0000000");
    private String textValue = "";
    private String content = "";
    private TextView tv1,tv2,tv3,tv4;
    private Button svButton,emL,shareBtn;
    private Intent it;
    private Date currentTime = Calendar.getInstance().getTime();


    /**
     * Creating everything on the create method
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emL = findViewById(R.id.emLo);
        svButton = findViewById(R.id.postBtn);
        shareBtn = findViewById(R.id.sheBtn);
        tv1 = findViewById(R.id.dtarRange);
        tv2 = findViewById(R.id.dwcRange);
        tv3 = findViewById(R.id.ddRange);
        tv4 = findViewById(R.id.dgRange);
        it = getIntent();
        textValue = it.getStringExtra("Grade");
        tv1.setText(textValue);
       if (textValue.equals("1")) {
            tv1.setText("Less than 49 Degrees");
            tv2.setText("Less than 49 Degrees");
            tv3.setText("Less than 49 Degrees");
            tv4.setText("Less than 49 Degrees");
        } else if (textValue.equals("2")) {
            tv1.setText("Between 49 to 60");
            tv2.setText("Between 49 to 60");
            tv3.setText("Between 49 to 60");
            tv4.setText("Between 49 to 60");
        } else if (textValue.equals("3")) {
            tv1.setText("Between 60 to 65");
            tv2.setText("Between 60 to 65");
            tv3.setText("Between 60 to 65");
            tv4.setText("Between 60 to 65");
        } else if (textValue.equals("4")) {
            tv1.setText("Higher than 65");
            tv2.setText("Higher than 65");
            tv3.setText("Higher than 65");
            tv4.setText("Higher than 65");
        }
        emL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        svButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream outstream;
                Resources res = getResources();
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                dir.mkdirs();
                File file = new File(dir,System.currentTimeMillis() + ".jpg");
                try {
                    Bitmap bmap = writeOnDrawable(tv1.getText().toString(),tv2.getText().toString(),tv3.getText().toString(),tv4.getText().toString());
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

    /**
     * Creating intents and carrying over information to other pages
     * This method loads the Email class and activity
     */
    public void openActivity2() {
        Intent intent = new Intent(this, MainActivityEmail.class);
        intent.putExtra("JSONVALUE", it.getStringExtra("EVERYTHING"));
        startActivity(intent);
    }

    /**
     * This Method creates intent and carry over the information to other pages
     * This method loads the Post Class
     */
    public void openActivity3() {
        Intent intent = new Intent(this, MainActivityPost.class);
        intent.putExtra("JSONVALUE", content);
        startActivity(intent);
    }


    /**
     * This method creates the image and puts certain text, that i planned out early.
     * @param text1
     * @param text2
     * @param text3
     * @param text4
     * @return
     */
    public Bitmap writeOnDrawable(String text1, String text2, String text3, String text4){
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.fbpost1).copy(Bitmap.Config.ARGB_8888, true);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        Canvas canvas = new Canvas(bm);
        paint.setTextSize(75);
        paint.setTypeface(Typeface.create("Arial Bold", Typeface.BOLD));
        if(text1.contains("Less than 49 Degrees")){
            paint.setColor(Color.rgb(0, 230, 77));
            canvas.drawText(text1,350,230,paint);
        } else if(text1.contains("Between 49 to 60")){
            paint.setColor(Color.rgb(255, 153, 51));
            canvas.drawText(text1,350,230,paint);
        } else if(text1.contains("Between 60 to 65")){
            paint.setColor(Color.rgb(255, 92, 51));
            canvas.drawText(text1,350,230,paint);
        } else if(text1.contains("Higher than 65")){
            paint.setColor(Color.rgb(179, 36, 0));
            canvas.drawText(text1,350,230,paint);
        }
        if(text2.contains("Less than 49 Degrees")){
            paint.setColor(Color.rgb(0, 230, 77));
            canvas.drawText(text2,350,640,paint);
        } else if(text2.contains("Between 49 to 60")){
            paint.setColor(Color.rgb(255, 153, 51));
            canvas.drawText(text2,350,640,paint);
        } else if(text2.contains("Between 60 to 65")){
            paint.setColor(Color.rgb(255, 92, 51));
            canvas.drawText(text2,350,640,paint);
        } else if(text2.contains("Higher than 65")){
            paint.setColor(Color.rgb(179, 36, 0));
            canvas.drawText(text2,350,640,paint);
        }
        if(text3.contains("Less than 49 Degrees")){
            paint.setColor(Color.rgb(0, 230, 77));
            canvas.drawText(text3,350,950,paint);
        } else if(text3.contains("Between 49 to 60")){
            paint.setColor(Color.rgb(255, 153, 51));
            canvas.drawText(text3,350,950,paint);
        } else if(text3.contains("Between 60 to 65")){
            paint.setColor(Color.rgb(0, 230, 77));
            canvas.drawText(text3,350,950,paint);
        } else if(text3.contains("Higher than 65")){
            paint.setColor(Color.rgb(179, 36, 0));
            canvas.drawText(text3,350,950,paint);
        }
        if(text4.contains("Less than 49 Degrees")){
            paint.setColor(Color.rgb(0, 230, 77));
            canvas.drawText(text4,350,1350,paint);
        } else if(text4.contains("Between 49 to 60")){
            paint.setColor(Color.rgb(255, 153, 51));
            canvas.drawText(text4,350,1350,paint);
        } else if(text4.contains("Between 60 to 65")){
            paint.setColor(Color.rgb(255, 92, 51));
            canvas.drawText(text4,350,1350,paint);
        } else if(text1.contains("Higher than 65")){
            paint.setColor(Color.rgb(179, 36, 0));
            canvas.drawText(text4,350,1350,paint);
        }



        return bm;
    }
}
