package com.excoffier.guillaume.mydrawerappforrpg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import static android.R.attr.startX;
import static android.R.attr.startY;
import static android.R.attr.x;

/**
 * Created by guigu on 19/04/2018.
 */

public class TestGameView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private GameThread gameThread;
    private float manXPos = 0, manYPos = 0;
    private int frameWidth = 230, frameHeight = 274;
    private int frameCount = 10;
    private int currentFrame = 0;
    private long fps;
    private long timeThisFrame;
    private long lastFrameChangeTime = 0;
    private int frameLengthInMillisecond = 50;
    private float runSpeedPerSecond = 500;


    private Rect frameToDraw = new Rect(0, 0, frameWidth, frameHeight);
    private RectF whereToDraw = new RectF(manXPos, manYPos, manXPos + frameWidth, frameHeight);


    public TestGameView(Context context) {
        super(context);
        gameThread = new GameThread(this);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.run_sprite);
        bmp = Bitmap.createScaledBitmap(bmp, frameWidth * frameCount, frameHeight, false);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {


            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                gameThread.setRunning(true);
                gameThread.start();


            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {


            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                gameThread.setRunning(false);


            }
        });
    }

    public void manageCurrentFrame() {
        long time = System.currentTimeMillis();
        if (time > lastFrameChangeTime + frameLengthInMillisecond) {
            lastFrameChangeTime = time;
            currentFrame++;

            if (currentFrame >= frameCount) {
                currentFrame = 0;
            }
        }

        frameToDraw.left = currentFrame * frameWidth;
        frameToDraw.right = frameToDraw.left + frameWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (gameThread.isRunning()) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            whereToDraw.set((int) manXPos, (int) manYPos, (int) manXPos + frameWidth, (int) manYPos + frameHeight);
            manageCurrentFrame();
            Paint paint = new Paint();
            canvas.drawBitmap(bmp, frameToDraw, whereToDraw, paint);
        }


    }


    public void update() {
        manXPos = manXPos + runSpeedPerSecond / fps;

        if (manXPos > getWidth()) {
            manXPos = 10;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                gameThread.setRunning(!gameThread.isRunning());
                break;
        }
        return true;

    }
}

