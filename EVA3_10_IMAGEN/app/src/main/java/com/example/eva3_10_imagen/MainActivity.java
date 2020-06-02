package com.example.eva3_10_imagen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imgVwImagen;
    Bitmap imagen;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            imgVwImagen.setImageBitmap(imagen);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwImagen = findViewById(R.id.imgVwImagen);

        Thread tHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                imagen = cargarImagen("https://ca-times.brightspotcdn.com/dims4/default/2275543/2147483647/strip/true/crop/1493x1209+0+0/resize/840x680!/quality/90/?url=https%3A%2F%2Fcalifornia-times-brightspot.s3.amazonaws.com%2Fd7%2Fdf%2F1abbd2e3445ba70c8ff888657f64%2Fspider-man.JPG");
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
        tHilo.start();
    }

    public Bitmap cargarImagen(String url){
        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return  bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}