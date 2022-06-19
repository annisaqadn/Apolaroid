package com.example.apolaroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.example.apolaroid.Database.DatabaseHelper;
import com.example.apolaroid.R;

public class DataPenyewa extends AppCompatActivity {
    ListView lvPenyewa;//mendeklarasikan ListView
    String[] daftar;//mendeklarasikan daftar
    protected Cursor cursor;//menginisialisasikan cursor
    DatabaseHelper dbhelper;//mendeklarasikan database
    public static DataPenyewa P;//menginisalisasikan activity penyewa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_penyewa);//memanggil layout activity data penyewa

        getSupportActionBar().setTitle("Data Penyewa");//memberi judul "Data Penyewa" pada activity data penyewa

        ImageButton fab = findViewById(R.id.btnTambah);//memanggil floatingbutton tambah pada layout
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tp = new Intent(DataPenyewa.this, TambahPenyewa.class);//memanggil activity Tambah penyewa
                startActivity(tp);//memulai activity tambah penyewa
            }
        });

        P = this;
        dbhelper = new DatabaseHelper(this);//menginisialiasikan database
        RefreshListp();//mengambil fungsi dari refreshlist
    }
    //membuat fungsi refresh list
    public void RefreshListp() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();//membaca database
        cursor = db.rawQuery("SELECT * FROM penyewa", null);//membaca data dengan memasukkan query
        daftar = new String[cursor.getCount()];//mengembalikan nilai
        cursor.moveToFirst();//mengembalikan set kosong

        //mengembalikan nilai
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString()+" - "+cursor.getString(1).toString();
        }
        lvPenyewa = findViewById(R.id.lvpenyewa);//memanggil id layout
        lvPenyewa.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        lvPenyewa.setSelected(true);
        lvPenyewa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final String id = selection.substring(0,5);//mengekstrak substring
                final CharSequence[] dialogitem = {"Lihat Data Penyewa", "Update Data Penyewa", "Hapus Data Penyewa"};//memberi pesan pada dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(DataPenyewa.this);
                builder.setTitle("Pilihan");//memberi judul pada halaman activity
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent l = new Intent(getApplicationContext(), LihatPenyewa.class);//memanggil lihat data penyewa
                                l.putExtra("nama", id); //memanggil database tabel penyewa kolom nama
                                startActivity(l);//memulai activity
                                break;
                            case 1:
                                Intent u = new Intent(getApplicationContext(), UpdatePenyewa.class);// memanggil ubah data penyewa
                                u.putExtra("nama", id);// memanggil database tabel penyewa kolom nama
                                startActivity(u);// memulai activity
                                break;

                            //membuat fungsi delete
                            case 2:
                                SQLiteDatabase db = dbhelper.getWritableDatabase();//menulis data ke database
                                db.execSQL("DELETE FROM penyewa WHERE id_penyewa = '" + id + "'");//memberi query delete data pada tabel penyewa
                                RefreshListp();//memanggil function refreshlist
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) lvPenyewa.getAdapter()).notifyDataSetInvalidated();
    }
}