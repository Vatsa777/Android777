package com.example.wallpaperchange;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button changewallpaper;
    Timer mytimer;
    Drawable drawable;
    WallpaperManager wpm;
    int prev=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changewallpaper = findViewById(R.id.btn);
        wpm = WallpaperManager.getInstance(this);
        changewallpaper.setOnClickListener(view -> setWallpaper());
        mytimer = new Timer();
    }
    private void setWallpaper(){
        mytimer.schedule(new TimerTask() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void run() {
                if(prev==1){
                    drawable = getResources().getDrawable(R.drawable.one);
                    prev = 2;
                }
                else if(prev==2){
                    drawable = getResources().getDrawable(R.drawable.two);
                    prev = 3;
                }
                else if(prev==3){
                    drawable = getResources().getDrawable(R.drawable.three);
                    prev = 1;
                }
                Bitmap wallpaper = ((BitmapDrawable)drawable).getBitmap();
                try{
                    wpm.setBitmap(wallpaper);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        },0,3000);
    }
}