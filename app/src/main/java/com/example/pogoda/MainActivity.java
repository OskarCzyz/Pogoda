package com.example.pogoda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String currentUnit = "celcius";
    private ImageView imageViewTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );


        imageViewTransition = findViewById(R.id.imageViewTransition);
        TextView tvBrenna = findViewById(R.id.tvBrenna);
        TextView tvCieszyn = findViewById(R.id.tvCieszyn);
        TextView tvZebrzydowice = findViewById(R.id.tvZebrzydowice);
        TextView tvStrumien = findViewById(R.id.tvStrumien);
        TextView tvChybie = findViewById(R.id.tvChybie);
        TextView tvHazlach = findViewById(R.id.tvHazlach);
        TextView tvDebowiec = findViewById(R.id.tvDebowiec);
        TextView tvSkoczow = findViewById(R.id.tvSkoczow);
        TextView tvGoleszow = findViewById(R.id.tvGoleszow);
        TextView tvUstron = findViewById(R.id.tvUstron);
        TextView tvWisla = findViewById(R.id.tvWisla);
        TextView tvIstebna = findViewById(R.id.tvIstebna);

        tvBrenna.setOnClickListener(MainActivity.this);
        tvCieszyn.setOnClickListener(MainActivity.this);
        tvStrumien.setOnClickListener(MainActivity.this);
        tvChybie.setOnClickListener(MainActivity.this);
        tvHazlach.setOnClickListener(MainActivity.this);
        tvDebowiec.setOnClickListener(MainActivity.this);
        tvSkoczow.setOnClickListener(MainActivity.this);
        tvGoleszow.setOnClickListener(MainActivity.this);
        tvUstron.setOnClickListener(MainActivity.this);
        tvWisla.setOnClickListener(MainActivity.this);
        tvIstebna.setOnClickListener(MainActivity.this);
        tvZebrzydowice.setOnClickListener(MainActivity.this);

    }

    @Override
    public void onClick(View v) {
        TextView text = (TextView) v;
        String name = (String) text.getText();
        int move = R.anim.move_hazlach;
        int scale = R.anim.scaleanimation;
        double latitude = 0, longitude =0;
        // Określanie który przycisk został wybrany
        // ustawianie innego rodzaju powiększania przy przejsciu kiedy powiat jest duzy
        // ustawianie personalizowanego przejscia
        // tlo przejscia
        // wysokosc i szerokosc geograficzna powiatu
        switch (name) {
            case "Brenna":
                scale = R.anim.scaleforbiganimation;
                move = R.anim.move_brenna;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_brenna);
                latitude = 49.7258;
                longitude = 18.9025;
                break;
            case "Chybie":
                move = R.anim.move_chybie;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_chybie);
                latitude = 49.9025;
                longitude = 18.8276;
                break;
            case "Cieszyn":
                move = R.anim.move_cieszyn;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_cieszyn);
                latitude = 49.7513;
                longitude = 18.6321;
                break;
            case "Goleszów":
                move = R.anim.move_goleszow;
                scale = R.anim.scaleforbiganimation;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_goleszow);
                latitude = 49.7358;
                longitude = 18.7368;
                break;
            case "Hażlach":
                imageViewTransition.setBackgroundResource(R.drawable.powiat_hazlach);
                latitude = 49.8071;
                longitude = 18.6518;
                break;
            case "Istebna":
                scale = R.anim.scaleforbiganimation;
                move = R.anim.move_istebna;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_istebna);
                latitude = 49.5632;
                longitude = 18.9057;
                break;
            case "Skoczów":
                scale = R.anim.scaleforbiganimation;
                move = R.anim.move_skoczow;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_skoczow);
                latitude = 49.8009;
                longitude = 18.7877;
                break;
            case "Strumień":
                scale = R.anim.scaleforbiganimation;
                move = R.anim.move_strumien;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_strumien);
                latitude = 49.9210;
                longitude = 18.7664;
                break;
            case "Ustroń":
                scale = R.anim.scaleforbiganimation;
                move = R.anim.move_ustron;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_ustron);
                latitude = 49.7215;
                longitude = 18.8020;
                break;
            case "Wisła":
                scale = R.anim.scaleforbiganimation;
                move = R.anim.move_wisla;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_wisla);
                latitude = 49.6563;
                longitude = 18.8591;
                break;
            case "Zebrzydowice":
                move = R.anim.move_zebrzydowice;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_zebrzydowice);
                latitude = 49.8779;
                longitude = 18.6113;
                break;
            case "Dębowiec":
                move = R.anim.move_debowiec;
                imageViewTransition.setBackgroundResource(R.drawable.powiat_debowiec);
                latitude = 49.8141;
                longitude = 18.7206;
                break;
        }
        //Creating new intent with data to GminaActivity
        Intent intent = new Intent(MainActivity.this, GminaActivity.class);
        intent.putExtra("Name", name);
        intent.putExtra("Latitude", latitude);
        intent.putExtra("Longitude", longitude);
        intent.putExtra("Unit", currentUnit);

        imageViewTransition.setVisibility(View.VISIBLE);
        Animation moveAnimation = AnimationUtils.loadAnimation(MainActivity.this, move);
        Animation scaleUpanimation = AnimationUtils.loadAnimation(MainActivity.this, scale);

        AnimationSet animationSet = new AnimationSet(true);

        animationSet.addAnimation(moveAnimation);
        animationSet.addAnimation(scaleUpanimation);

        imageViewTransition.startAnimation(animationSet);


        new Handler().postDelayed(() -> {
            imageViewTransition.setVisibility(View.GONE);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, 2000); // Delay of 2000 milliseconds

    }
}