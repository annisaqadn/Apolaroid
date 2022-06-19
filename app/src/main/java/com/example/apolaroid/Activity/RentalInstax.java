package com.example.apolaroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apolaroid.Database.DatabaseHelper;
import com.example.apolaroid.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class RentalInstax extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseHelper dbhelper;//mendeklarasikan database
    TextView lama;//mendeklarasikan textview
    RadioGroup promo;//mendeklarasikan radio group promo
    RadioButton weekend, weekday;//mendeklarasikan radio button weekend dan weekday
    Button kurang, tambah, rental;//mendeklarasikan button kurang, tambah,rental
    private Spinner spinnera, spinnerb;//menginisialisasikan spinner
    String Instax, Penyewa, Lama, idinstax, idpenyewa, tanggal, stringInstax, Promo;//membuat parameter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_instax);
        getSupportActionBar().setTitle("Data Rental");//memberi judul "Data Rental" pada layout

        dbhelper = new com.example.apolaroid.Database.DatabaseHelper(this);//deklarasi memanggil database
        lama = findViewById(R.id.lamasewa);
        promo = findViewById(R.id.duapromo);
        weekend = findViewById(R.id.radbutWeekEnd);
        weekday = findViewById(R.id.radbutWeekDay);
        rental = findViewById(R.id.btnRental);
        spinnera = findViewById(R.id.spinnernama);
        spinnerb = findViewById(R.id.spinnerinstax);
        kurang = findViewById(R.id.btnkurang);
        tambah = findViewById(R.id.btntambah);

        //membuat fungsi button tambah
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int t = Integer.valueOf(lama.getText().toString());
                t++;
                lama.setText(String.valueOf(t));
            }
        });
        //membuat fungsi button kurang
        kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = Integer.valueOf(lama.getText().toString());
                k--;
                lama.setText(String.valueOf(k));
            }
        });
        spinnera.setOnItemSelectedListener(this);
        spinnerb.setOnItemSelectedListener(this);

        loadSpinnerDP();//memanggil function spinner penyewa instax
        loadSpinnerDI();//memanggil function spinner instax
        Calendar c = Calendar.getInstance();//parameter calendar
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//mengatur format tanggal calendar menjadi "yyyy-MM-dd"
        tanggal = df.format(c.getTime());

        rental.setOnClickListener(new View.OnClickListener() { //membuat fungsi button rental sekarang
            @Override
            public void onClick(View v) {
                if (Float.valueOf(lama.getText().toString()) < 1) {
                    Toast.makeText(RentalInstax.this, "Lama Sewa harus lebih dari 0", Toast.LENGTH_SHORT).show();//apabila lama sewa kurang 1 hari akan muncul pesan toast "Lama Sewa harus lebih dari 0"
                } else {
                    Lama = lama.getText().toString();
                    if (weekend.isChecked()) {
                        Promo = "10%";//potongan harga 10% untuk weekend
                    } else if (weekday.isChecked()) {
                        Promo = "5%";//potongan harga 5% untuk weekday
                    }
                    //membuat objek bundle
                    Bundle b = new Bundle();
                    b.putString("a", idpenyewa.trim());
                    b.putString("b", idinstax.trim());
                    b.putString("c", tanggal.trim());
                    b.putString("d", Promo.trim());
                    b.putString("e", Lama.trim());
                    Intent r = new Intent(getApplicationContext(), TransaksiRental.class);
                    r.putExtras(b);
                    //berpindah ke transaksi
                    startActivity(r);
                }
            }
        });
    }
    //membuat fungsi spinner kamera
    private void loadSpinnerDI() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<String> categories = db.semuaKamerainstax();//memanggil parameter database

        final ArrayAdapter<String> dataAdapterb = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);//mengubah data menjadi array
        dataAdapterb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerb.setAdapter(dataAdapterb);
        spinnerb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Instax = parent.getItemAtPosition(position).toString();
                stringInstax = Instax.substring(8);

                idinstax = Instax.substring(0, 6);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //membuat fungsi spinner penyewa
    private void loadSpinnerDP() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());//memanggil database
        List<String> categories = db.semuaPenyewa();//memanggil parameter database

        ArrayAdapter<String> dataAdaptera = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);//mengubah data menjadi array
        dataAdaptera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnera.setAdapter(dataAdaptera);
        spinnera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Penyewa = parent.getItemAtPosition(position).toString();
                idpenyewa = Penyewa.substring(0, 6);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}