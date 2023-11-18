package com.example.pogoda;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvBrenna, tvCieszyn, tvZebrzydowice, tvStrumien, tvChybie, tvHazlach, tvDebowiec, tvSkoczow,tvGoleszow,tvUstron,tvWisla,tvIstebna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        double latitude = 0, longitude =0;
        if (name.equals("Brenna")) {
            latitude = 49.7258;
            longitude = 18.9025;
        } else if (name.equals("Chybie")) {
            latitude = 49.9025;
            longitude = 18.8276;
        } else if (name.equals("Cieszyn")) {
            latitude = 49.7513;
            longitude = 18.6321;
        } else if (name.equals("Goleszów")) {
            latitude = 49.7358;
            longitude = 18.7368;
        } else if (name.equals("Haźlach")) {
            latitude = 49.8071;
            longitude = 18.6518;
        } else if (name.equals("Istebna")) {
            latitude = 49.5632;
            longitude = 18.9057;
        } else if (name.equals("Skoczów")) {
            latitude = 49.8009;
            longitude = 18.7877;
        } else if (name.equals("Strumień")) {
            latitude = 49.9210;
            longitude = 18.7664;
        } else if (name.equals("Ustroń")) {
            latitude = 49.7215;
            longitude = 18.8020;
        } else if (name.equals("Wisła")) {
            latitude = 49.6563;
            longitude = 18.8591;
        } else if (name.equals("Zebrzydowice")) {
            latitude = 49.8779;
            longitude = 18.6113;
        } else if (name.equals("Dębowiec")) {
            latitude = 49.8141;
            longitude = 18.7206;
        }

        Intent intent = new Intent(MainActivity.this, GminaActivity.class);
        intent.putExtra("Name", name);
        intent.putExtra("Latitude", latitude);
        intent.putExtra("Longitude", longitude);
        startActivity(intent);
    }
}