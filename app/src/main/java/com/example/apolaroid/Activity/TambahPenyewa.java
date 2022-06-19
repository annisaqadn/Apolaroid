package com.example.apolaroid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static android.text.TextUtils.isEmpty;

import com.example.apolaroid.Database.DatabaseHelper;
import com.example.apolaroid.R;

public class TambahPenyewa extends AppCompatActivity {
    Button btnA, btnB;//mendeklarasikan button
    EditText txtA, txtB, txtC, txtD;//mendeklarasikan edittext
    DatabaseHelper dbhelper;//mendeklarasikan database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_penyewa);//memanggil layout tambah penyewa
        getSupportActionBar().setTitle("Tambah Data Penyewa");//memberi judul "Tambah Data Penyewa" pada halaman activity

        dbhelper = new DatabaseHelper(this);//memanggil database

        txtA = findViewById(R.id.eid);//memanggil layout id edittext ID
        txtB = findViewById(R.id.enama);//memanggil layout id edittext Nama
        txtC = findViewById(R.id.ealamat);//memanggil layout id edittext Alamat
        txtD = findViewById(R.id.enotel);//memanggil layout id edittext No telepon


        btnA = findViewById(R.id.btnkanan);//memanggil layout id button tambah
        btnB = findViewById(R.id.btnkiri);//memanggil layout id button kembali

        //membuat fungsi btn1
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            //apabila data yang diisi tidak sesuai dan belum terisi semua
            {
                if (isEmpty(txtB.getText().toString())|| isEmpty(txtC.getText().toString())|| isEmpty(txtD.getText().toString())) {
                    Toast.makeText(TambahPenyewa.this, "Data Penyewa Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();//apabila berhasil maka berhasil menambahkan data penyewa
                } else if (txtA.length() < 5 )//hanya dapat memasukkan angka 5 karakter
                {
                    Toast.makeText(TambahPenyewa.this, "ID Penyewa harus diisi 5 Karakter Angka!", Toast.LENGTH_SHORT).show();//memberi pesan menggunakan toast untuk mengisi id dengan 5 karakter angka
                }else{  //apabila data yang dimasukan sesuai
                    SQLiteDatabase db = dbhelper.getWritableDatabase();//menulis ke database
                    db.execSQL("INSERT INTO penyewa(id_penyewa,nama_penyewa, alamat_penyewa, no_telpon) VALUES ('" + //mengeksekusi query insert
                            txtA.getText().toString() + "','" +
                            txtB.getText().toString() + "','" +
                            txtC.getText().toString() + "','" +
                            txtD.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data Penyewa Berhasil ditambahkan", Toast.LENGTH_SHORT).show();//memberi pesan toast
                    DataPenyewa.P.RefreshListp();//mengembalikan ke data penyewa
                    finish();
                }
            }
        });

        //membuat fungsi kembali
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}