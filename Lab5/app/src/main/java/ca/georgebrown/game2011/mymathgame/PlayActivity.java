package ca.georgebrown.game2011.mymathgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

public class PlayActivity extends Activity {

    private Paint paint;
    private Canvas gameCanvas;
    private Bitmap gameBitmap;
    private ImageView gameFrame;
    private Rectangle rectangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        gameFrame = findViewById(R.id.canvasImageView);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        gameBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        gameCanvas = new Canvas(gameBitmap);

        gameCanvas.drawColor(Color.BLACK);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        gameCanvas.drawText("Score:42 Lives:3 Hi:97", 50, 100, paint);

        gameFrame.setImageBitmap(gameBitmap);

        Point newTopLeft = new Point(250,250);
        Point newBottomRight = new Point(500,400);
        rectangle = new Rectangle(newTopLeft, newBottomRight);

        drawRectangle(rectangle, "DRAW");
    }

    private void drawRectangle(Rectangle rectangle, String action) {
        if (action.equals("DRAW")) {
            paint.setColor(Color.WHITE);
        }
        else // ERASE
        {
            paint.setColor(Color.BLACK);
        }

        int rectLeft = rectangle.getTopLeft().getX();
        int rectTop = rectangle.getTopLeft().getY();
        int rectRight = rectangle.getBottomRight().getX();
        int rectBottom = rectangle.getBottomRight().getY();

        Rect rectToDraw = new Rect(rectLeft, rectTop, rectRight, rectBottom);
        gameCanvas.drawRect(rectToDraw, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        drawRectangle(rectangle, "ERASE");
        rectangle.translate(new Point((int)touchX, (int)touchY));
        drawRectangle(rectangle, "DRAW");

        gameFrame.invalidate();

        return super.onTouchEvent(event);
    }
}
