package com.example.pogoda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String currentUnit = "celcius";
    private TextView tvBrenna, tvCieszyn, tvZebrzydowice, tvStrumien, tvChybie, tvHazlach, tvDebowiec, tvSkoczow,tvGoleszow,tvUstron,tvWisla,tvIstebna;
    private ImageView imageViewTransition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageViewTransition = findViewById(R.id.imageViewTransition);
        tvBrenna = findViewById(R.id.tvBrenna);
        tvCieszyn = findViewById(R.id.tvCieszyn);
        tvZebrzydowice = findViewById(R.id.tvZebrzydowice);
        tvStrumien = findViewById(R.id.tvStrumien);
        tvChybie = findViewById(R.id.tvChybie);
        tvHazlach = findViewById(R.id.tvHazlach);
        tvDebowiec = findViewById(R.id.tvDebowiec);
        tvSkoczow = findViewById(R.id.tvSkoczow);
        tvGoleszow = findViewById(R.id.tvGoleszow);
        tvUstron = findViewById(R.id.tvUstron);
        tvWisla = findViewById(R.id.tvWisla);
        tvIstebna = findViewById(R.id.tvIstebna);

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
        //setting buttons
        double latitude = 0, longitude =0;
        if (name.equals("Brenna")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_brenna);
            latitude = 49.7258;
            longitude = 18.9025;
        } else if (name.equals("Chybie")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_chybie);
            latitude = 49.9025;
            longitude = 18.8276;
        } else if (name.equals("Cieszyn")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_cieszyn);
            latitude = 49.7513;
            longitude = 18.6321;
        } else if (name.equals("Goleszów")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_goleszow);
            latitude = 49.7358;
            longitude = 18.7368;
        } else if (name.equals("Haźlach")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_hazlach);
            latitude = 49.8071;
            longitude = 18.6518;
        } else if (name.equals("Istebna")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_istebna);
            latitude = 49.5632;
            longitude = 18.9057;
        } else if (name.equals("Skoczów")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_skoczow);
            latitude = 49.8009;
            longitude = 18.7877;
        } else if (name.equals("Strumień")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_strumien);
            latitude = 49.9210;
            longitude = 18.7664;
        } else if (name.equals("Ustroń")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_ustron);
            latitude = 49.7215;
            longitude = 18.8020;
        } else if (name.equals("Wisła")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_wisla);
            latitude = 49.6563;
            longitude = 18.8591;
        } else if (name.equals("Zebrzydowice")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_zebrzydowice);
            latitude = 49.8779;
            longitude = 18.6113;
        } else if (name.equals("Dębowiec")) {
            imageViewTransition.setBackgroundResource(R.drawable.powiat_debowiec);
            latitude = 49.8141;
            longitude = 18.7206;
        }
        //Creating new intent with data to GminaActivity
        Intent intent = new Intent(MainActivity.this, GminaActivity.class);
        intent.putExtra("Name", name);
        intent.putExtra("Latitude", latitude);
        intent.putExtra("Longitude", longitude);
        intent.putExtra("Unit", currentUnit);

        imageViewTransition.setVisibility(View.VISIBLE);
        Animation scaleUpanimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scaleanimation);
        imageViewTransition.startAnimation(scaleUpanimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewTransition.setVisibility(View.GONE);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 1000); // Delay of 500 milliseconds

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        switch (item.getItemId()) {
            case R.id.menu_item_farenheit:
                currentUnit = "fahrenheit";
                return true;
            case R.id.menu_item_celcius:
                currentUnit = "celcius";
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}