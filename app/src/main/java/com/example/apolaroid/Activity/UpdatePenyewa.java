package com.example.apolaroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;
import com.example.apolaroid.Database.DatabaseHelper;
import com.example.apolaroid.R;

public class UpdatePenyewa extends AppCompatActivity {
    Button btnA, btnB;//mendeklarasikan button
    EditText txtA, txtB, txtC, txtD;//mendeklarasikan edittext
    protected Cursor cursor;//menginisialisasikan cursor
    DatabaseHelper dbhelper;//mendeklarasikan database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_penyewa);
        getSupportActionBar().setTitle("Update Data Penyewa");//memberi judul "Update Data Penyewa" pada halaman activity

        dbhelper =new DatabaseHelper(this);//memanggil database
        txtA = findViewById(R.id.eid);//memanggil layout id edittext ID
        txtB = findViewById(R.id.enama);//memanggil layout id edittext Nama
        txtC = findViewById(R.id.ealamat);//memanggil layout id edittext Alamat
        txtD = findViewById(R.id.enotel);//memanggil layout id edittext No telepon

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM penyewa WHERE id_penyewa = '" +//mengeksekusi query table penyewa
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        //mengembalikan nilai
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            txtA.setText(cursor.getString(0).toString());
            txtB.setText(cursor.getString(1).toString());
            txtC.setText(cursor.getString(2).toString());
            txtD.setText(cursor.getString(3).toString());
        }
        btnA = findViewById(R.id.btnkanan);//memanggil button update data penyewa
        btnB = findViewById(R.id.btnkiri);//memanggil button kembali

        //membuat fungsi button update data
        btnA.setOnClickListener(new View.OnClickListener() {
            //apabila data yang dimasukkan tidak sesuai
            @Override
            public void onClick(View v) {
                if (isEmpty(txtB.getText().toString())|| isEmpty(txtC.getText().toString())|| isEmpty(txtD.getText().toString())) {//memberi fungsi data penyewa tidak boleh kosong
                    Toast.makeText(UpdatePenyewa.this, "Data Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();//apabila data diisi kosong akan muncul pesan toast "Data Tidak Boleh Kosong!"
                } else if (txtA.length() < 5 ){//hanya dapat memasukkan angka 5 karakter
                    Toast.makeText(UpdatePenyewa.this, "ID Penyewa harus diisi 5 Karakter Angka!", Toast.LENGTH_SHORT).show();//memberi pesan menggunakan toast untuk mengisi id dengan 5 karakter angka
                }else
                //apabila data yang dimasukkan sesuai
                {
                    SQLiteDatabase db = dbhelper.getWritableDatabase();//menulis data kedalam database
                    db.execSQL("UPDATE penyewa SET nama_penyewa ='"+//mengeksekusi query update ke tabel penyewa
                            txtB.getText().toString() + "', alamat_penyewa='" +//mengeksekusi kolom alamat penyewa
                            txtC.getText().toString() + "', no_telpon='" +//mengeksekusi  kolom no telpon
                            txtD.getText().toString() + "' WHERE  id_penyewa='" +//mengeksekusi kolom id penyewa
                            txtA.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(),"Data Penyewa Berhasil diupdate", Toast.LENGTH_SHORT).show();//memberi pesan toast "Data Penyewa Berhasil diupdate"
                    DataPenyewa.P.RefreshListp();//memanggil activity data penyewa dan refreshlist
                    finish();
                }
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}