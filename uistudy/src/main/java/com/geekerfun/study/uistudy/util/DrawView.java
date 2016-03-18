package com.geekerfun.study.uistudy.util;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ebupt on 16/2/14.
 */
public class DrawView extends View {
    public  DrawView(Context context){
        super(context);
    }
    public  DrawView(Context context,AttributeSet set){
        super(context,set);
    }
    public float currentX   = 40;
    public float currentY   = 50;
    Paint p = new Paint();

    @Override
    public void  onDraw(Canvas canvas){
        super.onDraw(canvas);
        p.setColor(Color.BLUE);
        canvas.drawCircle(currentX,currentY,15,p);
    }

    @Override
    public boolean  onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        currentX = event.getX();
        currentY = event.getY();
        invalidate();
        return true;
    }
}
