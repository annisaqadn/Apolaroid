package com.example.apolaroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.apolaroid.Database.DatabaseHelper;
import com.example.apolaroid.R;

public class DataInstax extends AppCompatActivity {
    String[] daftar;//mendeklarasikan array
    ListView lvInstax;//mendeklarasikan listview
    protected Cursor cursor;//inisialisasi cursor
    DatabaseHelper dbhelper;//memanggil class database
    public static com.example.apolaroid.Activity.DataInstax I;//inisialisasi activity data Instax

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_instax2);//memanggil layout data Instax

        getSupportActionBar().setTitle("Data Instax");//memberi judul pada activity

        I = this;
        dbhelper = new DatabaseHelper(this);//membuat deklarasi membaca database dari class databasehelper
        RefreshListi();
    }

    //membuat parameter refreshlist
    private void RefreshListi() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();//membaca data dari database
        cursor = db.rawQuery("SELECT * FROM kamera WHERE status = 'y'", null);//memasukkan query
        daftar = new String[cursor.getCount()];//menghitung data yang di list
        cursor.moveToFirst();
        //melakukan perulangan data
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(1);
        }
        lvInstax = findViewById(R.id.lvinstax);//memanggil layout id isi
        lvInstax.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        lvInstax.setSelected(true);
        lvInstax.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                Intent i = new Intent(com.example.apolaroid.Activity.DataInstax.this, com.example.apolaroid.Activity.DetailInstax.class);//memanggil activity detail instax
                i.putExtra("namainstax", selection);//memanggil kolom nama instax
                startActivity(i);
            }
        });

        ((ArrayAdapter) lvInstax.getAdapter()).notifyDataSetInvalidated();
    }
}