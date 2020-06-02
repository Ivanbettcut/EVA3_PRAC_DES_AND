package com.example.eva3_9_banner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgVwBanner;
    int[] imagenes = {R.drawable.f1, R.drawable.f2, R.drawable.f3};
    int indice = 0;

    Handler handler = new Handler();


    Runnable backG = new Runnable() {//Hilo con el trabajo pesado
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(foreG);//Notificamos que cambie la imagen
            }
        }
    };

    Runnable foreG = new Runnable() {
        @Override
        public void run() {
            imgVwBanner.setImageResource(imagenes[indice]);
            if(indice == 2){
                indice = 0;
            }else{
                indice++;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwBanner = findViewById(R.id.imgVwBanner);
        Thread hilo = new Thread(backG);
        hilo.start();
    }
}