package edu.wit.monplaisirj;

import java.util.ArrayList;
import java.util.List;

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
       private Bitmap back;
       private SurfaceHolder holder;
       private GameLoopThread gameLoopThread;
       private Sprite sprite;
       private Sprite sprite_two;
       private int x = 0; 
       private int xSpeed = 1;
       private List<Sprite> sprites = new ArrayList<Sprite>();
      
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
                    	   createSprites();
                           gameLoopThread.setRunning(true);
                           gameLoopThread.start();
                    }
 
                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format,
                                  int width, int height) {
                    }
             });
             
             map = BitmapFactory.decodeResource(getResources(), R.drawable.soil);
            // sprite = new Sprite(this, bmp);
   
       }
       
       private Sprite createSprite(int resource)
       {
       	Bitmap bmp =  BitmapFactory.decodeResource(getResources(), resource);
   		return new Sprite(this, bmp);
       	
       }
       
       private void createSprites()
       {
    	   sprites.add(createSprite(R.drawable.ted_extended));
    	   sprites.add(createSprite(R.drawable.ted_background_clay));
       }

 
       @Override
       protected void onDraw(Canvas canvas) {
           
    	   //canvas.drawColor(Color.BLACK);
    	   canvas.drawBitmap(map, 0, 0, null);
    	   for(Sprite sprite: sprites)
    	   {
    		   sprite.onDraw(canvas);
    	   }
           //sprite.onDraw(canvas);
         
       }
}