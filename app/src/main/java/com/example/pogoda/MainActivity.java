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

    }

    @Override
    public void onClick(View v) {
        TextView text = (TextView) v;
        String nazwa = (String) text.getText();

        Intent intent = new Intent(MainActivity.this, GminaActivity.class);
        intent.putExtra("Powiat", nazwa);
        startActivity(intent);
    }
}