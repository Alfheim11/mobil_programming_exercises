package com.example.calculator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private double ilkSayi = 0;
    private String islem = "";
    private boolean yeniSayiBaslat = true;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);


        View.OnClickListener rakamListener = v -> {
            Button b = (Button) v;
            if (yeniSayiBaslat) {
                textView.setText(b.getText().toString());
                yeniSayiBaslat = false;
            } else {
                textView.append(b.getText().toString());
            }
        };

        int[] rakamIdleri = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        for (int id : rakamIdleri) {
            findViewById(id).setOnClickListener(rakamListener);
        }


        findViewById(R.id.buttonTopla).setOnClickListener(v -> islemHazirla("+"));
        findViewById(R.id.buttonCikar).setOnClickListener(v -> islemHazirla("-"));
        findViewById(R.id.buttonCarp).setOnClickListener(v -> islemHazirla("*"));
        findViewById(R.id.buttonBol).setOnClickListener(v -> islemHazirla("/"));
        findViewById(R.id.buttonUs).setOnClickListener(v -> islemHazirla("^"));
        findViewById(R.id.buttonBolY).setOnClickListener(v -> islemHazirla("/")); // x/y de bölme işlemi görür




        findViewById(R.id.buttonKok).setOnClickListener(v -> {
            double sayi = Double.parseDouble(textView.getText().toString());
            textView.setText(String.valueOf(Math.sqrt(sayi)));
            yeniSayiBaslat = true;
        });


        findViewById(R.id.buttonBirBoluX).setOnClickListener(v -> {
            double sayi = Double.parseDouble(textView.getText().toString());
            if (sayi != 0) {
                textView.setText(String.valueOf(1 / sayi));
            } else {
                textView.setText("Hata");
            }
            yeniSayiBaslat = true;
        });


        findViewById(R.id.buttonC).setOnClickListener(v -> {
            textView.setText("0");
            ilkSayi = 0;
            islem = "";
            yeniSayiBaslat = true;
        });

        findViewById(R.id.buttonEsittir).setOnClickListener(v -> hesapla());
    }

    private void islemHazirla(String secilenIslem) {
        ilkSayi = Double.parseDouble(textView.getText().toString());
        islem = secilenIslem;
        yeniSayiBaslat = true;
    }

    @SuppressLint("SetTextI18n")
    private void hesapla() {
        double ikinciSayi = Double.parseDouble(textView.getText().toString());
        double sonuc = 0;

        switch (islem) {
            case "+": sonuc = ilkSayi + ikinciSayi; break;
            case "-": sonuc = ilkSayi - ikinciSayi; break;
            case "*": sonuc = ilkSayi * ikinciSayi; break;
            case "/":
                if (ikinciSayi != 0) sonuc = ilkSayi / ikinciSayi;
                else { textView.setText("Hata"); return; }
                break;
            case "^": sonuc = Math.pow(ilkSayi, ikinciSayi); break;
        }


        if (sonuc == (long) sonuc) {

            textView.setText(String.valueOf((long) sonuc));
        } else {

            textView.setText(String.valueOf(sonuc));
        }

        yeniSayiBaslat = true;
    }
}