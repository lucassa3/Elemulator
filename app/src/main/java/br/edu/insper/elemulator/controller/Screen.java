package br.edu.insper.elemulator.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;

import br.edu.insper.elemulator.model.RAM;

public class Screen extends View {
    private ShapeDrawable mDrawable;
    RAM ram;
    Paint paint;
    RectF rect;
    int counter;


    public Screen(Context context, RAM ram) {
        super(context);
        this.ram = ram;
        this.paint = new Paint();
        rect = new RectF();
        rect.set(10,10,10,10);
        counter = 10;
    }


    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLUE);
        canvas.drawRect(rect, paint);
        canvas.drawCircle(1, 1, counter, paint);
        System.out.println("rodeiiieieieieieieieieieieieeiieieieeei");
        counter+=10;
        System.out.println(counter);

        int count = 0;
        /*for (int j = 0; j<256; j++) {
            for (int i = 0; i<512; i+=16) {
                for (int k = 0; k<16; k++) {
                    if (ram.getSelectedValueInt(16384+count)[k]) paint.setColor(Color.BLACK);
                    else paint.setColor(Color.WHITE);
                    canvas.drawCircle(i+k, j, 1, paint);

                }
                count++;
            }
        }*/
    }
}