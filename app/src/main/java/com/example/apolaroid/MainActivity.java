package com.example.apolaroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;

import com.example.apolaroid.Activity.DataInstax;
import com.example.apolaroid.Activity.DataPenyewa;
import com.example.apolaroid.Activity.RentalInstax;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageButton tvInstax, tvPenyewa, tvRental;//mendeklarasikan imagebutton
    TextView tvini;//mendeklarasikan TextView
    String hariIni;//mendeklarasikan hari

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//memanggil layout activity main
        //membaca atribut pada layout
        tvInstax = findViewById(R.id.btnInstax);//memanggil id layout button Instax
        tvPenyewa = findViewById(R.id.btnPenyewaInstax);//memanggil id layout button Penyewa Instax
        tvRental = findViewById(R.id.btnRentalInstax);//memanggil id layout button Rental Instax
        tvini = findViewById(R.id.tvDate);//memanggil id layout textview hari, tanggal bulan tahun
        //fungsi tanggal
        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        if (hariIni.equalsIgnoreCase("sunday")) {
            hariIni = "Minggu";
        } else if (hariIni.equalsIgnoreCase("monday")) {
            hariIni = "Senin";
        } else if (hariIni.equalsIgnoreCase("tuesday")) {
            hariIni = "Selasa";
        } else if (hariIni.equalsIgnoreCase("wednesday")) {
            hariIni = "Rabu";
        } else if (hariIni.equalsIgnoreCase("thursday")) {
            hariIni = "Kamis";
        } else if (hariIni.equalsIgnoreCase("friday")) {
            hariIni = "Jumat";
        } else if (hariIni.equalsIgnoreCase("saturday")) {
            hariIni = "Sabtu";
        }

        getToday();
        //memanggil bundle dari data instax
        tvInstax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DataInstax.class);
                startActivity(i);
            }
        });
        //memanggil bundle dari data penyewa
        tvPenyewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(MainActivity.this, DataPenyewa.class);
                startActivity(p);
            }
        });
        //memanggil bundle dari rental instax
        tvRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(MainActivity.this, RentalInstax.class);
                startActivity(r);
            }
        });
    }
    //membuuat parameter untuk fungsi Date
    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d", date);//membuat fungsi tanggal
        String bulanke = (String) DateFormat.format("M", date);//membuat fungsi bulan
        String tahun = (String) DateFormat.format("yyyy", date);//membuat fungsi tahun

        int month = Integer.parseInt(bulanke);
        String bulan = null;
        if (month == 1) {
            bulan = "Januari";
        } else if (month == 2) {
            bulan = "Februari";
        } else if (month == 3) {
            bulan = "Maret";
        } else if (month == 4) {
            bulan = "April";
        } else if (month == 5) {
            bulan = "Mei";
        } else if (month == 6) {
            bulan = "Juni";
        } else if (month == 7) {
            bulan = "Juli";
        } else if (month == 8) {
            bulan = "Agustus";
        } else if (month == 9) {
            bulan = "September";
        } else if (month == 10) {
            bulan = "Oktober";
        } else if (month == 11) {
            bulan = "November";
        } else if (month == 12) {
            bulan = "Desember";
        }
        String formatFix = hariIni + ", " + tanggal + " " + bulan + " " + tahun;
        tvini.setText(formatFix);
    }
}