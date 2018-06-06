package com.example.eprastyo.loginwithvolley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class Pendaftaran_User extends AppCompatActivity implements View.OnClickListener{

    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextAlamat;
    private EditText editTextNoTelp;
    private EditText editTextEmail;

    private Button buttonAdd;
//    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran__user);

        //Inisialisasi dari View
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextNoTelp = (EditText) findViewById(R.id.editTextNoTelp);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
//        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
//        buttonView.setOnClickListener(this);
    }


    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addUser(){

        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        final String notelp = editTextNoTelp.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();



        class AddUser extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Pendaftaran_User.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Pendaftaran_User.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_USER_USERNAME,username);
                params.put(Konfigurasi.KEY_USER_PASSWORD,password);
                params.put(Konfigurasi.KEY_USER_ALAMAT,alamat);
                params.put(Konfigurasi.KEY_USER_NOTELP,notelp);
                params.put(Konfigurasi.KEY_USER_EMAIL,email);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.PENDAFTARAN_USER, params);
                return res;
            }
        }

        AddUser ae = new AddUser();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addUser();

        }
    }
}
