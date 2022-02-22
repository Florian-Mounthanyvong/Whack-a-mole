package com.example.tp7_21966918;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;


import java.util.Random;

public class Taupiniere extends ConstraintLayout {
    private ImageView taupe;
    private int taupeWidth, taupeHeight, taupeX , taupeY;
    private int compteur,score,scoremax;
    private static long apparition,touche;
    public ImageView getTaupe() {
        return taupe;
    }

    public Taupiniere(Context context){
        super(context) ;
        setWillNotDraw(false) ;
        taupe=new ImageView(context);
        taupe.setImageResource(R.drawable.taupe);
        this.addView(taupe);
        this.setBackgroundResource(R.drawable.taupiniere);
        compteur=0;
        score=0;
        DisplayMetrics dm=getResources().getDisplayMetrics();
        int l =(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,dm);
        taupe.setLayoutParams(new LayoutParams(l,l));
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
            super.onDraw(canvas);
            Random r = new Random();
            taupe.setX(r.nextInt(this.getWidth() - taupe.getWidth()));
            taupe.setY(r.nextInt(this.getHeight() - taupe.getHeight()));
            apparition=SystemClock.uptimeMillis();
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float x=event.getX();
                float y =event.getY();
                if(x>=taupe.getX()&&x<=taupe.getX()+taupe.getWidth()&&y>=taupe.getY()&&y<=taupe.getY()+taupe.getHeight()){
                    compteur++;
                    if(event.getEventTime()-apparition<1000){
                        score=score+1000-(int)(event.getEventTime()-apparition);
                    }
                    if(compteur==10){
                        compteur=0;
                        if(score>scoremax){
                            scoremax=score;
                            getMainActivity().getMaxView().setText(max());
                        }
                        score=0;
                    }
                    getMainActivity().getScoreView().setText(toString());
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    private MainActivity getMainActivity( ) {
        Context context=getContext() ;
        while (context instanceof ContextWrapper) {
            if(context instanceof MainActivity) {
                return (MainActivity) context;
            }
            context=((ContextWrapper)context).getBaseContext( ) ;
        }
        return null ;
    }
    @Override
    public String toString(){
        return "Score : "+ score ;
    }
    public String max(){
        return "Mon meilleur score : " + scoremax;
    }
}
