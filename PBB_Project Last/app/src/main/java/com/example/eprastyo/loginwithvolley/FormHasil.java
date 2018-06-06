package com.example.eprastyo.loginwithvolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FormHasil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_hasil);

        Bundle a = getIntent().getExtras();

        TextView asal = (TextView) findViewById(R.id.hasilAsal);
        TextView tujuan = (TextView) findViewById(R.id.hasilTujuan);

        asal.setText(a.getCharSequence("kotaAsal"));
        tujuan.setText(a.getCharSequence("kotaTujuan"));

        TextView tgl = (TextView) findViewById(R.id.tanggal);
        TextView jam = (TextView) findViewById(R.id.waktu);

        tgl.setText(a.getCharSequence("tanggal"));
        jam.setText(a.getCharSequence("waktu"));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.MenuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(getApplicationContext(),Login.class));
                break;
            case R.id.MenuSetting:
                Toast.makeText(FormHasil.this,"Setting Clicked",Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    public void Close(View view){
        Intent a = new Intent(getApplicationContext(),Navigasi.class);
        startActivity(a);
        finish();
    }
}
