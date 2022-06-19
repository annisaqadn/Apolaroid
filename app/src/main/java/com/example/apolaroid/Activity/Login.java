package com.example.apolaroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apolaroid.MainActivity;
import com.example.apolaroid.R;

public class Login extends AppCompatActivity {
    Button btnsignin;//Mendeklarasi variabel untuk button
    EditText edusername, edpassword;//Mendeklarasi variabel untuk EditText
    String username, password;//Mendeklarasi variabel untuk menyimpan username dan password
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnsignin = findViewById(R.id.btnSignin);//memanggil id layout button
        edusername = findViewById(R.id.edUsername);//memanggil id layout Edittext
        edpassword = findViewById(R.id.edPassword);//memanggil id layout Edittext
        //Membuat fungsi onclick pada button btnsignin
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edusername.getText().toString();//Menyimpan input user di edittext username kedalam variabel username
                password = edpassword.getText().toString();//Menyimpan input user di edittext password kedalam variabel password


                String uname = "Adminapolaroid";//Mengeset username yang benar
                String pwd = "iniAdmin";//Mengeset password yang benar

                if (username.isEmpty() && password.isEmpty()) { //mengecek apakah isi dari username dan password sudah sama dengan username dan password yang sudah di set
                    edusername.setError("Username harus diisi");//menampilkan pesan "nama harus anda isi"
                } else if (username.equals(uname) && (password.isEmpty())) {
                    edpassword.setError("Masukkan password!");//menampilkan pesan "Masukkan password anda"
                } else if (username.equals(uname) && password.equals(pwd)) { //Memasukkan username "Adminapolaroid" dan Password "iniAdmin" untuk sign in
                    Toast t = Toast.makeText(getApplicationContext(), "Login Sukses", Toast.LENGTH_LONG);//membuat variabel toast dan menampilkan pesan "Login sukses"
                    t.show();
                    Intent I = new Intent(Login.this, MainActivity.class);
                    startActivity(I);
                } else if (username.equals(uname) && !password.equals(pwd)) {
                    Toast t = Toast.makeText(getApplicationContext(), "Password salah", Toast.LENGTH_LONG);//membuat variabel toast dan membuat toast dan menampilkan pesan "password salah"
                    t.show();
                } else {
                    Toast t = Toast.makeText(getApplicationContext(), "Username salah", Toast.LENGTH_LONG);//membuat variabel toast dan membuat toast dan menampilkan pesan "username salah"
                    t.show();
                }
            }
        });
    }
}