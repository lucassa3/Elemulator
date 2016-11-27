package br.edu.insper.elemulator.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;

import br.edu.insper.elemulator.model.RAM;

public class Screen extends View {
    private ShapeDrawable mDrawable;
    RAM ram;


    public Screen(Context context, RAM ram) {
        super(context);

    }


    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(20, 20, 15, paint);

        int count = 0;
        for (int j = 0; j<256; j++) {
            for (int i = 0; i<512; i+=16) {
                for (int k = 0; k<16; k++) {
                    if (ram.getSelectedValueInt(16384+count)[k]) paint.setColor(Color.BLACK);
                    else paint.setColor(Color.WHITE);
                    canvas.drawRect(i+j,k,1,1, paint);
                }
                count++;
            }
        }
    }
}