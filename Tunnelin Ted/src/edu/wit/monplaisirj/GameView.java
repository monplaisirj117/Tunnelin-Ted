package edu.wit.monplaisirj;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
 
@SuppressLint("WrongCall")
public class GameView extends SurfaceView {
       private Bitmap bmp;
       private Bitmap map;
       private SurfaceHolder holder;
       private GameLoopThread gameLoopThread;
       private Sprite sprite;
       private int x = 0; 
       private int xSpeed = 1;
      
       public GameView(Context context) {
             super(context);
             gameLoopThread = new GameLoopThread(this);
             holder = getHolder();
             holder.addCallback(new SurfaceHolder.Callback() {
 
                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                           boolean retry = true;
                           gameLoopThread.setRunning(false);
                           while (retry) {
                                  try {
                                        gameLoopThread.join();
                                        retry = false;
                                  } catch (InterruptedException e) {
                                  }
                           }
                    }
 
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                           gameLoopThread.setRunning(true);
                           gameLoopThread.start();
                    }
 
                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format,
                                  int width, int height) {
                    }
             });
             bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ted_sprite);
             map = BitmapFactory.decodeResource(getResources(), R.drawable.soil);
             sprite = new Sprite(this, bmp);
       }
 
       @Override
       protected void onDraw(Canvas canvas) {
           
    	   //canvas.drawColor(Color.BLACK);
    	   canvas.drawBitmap(map, 0, 0, null);
           sprite.onDraw(canvas);
       }
}