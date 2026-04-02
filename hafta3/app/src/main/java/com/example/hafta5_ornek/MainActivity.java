package com.example.hafta5_ornek;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar1, seekBar2;
    TextView textViewDeger1, textViewDeger2;
    Button baslaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);
        textViewDeger1 = findViewById(R.id.textViewDeger1);
        textViewDeger2 = findViewById(R.id.textViewDeger2);
        baslaButton = findViewById(R.id.button);

        seekBar1.setMax(100);
        seekBar2.setMax(100);

        // 1. SeekBar dinleyicisi
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewDeger1.setText("Seçilen: " + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 2. SeekBar dinleyicisi
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewDeger2.setText("Seçilen: " + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        baslaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deger1 = seekBar1.getProgress();
                int deger2 = seekBar2.getProgress();

                int min = Math.min(deger1, deger2);
                int max = Math.max(deger1, deger2);

                int rastgeleSayi;
                if (min == max) {
                    rastgeleSayi = min;
                } else {
                    Random random = new Random();
                    rastgeleSayi = random.nextInt((max - min) + 1) + min;
                }

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("RASTGELE_SAYI", rastgeleSayi);
                startActivity(intent);
            }
        });
    }
}