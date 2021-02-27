package com.example.SaveOurPaws;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Class for the Animation progressing
 */
public class ProgressAnimation extends Animation {
    private Context context;
    private ProgressBar pb;
    private String grade = "";
    private String con = "";
    private TextView txtV;
    private float from;
    private float to;
    private boolean check = false;

    /**
     * Class for the Animation
     * @param a Grade for Prediciting
     * @param b Content for Everything
     * @param c Context
     * @param pgb Porgress Bar Class
     * @param t Text View
     * @param f Float value
     * @param to Float Value
     */
    public ProgressAnimation(String a,String b,Context c, ProgressBar pgb, TextView t, float f, float to){
       this.grade = a;
       this.con = b;
        this.context = c;
        this.pb = pgb;
        this.txtV = t;
        this.from = f;
        this.to = to;
    }

    /**
     *  Applying the animation transformation
     *  Also creating the intents and passing the information over to the Main class
     * @param interpolatedTime
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t){
        super.applyTransformation(interpolatedTime,t);
        float value = from + (to-from) * interpolatedTime;
        pb.setProgress((int) value);
        txtV.setText((int) value + " %");

        if(value == to){
            Intent it = new Intent(context,MainActivity.class);
            it.putExtra("Grade",this.grade);
            it.putExtra("EVERYTHING",this.con);
            context.startActivity(it);
        }
    }

}
