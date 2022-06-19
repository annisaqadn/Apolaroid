package com.example.apolaroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apolaroid.Database.DatabaseHelper;
import com.example.apolaroid.R;

public class DetailInstax extends AppCompatActivity {
    String sInstax, sHarga, sGambar, sNoser;
    DatabaseHelper dbHelper;//menginisialisasi database
    TextView tInstax, tHarga, tNoser;//menginisialisasi textview
    ImageView iGambar;//menginisialisasi imageview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_instax);//memanggil layout activity detail instax
        getSupportActionBar().setTitle("Detail Instax");//memberi judul "Detail Instax" pada layout

        Bundle terima = getIntent().getExtras();//memberi bundle
        String instax = terima.getString("namainstax");//mendeklarasikan kolom kamera instax

        dbHelper = new DatabaseHelper(this);//memanggil database
        tInstax = findViewById(R.id.Tinstax);//memanggil id layout
        tHarga = findViewById(R.id.Tharga);//memanggil id layout
        tNoser = findViewById(R.id.Tnoser);//memanggil id layout
        iGambar = findViewById(R.id.Iinstax);//memanggil id layout

        //mengambil data kamera
        SQLiteDatabase db = dbHelper.getReadableDatabase();//untuk membaca database
        Cursor cursor = db.rawQuery("select * from kamera where nama_kamera = '" + instax + "'", null);//membuat query membaca kamera
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sInstax = cursor.getString(1);
            sHarga = cursor.getString(2);
            sNoser = cursor.getString(3);
        }


        //mencocokan antara nama data dan memasukkan foto kamera instax
        if (sInstax.equals("Instax Mini 8 Minion + Paper")) {
            sGambar = "mini8minion";
        } else if (sInstax.equals("Instax Mini 70 + Paper")) {
            sGambar = "mini70";
        } else if (sInstax.equals("Instax Square SQ-1 + Paper")) {
            sGambar = "squaresq1";
        } else if (sInstax.equals("Instax Mini 9 + Paper")) {
            sGambar = "mini9";
        } else if (sInstax.equals("Instax Mini 11 + Paper")) {
            sGambar = "mini11";
        }else if (sInstax.equals("Instax Share Sp-3 + Paper")) {
            sGambar = "sharesp3";
        }else if (sInstax.equals("Instax WIDE 300 + Paper")) {
            sGambar = "wide300";
        }

        tInstax.setText(sInstax);
        tNoser.setText(sNoser);
        iGambar.setImageResource(getResources().getIdentifier(sGambar, "drawable", getPackageName()));//untuk fungsi memasukkan foto menggunakan setImageResource(getResources
        tHarga.setText("Rp. " + sHarga);

    }
}