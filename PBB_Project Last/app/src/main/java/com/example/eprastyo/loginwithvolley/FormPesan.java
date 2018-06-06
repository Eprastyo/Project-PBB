package com.example.eprastyo.loginwithvolley;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class FormPesan extends AppCompatActivity implements View.OnClickListener{
    String [] kota_asal = {
            "Surabaya",
            "Malang",
            "Kediri",
            "Sidoarjo",
            "Banyuwangi"
    };

    String [] kota_tujuan ={
            "Jakarta",
            "Bogor",
            "Bandung",
            "Tangerang"
    };

    String [] armada;

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pesan);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,kota_asal);
        AutoCompleteTextView txtKotaAsal = (AutoCompleteTextView)findViewById(R.id.txtKotaAsal);
        txtKotaAsal.setThreshold(3);
        txtKotaAsal.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,kota_tujuan);
        AutoCompleteTextView txtKotaTujuan = (AutoCompleteTextView)findViewById(R.id.txtKotaTujuan);
        txtKotaTujuan.setThreshold(3);
        txtKotaTujuan.setAdapter(adapter2);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        armada = getResources().getStringArray(R.array.armada_array);
        Spinner s1 = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,armada);
        s1.setAdapter(adapter);
    }
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    public void onClick_Hasil(View view){

        Intent baru = new Intent(getApplicationContext(),FormHasil.class);

        Bundle b = new Bundle();

        AutoCompleteTextView nama_a = (AutoCompleteTextView) findViewById(R.id.txtKotaAsal);
        b.putString("kotaAsal",nama_a.getText().toString());

        AutoCompleteTextView nama_b = (AutoCompleteTextView) findViewById(R.id.txtKotaTujuan);
        b.putString("kotaTujuan",nama_b.getText().toString());

        EditText tanggal = (EditText)findViewById(R.id.in_date);
        b.putString("tanggal",tanggal.getText().toString());

        EditText waktu = (EditText)findViewById(R.id.in_time);
        b.putString("waktu",waktu.getText().toString());

        baru.putExtras(b);
        startActivity(baru);

    }

    public void Kembali(View view){
        Intent kemb = new Intent(getApplicationContext(),Navigasi.class);
        startActivity(kemb);
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
                Toast.makeText(FormPesan.this,"Setting Clicked",Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
