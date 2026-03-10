package com.example.uygulamalistview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SonucActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        TextView tvSonuc = findViewById(R.id.tvSonuc);
        Button btnYenidenDagit = findViewById(R.id.btnYenidenDagit);

        // MainActivity'den gönderilen verileri alıyoruz
        int secilenSayi = getIntent().getIntExtra("gelenPlaka", 0);
        String secilenSehir = getIntent().getStringExtra("gelenSehir");
        int sehrinGercekPlakasi = getIntent().getIntExtra("gercekPlaka", 0);

        // Karşılaştırma yapıyoruz
        if (secilenSayi == sehrinGercekPlakasi) {
            tvSonuc.setText(secilenSehir + " (" + secilenSayi + ") - Doğru Eşleşti!");
            tvSonuc.setTextColor(Color.GREEN); // Doğruysa yeşil yaz
        } else {
            tvSonuc.setText(secilenSehir + " için seçtiğin plaka: " + secilenSayi + "\nYanlış Eşleşti! Doğrusu: " + sehrinGercekPlakasi);
            tvSonuc.setTextColor(Color.RED); // Yanlışsa kırmızı yaz
        }

        // Yeniden Dağıt Butonuna tıklandığında:
        btnYenidenDagit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // İlk ekrana döner ve listeleri yeniden oluşturup (sayıları tekrar karıştırıp) ekranı tazeler
                Intent intent = new Intent(SonucActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Bu ekranı kapatır
            }
        });
    }
}