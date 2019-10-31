package com.excoffier.guillaume.mydrawerappforrpg;

import android.graphics.Canvas;

/**
 * Created by guigu on 19/04/2018.
 */

public class GameThread extends Thread {
    private TestGameView view;
    private boolean running;

    public TestGameView getView() {
        return view;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public GameThread(TestGameView view){
        this.view=view;
    }

    @Override
    public void run(){
        while (running){
            Canvas c =null;
            try{
                c=view.getHolder().lockCanvas();
                synchronized (view.getHolder()){
                    view.onDraw(c);
                }
            }finally {
                if(c!=null){
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }



}
