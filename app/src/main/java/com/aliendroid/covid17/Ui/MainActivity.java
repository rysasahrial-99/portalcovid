package com.aliendroid.covid17.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.aliendroid.model.Indonesia;
import com.aliendroid.covid17.R;
import com.aliendroid.portal.PortalActivity;
import com.aliendroid.tescovid.GameActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private TextView sembuh;
    private TextView rawat;
    private TextView meninggal;
    private TextView positif;
    private TextView negara;
    private CardView tblProv, tblGlobal, tblportal, tblquiz;
    int scorehitungan =0;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences Myscorehitungan = this.getSharedPreferences("Myscoretes", Context.MODE_PRIVATE);
        scorehitungan = Myscorehitungan.getInt("scoretes", 0);

        TextView txthasil = findViewById(R.id.txthasil);
        if (scorehitungan==0) {
            txthasil.setText("Selesaikan semua pertanyaan untuk menampilkan hasil tes.");
        } else if (scorehitungan<=7) {
            txthasil.setText("Pengetahuan anda tentang Covid19 sangat minim, hasil tes menunjukan anda belum bisa membedakan antara fakta dan hoax covid19.");

        } else {
            txthasil.setText("Pengetahuan anda tentang Covid19 sangat bagus, hasil tes menunjukan anda suda bisa membedakan antara fakta dan hoax covid19.");

        }



        rawat = findViewById(R.id.txtrawat);
        rawat.setText(Indonesia.dirawat);

        sembuh = findViewById(R.id.txtSembuh);
        sembuh.setText(Indonesia.sembuh);

        meninggal = findViewById(R.id.txtmeninggal);
        meninggal.setText(Indonesia.meninggal);

        positif = findViewById(R.id.txtTotal);
        positif.setText(Indonesia.positif);

        negara = findViewById(R.id.txtnegara);
        negara.setText("Kasus di " + Indonesia.name);

        tblProv = findViewById(R.id.tblProv);
        tblProv.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ProvinsiActivity.class);
                startActivity(intent);


            }
        });

        tblquiz = findViewById(R.id.tblquiz);
        tblquiz.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                startActivity(intent);


            }
        });




        tblportal = findViewById(R.id.tblportal);
        tblportal.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PortalActivity.class);
                startActivity(intent);


            }
        });

        tblGlobal = findViewById(R.id.tblglobal);
        tblGlobal.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GlobalActivity.class);
                startActivity(intent);


            }
        });

        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("d/M/yyyy h:m:s a");
        //SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        String strdate1 = sdf1.format(c1.getTime());
        TextView txtdate1 = (TextView) findViewById(R.id.txttanggal);
        txtdate1.setText(strdate1);
    }


    public void onResume(){
        super.onResume();
        rawat = findViewById(R.id.txtrawat);
        rawat.setText(Indonesia.dirawat);

        sembuh = findViewById(R.id.txtSembuh);
        sembuh.setText(Indonesia.sembuh);

        meninggal = findViewById(R.id.txtmeninggal);
        meninggal.setText(Indonesia.meninggal);

        positif = findViewById(R.id.txtTotal);
        positif.setText(Indonesia.positif);

        negara = findViewById(R.id.txtnegara);
        negara.setText("Kasus di " + Indonesia.name);


        TextView txthasil = findViewById(R.id.txthasil);
        if (scorehitungan==0) {
            txthasil.setText("Selesaikan semua pertanyaan untuk menampilkan hasil tes.");
        } else if (scorehitungan<=5) {
            txthasil.setText("Pengetahuan anda tentang Covid19 sangat minim, hasil tes menunjukan anda belum bisa membedakan antara fakta dan hoax covid19.");

        } else {
            txthasil.setText("Pengetahuan anda tentang Covid19 sangat bagus, hasil tes menunjukan anda suda bisa membedakan antara fakta dan hoax covid19.");

        }
    }


}