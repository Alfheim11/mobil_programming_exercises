package com.example.uygulamalistview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    // Kullanıcının seçtiği verileri tutacağımız değişkenler
    int secilenPlaka = -1;
    int secilenSehirIndeksi = -1;
    String secilenSehirAdi = "";

    // Şehirler dizisi (Sen burayı 81 ile doldurabilirsin, ben örnek için kısa tuttum)
    String[] sehirler = {
            "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir",
            "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli",
            "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari",
            "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir",
            "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir",
            "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat",
            "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman",
            "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye",
            "Düzce"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView listViewSayilar = findViewById(R.id.listViewPlaka);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView listViewSehirler = findViewById(R.id.listViewSehir);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnKontrolEt = findViewById(R.id.btnKontrol);

        // 1-81 arası sayıları oluşturup karıştırıyoruz
        ArrayList<Integer> sayilarListesi = new ArrayList<>();
        for (int i = 1; i <= 81; i++) {
            sayilarListesi.add(i);
        }
        Collections.shuffle(sayilarListesi); // Sayıları rastgele dağıtır

        // Listeleri ekrana bağlıyoruz (simple_list_item_single_choice ile yanında seçme butonu çıkar)
        ArrayAdapter<Integer> sayiAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, sayilarListesi);
        listViewSayilar.setAdapter(sayiAdapter);

        ArrayAdapter<String> sehirAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, sehirler);
        listViewSehirler.setAdapter(sehirAdapter);

        // SOL LİSTE (Sayılar) - Manuel Seçimi İptal Ediyoruz
        listViewSayilar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Eğer kullanıcı inatla sol taraftaki sayılara tıklamaya çalışırsa:
                if (secilenSehirIndeksi != -1) {
                    // Daha önce sağdan bir şehir seçildiyse, sol taraftaki seçimi zorla o eski hizaya geri getir
                    listViewSayilar.setItemChecked(secilenSehirIndeksi, true);
                } else {
                    // Henüz hiçbir şehir seçilmediyse, tıkladığı yerdeki işareti hemen geri kaldır
                    listViewSayilar.setItemChecked(position, false);
                }
            }
        });

        // SAĞ LİSTE (Şehirler) - Asıl Seçim Buradan Yapılacak
        listViewSehirler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 1. Seçilen verileri hafızaya alıyoruz
                secilenSehirIndeksi = position;
                secilenPlaka = sayilarListesi.get(position);
                secilenSehirAdi = sehirler[position];

                // 2. Soldaki (Sayılar) listesinde aynı hizadaki satırı OTOMATİK seçili hale getiriyoruz
                listViewSayilar.setItemChecked(position, true);

                // 3. Ekstra Dokunuş: 81 il uzun bir liste olduğu için, sen sağdan bir şehir seçtiğinde
                // sol tarafı da ekranda o hizaya otomatik olarak kaydırıyoruz (aşağıda kalmasın diye)
                listViewSayilar.smoothScrollToPosition(position);
            }
        });

        // Kontrol Et Butonuna Tıklandığında:
        btnKontrolEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eğer ikisinden biri seçilmediyse hata ver
                if (secilenPlaka == -1 || secilenSehirIndeksi == -1) {
                    Toast.makeText(MainActivity.this, "Lütfen bir sayı ve bir şehir seçin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 2. Ekrana verileri gönderiyoruz
                Intent intent = new Intent(MainActivity.this, SonucActivity.class);
                intent.putExtra("gelenPlaka", secilenPlaka);
                intent.putExtra("gelenSehir", secilenSehirAdi);
                // Diziler 0'dan başlar, plakalar 1'den. O yüzden indeksin 1 fazlası gerçek plakadır.
                intent.putExtra("gercekPlaka", secilenSehirIndeksi + 1);
                startActivity(intent);
            }
        });
    }
}