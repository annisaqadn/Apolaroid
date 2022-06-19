package com.example.apolaroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.apolaroid.Database.DatabaseHelper;
import com.example.apolaroid.MainActivity;
import com.example.apolaroid.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TransaksiRental extends AppCompatActivity {
    DatabaseHelper dbhelper;//mendeklarasikan database
    TextView tgl, idpenyewa, idinstax, lama, promo; //mendeklarasikan textview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_rental);
        getSupportActionBar().setTitle("Transaksi Rental Instax");//memberi judul "Transaksi Rental Instax" pada layout

        tgl = findViewById(R.id.tvtgl);
        idpenyewa = findViewById(R.id.tvidpenyewa);
        idinstax = findViewById(R.id.tvidinstax);
        lama = findViewById(R.id.tvlamarental);
        promo = findViewById(R.id.tvpromo);

        Bundle bundle = getIntent().getExtras();
        String IDPenyewa = bundle.getString("a");
        String IDInstax = bundle.getString("b");
        String Tanggal = bundle.getString("c");
        String Promo = bundle.getString("d");
        String Lama = bundle.getString("e");

        idpenyewa.setText(IDPenyewa);
        idinstax.setText(IDInstax);
        tgl.setText(Tanggal);
        promo.setText(Promo);
        lama.setText(Lama);
    }
}


