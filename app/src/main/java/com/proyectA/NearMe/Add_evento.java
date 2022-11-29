package com.proyectA.NearMe;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.proyectA.NearMe.Modelo.Evento;

import java.util.Calendar;
import java.util.UUID;

public class Add_evento extends AppCompatActivity implements View.OnClickListener {
    Button btnDatePicker, btnTimePicker, btnMapPicker, btnCrear;
    EditText mTitulo;
    TextView txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    //database
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_evento);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        btnMapPicker=(Button)findViewById(R.id.btn_maps);
        btnCrear=(Button)findViewById(R.id.btn_crear);
        mTitulo = findViewById(R.id.txt_tit);


        txtDate= (TextView) findViewById(R.id.tv_date_select);
        txtTime= (TextView) findViewById(R.id.tv_time_select);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnMapPicker.setOnClickListener(this);
        btnCrear.setOnClickListener(this);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Obtiene la fecha actual
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            Context context = getApplicationContext();

                            if (year<mYear) {
                                Toast toast = Toast.makeText(context, "El año ingresado es menor al actual", Toast.LENGTH_SHORT);
                                toast.show();
                            }else{
                                if (monthOfYear < mMonth) {
                                    Toast toast1 = Toast.makeText(context, "El mes ingresado es menor al actual", Toast.LENGTH_SHORT);
                                    toast1.show();
                                }else{
                                    if (dayOfMonth < mDay && monthOfYear==mMonth) {
                                        Toast toast2 = Toast.makeText(context, "El dia ingresado es menor al actual", Toast.LENGTH_SHORT);
                                        toast2.show();
                                    } else {
                                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    }
                                }
                            }

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Obtiene el tiempo actual
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Lanza el dialogo de tiempo
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
        if (v == btnMapPicker) {
            //Lanzar el fragmento de mapa para recuperar la localizacion y guardar el nombre, lat y long
            Context context = getApplicationContext();
            Toast toasta = Toast.makeText(context, "Aqui lanzaria el mapa, si hubiera uno D:", Toast.LENGTH_SHORT);
            toasta.show();
        }
        if (v == btnCrear){

            String titulo = mTitulo.getText().toString();
            String ubicacion = txtDate.getText().toString();
            String asistentes = txtTime.getText().toString();

            Evento p = new Evento();
            p.setID(UUID.randomUUID().toString());
            p.setNombre(titulo);
            p.setUbicacion(ubicacion);
            p.setAsistentes(asistentes);

            database.getReference().getRoot().child(p.getID()).setValue(p);
            Toast.makeText(this, "Agregado correctamente", Toast.LENGTH_SHORT).show();
            clear();
        }
    }
    public void clear(){
        mTitulo.setText("");
        txtTime.setText("");
        txtDate.setText("");
    }
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}