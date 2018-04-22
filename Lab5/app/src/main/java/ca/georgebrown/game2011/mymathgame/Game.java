package ca.georgebrown.game2011.mymathgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

/**
 * Created by Tech on 2018-03-19.
 */

public class Game extends View {
    private int m_screenW;
    private int m_screenH;

    private int playerX;
    private int playerY;
    private int playerW;
    private int playerH;

    private int m_scoreH;
    private int m_score;
    private Bitmap playerBitmap, background;
    private Paint m_paint;
    private long m_startTime, m_endTime;
    private double m_diff;

    private Bitmap[] currentClouds = new Bitmap[10];
    private int cloudGen = 225;
    private int cloudIndex = 1;
    private boolean[] cloudsActive = new boolean[10];
    private int[] cloudsX = new int[10];
    private int[] cloudsY = new int[10];
    private int[] cloudImageArray = {R.drawable.cloud1,R.drawable.cloud2,R.drawable.cloud3};



    private volatile boolean playing;
    private boolean isMoving;
    private float manXPos = 10, manYPos = 10;
    private int frameWidth = 230, frameHeight = 274;
    private int frameCount = 8;
    private int currentFrame = 0;
    private long fps;
    private long timeThisFrame;
    private long lastFrameChangeTime = 0;
    private int frameLengthInMillisecond = 50;

    private Rect frameToDraw = new Rect(0, 0, frameWidth, frameHeight);

    private RectF whereToDraw = new RectF(playerX, playerY, playerX + frameWidth, frameHeight);

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        m_screenW = w;
        m_screenH = h;

        background = Bitmap.createScaledBitmap(background,w,h,true);
        playerX = (int) (50);
        playerY = (int) (m_screenH*0.85) - (playerH);
    }

    public Game(Context context) {
        super(context);
        m_startTime = System.nanoTime();
        playerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.running_man);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        playerBitmap = Bitmap.createScaledBitmap(playerBitmap, frameWidth * frameCount, frameHeight, false);

        playerW = playerBitmap.getWidth();
        playerH = playerBitmap.getHeight();
        int previousRan = 0;
        int ranNum = 0;
        for(int i = 0; i < currentClouds.length ; i++)
        {
            Random r = new Random();
            while (ranNum == previousRan) {
                System.out.println("wtf");
                ranNum = r.nextInt(cloudImageArray.length);
            }
            previousRan = ranNum;
            currentClouds[i] = BitmapFactory.decodeResource(getResources(),cloudImageArray[ranNum]);
            cloudsX[i] = 1800;
            cloudsY[i] = r.nextInt(200-10)+10;
            cloudsActive[i] = false;

        }
        cloudsActive[0] = true;
        m_scoreH = 100;

        m_paint = new Paint();
        m_paint.setColor(Color.RED);
        m_paint.setTextSize(60);

    }

    @Override
    protected void onDraw(Canvas canvas){
        isMoving = !isMoving;
        super.onDraw(canvas);

        // Drawing the Background Image
        canvas.drawBitmap(background,0,0,null);


        // Set a random X and Y coordinate for the bug
        m_endTime = System.nanoTime();
        m_diff = (m_endTime - m_startTime)/1e6;

        // Drawing the Player
            whereToDraw.set((int) playerX, (int) playerY, (int) playerX + frameWidth, (int) playerY + frameHeight);
            manageCurrentFrame();
            canvas.drawBitmap(playerBitmap, frameToDraw, whereToDraw, null);


        if (m_diff > 10)
        {
            cloudGen -- ;
            System.out.println(cloudGen);
            if (cloudGen == 0)
            {
                Random r = new Random();
                cloudGen = r.nextInt(250-150)+150;
                cloudsActive[cloudIndex] = true;
                if (cloudIndex == 9)
                    cloudIndex = 0;
                else
                    cloudIndex++;
            }
        }
        while (playing) {
            long startFrameTime = System.currentTimeMillis();
            update();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;

            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
        }

        for (int i = 0; i < currentClouds.length;i++)
            if (cloudsActive[i] == true) {
                cloudsX[i]-=3;
                if (cloudsX[i] < -300) {
                    cloudsX[i] = 1800;
                    cloudsActive[i] = false;
                }
            }

        for(int i = 0 ; i < currentClouds.length ; i++)
        {
            canvas.drawBitmap(currentClouds[i],cloudsX[i],cloudsY[i],null);
        }
        m_startTime = m_endTime;


        // Draw Score
        canvas.drawText("Score: " + m_score, 50, 100, m_paint);



        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        System.out.println(touchX + "(" + playerX + " " + (playerX + playerW)+")" +
                           touchY + "(" + playerY + " " + (playerY + playerH)+")" );

        if ((touchX >= playerX && touchX <= playerX + playerW) && (touchY >= playerY - playerH /2 && touchY <= playerY + playerH))
        {
            m_score++;

        }


        return super.onTouchEvent(event);
    }

        public void update() {
            if (isMoving) {

            }

        }

        public void manageCurrentFrame() {
            long time = System.currentTimeMillis();

            if (isMoving) {
                if (time > lastFrameChangeTime + frameLengthInMillisecond) {
                    lastFrameChangeTime = time;
                    currentFrame++;

                    if (currentFrame >= frameCount) {
                        currentFrame = 0;
                    }
                }
            }

            frameToDraw.left = currentFrame * frameWidth;
            frameToDraw.right = frameToDraw.left + frameWidth;
        }

    }



