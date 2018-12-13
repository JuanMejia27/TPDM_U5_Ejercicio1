package mx.edu.ittepic.tpdm_u5_practica1_juanmejia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Lienzo extends View {
    int x, y, nX, nY;
    boolean inicio = true, luz = true;

    public Lienzo(Context context) {
        super(context);
        x = nX = 0;
        y = nY = 0;
    }

    public void onDraw (Canvas c) {
        Paint p = new Paint();

        if (inicio) {
            x = c.getWidth()/2;
            y = c.getHeight()/2;
            inicio = false;
        }

        if (x>=100 && x<=c.getWidth()-100 || x-nX>100 && x-nX<c.getWidth()-100){
            x -= nX;
        }
        if (y>=100 && y<=c.getHeight()-100 || y+nY>100 && y+nY<c.getHeight()-100) {
            y += nY;
        }
        if (luz) {
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(10f);
            p.setColor(Color.argb(255, 18, 18, 20));
            c.drawCircle(x, y, 100, p);
        } else {
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(-1f);
            c.drawColor(Color.argb(255, 18, 18, 20));
            c.drawCircle(x, y, 100, p);
        }
    }
}//class
