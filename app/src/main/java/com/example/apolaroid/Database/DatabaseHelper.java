package com.example.apolaroid.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rentalins.db";// nama database
    private String KEY_NAME = "NAMA"; //menginisialisasi nama database

    //method untuk membaca database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlkamera = "create table kamera(id_kamera integer primary key, nama_kamera text null, harga_kamera integer null, no_seri text null , status text null);";//membuat tabel kamera instax
        Log.d("Data", "onCreate: " + sqlkamera);
        db.execSQL(sqlkamera);//untuk mengeksekusi tabel kamera instax

        String sqlpenyewa = "create table penyewa (id_penyewa integer primary key, nama_penyewa text null, alamat_penyewa text null, no_telpon text null);";// membuat tabel penyewa
        Log.d("Data", "onCreate: " + sqlpenyewa);
        db.execSQL(sqlpenyewa);//untuk mengeksekusi tabel penyewa

        // memasukkan data ke database tabel kamera instax
        db.execSQL("insert into kamera values (" + "'11001'," + "'Instax Mini 8 Minion + Paper'," + "130000," + "'Mini 8 Minion'," + "'y'" + ");" + "");
        db.execSQL("insert into kamera values (" + "'11002'," + "'Instax Mini 70 + Paper'," + "105000," + "'Mini 70'," + "'y'" + ");" + "");
        db.execSQL("insert into kamera values (" + "'11003'," + "'Instax Square SQ-1 + Paper'," + "100000," + "'Square SQ-1'," + "'y'" + ");" + "");
        db.execSQL("insert into kamera values (" + "'11004'," + "'Instax Mini 9 + Paper'," + "120000," + "'Mini 9'," + "'y'" + ");" + "");
        db.execSQL("insert into kamera values (" + "'11005'," + "'Instax Mini 11 + Paper'," + "130000," + "'Mini 11'," + "'y'" + ");" + "");
        db.execSQL("insert into kamera values (" + "'11006'," + "'Instax Share Sp-3 + Paper'," + "110000," + "'Share Sp-3'," + "'y'" + ");" + "");
        db.execSQL("insert into kamera values (" + "'11007'," + "'Instax WIDE 300 + Paper'," + "160000," + "'WIDE 300'," + "'y'" + ");" + "");

        //memasukkan data ke database tabel penyewa
        db.execSQL("insert into penyewa values (" + "'20001'," + "'Ananda Syifa'," + "'Gunungkidul'," + "'081757800221'" + ");" + "");
    }

    //membuat parameter untuk membaca list instax fujifilm
    public List<String> semuaKamerainstax() {
        List<String> instaxs = new ArrayList<String>();//merubah data menjadi arraylist
        String selectQuery = "select * from kamera where status = 'y'";//query memilih data dalam database tabel kamera
        SQLiteDatabase db = this.getReadableDatabase();//membaca data dalam database
        Cursor cursor = db.rawQuery(selectQuery, null);//cursor untuk membaca data

        //perulangan data
        if (cursor.moveToFirst()) {
            do {
                instaxs.add(cursor.getString(0) + " - " + cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();//menutup database apabila sudah tidak digunakan

        return instaxs;//mengembalikan nilai
    }

    //membuat parameter untuk membaca list penyewa
    public List<String> semuaPenyewa() {
        List<String> penyewas = new ArrayList<String>();//merubah data dalam arraylist
        String selectQuery = "select * from penyewa";//query memilih data dalam database tabel penyewa
        //membaca data dalam database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//cursor untuk membaca data

        //perulangan data
        if (cursor.moveToFirst()) {
            do {
                penyewas.add(cursor.getString(0) + " - " + cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();//menutup database apabila sudah tidak digunakan

        return penyewas;//mengembalikan nilai
    }


    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}