package com.example.hafta5_ornek;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView sayacText;
    ConstraintLayout arkaplan;
    int kalanSure;

    // Geri sayım için gereken nesneler
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        sayacText = findViewById(R.id.sayacTextView);
        arkaplan = findViewById(R.id.ikinciEkranArkaplan);

        // İlk ekrandan gelen rastgele sayıyı alıyoruz (gelmezse varsayılan 10 olsun)
        kalanSure = getIntent().getIntExtra("RASTGELE_SAYI", 10);
        sayacText.setText(String.valueOf(kalanSure));

        handler = new Handler();

        // Runnable: İçindeki kodları belirlediğimiz sürede bir tekrar eden yapı
        runnable = new Runnable() {
            @Override
            public void run() {
                if (kalanSure > 0) {
                    kalanSure--; // Süreyi 1 azalt
                    sayacText.setText(String.valueOf(kalanSure)); // Ekrana yazdır

                    // Arka planı rastgele bir renk yap
                    Random rnd = new Random();
                    int rastgeleRenk = Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    arkaplan.setBackgroundColor(rastgeleRenk);

                    // Bu işlemleri 1 saniye (1000 milisaniye) sonra tekrarla
                    handler.postDelayed(this, 1000);
                } else {
                    // Süre 0 olduysa Toast mesajı göster ve döngüyü durdur
                    Toast.makeText(SecondActivity.this, "Uygulama bitmiştir", Toast.LENGTH_LONG).show();
                    handler.removeCallbacks(runnable);
                }
            }
        };

        // Geri sayımı başlatıyoruz
        handler.post(runnable);
    }
}