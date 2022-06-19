package com.example.apolaroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.apolaroid.Database.DatabaseHelper;
import com.example.apolaroid.R;

public class LihatPenyewa extends AppCompatActivity {
    Button btnA;//mendeklarasikan button
    TextView txtA, txtB, txtC, txtD;//mendeklarasikan textview
    protected Cursor cursor;//menginisialisasikan cursor
    DatabaseHelper dbhelper;//mendeklarasikan database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_penyewa);//memanggil layout lihat penyewa
        getSupportActionBar().setTitle("Lihat Data Penyewa");//memberi judul "Lihat Penyewa" pada layout

        dbhelper = new com.example.apolaroid.Database.DatabaseHelper(this);//deklarasi memanggil database
        txtA = findViewById(R.id.txt1);//memanggil id layout text
        txtB = findViewById(R.id.txt2);//memanggil id layout text
        txtC = findViewById(R.id.txt3);//memanggil id layout text
        txtD = findViewById(R.id.txt4);//memanggil id layout text

        //memanggil database
        SQLiteDatabase db = dbhelper.getReadableDatabase();//membaca database
        cursor = db.rawQuery("SELECT * FROM penyewa WHERE id_penyewa = '" + getIntent().getStringExtra("nama") + "'", null);//membaca query database tabel data penyewa dan membaca kolom nama

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);//mengurutkan sesuai dengan nomor
            txtA.setText(cursor.getString(0).toString());
            txtB.setText(cursor.getString(1).toString());
            txtC.setText(cursor.getString(2).toString());
            txtD.setText(cursor.getString(3).toString());
        }

        //membuat fungsi button kembali
        btnA = findViewById(R.id.btnSimpan);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}