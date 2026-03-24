package com.example.hafta4_uygulama;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] resimler = {
            R.drawable.ist1, R.drawable.ist2, R.drawable.ist3,
            R.drawable.ank1, R.drawable.ank2, R.drawable.ank3,
            R.drawable.izm1, R.drawable.izm2, R.drawable.izm3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDiyalogAc = findViewById(R.id.btnDiyalogAc);

        btnDiyalogAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog diyalog = new Dialog(MainActivity.this);
                diyalog.setContentView(R.layout.diyalog_tasarim);
                diyalog.setCancelable(true);

                ImageView rastgeleResim = diyalog.findViewById(R.id.imgRastgele);
                Button btnIst = diyalog.findViewById(R.id.btnIstanbul);
                Button btnAnk = diyalog.findViewById(R.id.btnAnkara);
                Button btnIzm = diyalog.findViewById(R.id.btnIzmir);

                Random rastgele = new Random();
                int secilenIndex = rastgele.nextInt(resimler.length);
                rastgeleResim.setImageResource(resimler[secilenIndex]);

                btnIst.setOnClickListener(v1 -> {
                    startActivity(new Intent(MainActivity.this, IstanbulActivity.class));
                    diyalog.dismiss();
                });

                btnAnk.setOnClickListener(v12 -> {
                    startActivity(new Intent(MainActivity.this, AnkaraActivity.class));
                    diyalog.dismiss();
                });

                btnIzm.setOnClickListener(v13 -> {
                    startActivity(new Intent(MainActivity.this, IzmirActivity.class));
                    diyalog.dismiss();
                });

                diyalog.show();
            }
        });
    }
}