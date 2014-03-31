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
       private Bitmap tedAnim;
       private Bitmap tedBgAnim;
       private Bitmap tunnelBitmap;
       private Bitmap map;
       private Bitmap back;
       private SurfaceHolder holder;
       private GameLoopThread gameLoopThread;
<<<<<<< HEAD
       private Sprite sprite;
       private Sprite sprite_two;
=======
       private TedSprite tedSprite;
       private TedSpriteBG bgSprite;
>>>>>>> 9e6876c44040a59cc34b095844b2cc2463a029d2
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
<<<<<<< HEAD
             
             map = BitmapFactory.decodeResource(getResources(), R.drawable.soil);
            // sprite = new Sprite(this, bmp);
   
       }
       
       private Sprite createSprite(int resource)
       {
       	Bitmap bmp =  BitmapFactory.decodeResource(getResources(), resource);
   		return new Sprite(this, bmp);
       	
=======
             tedAnim = BitmapFactory.decodeResource(getResources(), R.drawable.ted_extended);
             tedBgAnim = BitmapFactory.decodeResource(getResources(), R.drawable.ted_background_soil);
             tunnelBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ted_tunnel);
             map = BitmapFactory.decodeResource(getResources(), R.drawable.soil);
             tedSprite = new TedSprite(this, tedAnim);
             bgSprite = new TedSpriteBG(this, tedBgAnim);            
>>>>>>> 9e6876c44040a59cc34b095844b2cc2463a029d2
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
<<<<<<< HEAD
    	   for(Sprite sprite: sprites)
    	   {
    		   sprite.onDraw(canvas);
    	   }
           //sprite.onDraw(canvas);
         
=======
    	   canvas.drawBitmap(tunnelBitmap, tedSprite.getX()+3, tedSprite.getY()-60, null);
    	   bgSprite.onDraw(canvas);
           tedSprite.onDraw(canvas);
>>>>>>> 9e6876c44040a59cc34b095844b2cc2463a029d2
       }
}