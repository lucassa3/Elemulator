package br.edu.insper.elemulator.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;

import java.util.ArrayList;

import br.edu.insper.elemulator.model.RAM;

public class Screen extends View {
    Paint paint;
    int y = 0;
    ArrayList<Integer> x;


    public Screen(Context context) {
        super(context);
        this.paint = new Paint();
        x = new ArrayList<>();
        paint.setStyle(Paint.Style.FILL);

    }


    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        for (int i : this.x) {
            paint.setColor(Color.BLACK);
            canvas.drawCircle(i, y, 1, paint);
        }





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

    protected void markPixel(ArrayList x, int y) {
        this.x = x;
        this.y = y;
    }

}