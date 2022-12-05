package com.proyectA.NearMe;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.FirebaseDatabase;
import com.proyectA.NearMe.Modelo.Evento;

import java.util.Calendar;
import java.util.UUID;

public class Add_evento extends AppCompatActivity implements View.OnClickListener {
    Button btnDatePicker, btnTimePicker, btnMapPicker, btnCrear, btnCancelar;
    EditText mTitulo, mDescripcion, mDate, mTime, mMaps;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private String latidud, longitud;
    //database
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_evento);
        //botones
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        btnMapPicker=(Button)findViewById(R.id.btn_maps);
        btnCrear=(Button)findViewById(R.id.btn_crear);
        btnCancelar=(Button)findViewById(R.id.btn_cancelar);

        //ID de textview o plain text
        mTitulo = findViewById(R.id.txt_tit);
        mDescripcion = findViewById(R.id.txt_desc);
        mDate= findViewById(R.id.et_date_selected);
        mTime= findViewById(R.id.et_time_selected);
        mMaps = findViewById(R.id.et_ubi_selected);

        //Listener de botones
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnMapPicker.setOnClickListener(this);
        btnCrear.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
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
                                        mDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
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
                            if (minute ==0 && hourOfDay==0){
                                mTime.setText("00:00");
                            }else if (minute ==0){
                                mTime.setText(undigito(hourOfDay) +":00");
                            }else if (hourOfDay==0){
                                mTime.setText( "00:" + undigito(minute));
                            }else {
                                mTime.setText(undigito(hourOfDay) + ":" + undigito(minute));
                            }
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnMapPicker) {
            Intent intent = new Intent(Add_evento.this, All_Maps.class);
            startActivityForResult(intent,1);
        }
        if (v == btnCrear){

            String titulo = mTitulo.getText().toString();
            String fecha = mDate.getText().toString();
            String hora = mTime.getText().toString();
            String descripcion = mDescripcion.getText().toString();
            String ubicacion = mMaps.getText().toString();

            //validaciones de vacio
            if(titulo.equals("")||fecha.equals("")||hora.equals("")||descripcion.equals("")||ubicacion.equals("")) {
                validacion();
            }else{
                Evento p = new Evento();
                p.setID(UUID.randomUUID().toString());
                p.setNombre(titulo);
                p.setFecha(fecha);
                p.setHora(hora);
                p.setDescripcion(descripcion);
                p.setUbicacion(ubicacion);
                p.setLatitud(latidud);
                p.setLongitud(longitud);


                database.getReference().getRoot().child(p.getID()).setValue(p);
                Toast.makeText(this, "Agregado correctamente", Toast.LENGTH_SHORT).show();
                clear();
                //cerrar actividad
                int time_out =1000;
                new Handler().postDelayed(() -> {
                    finish();
                },time_out);
            }
        }
        if (v == btnCancelar) {
            finish();
        }
    }
    //Validacion casillas vacias
    public void validacion() {
        String titulo = mTitulo.getText().toString();
        String fecha = mDate.getText().toString();
        String hora = mTime.getText().toString();
        String descripcion = mDescripcion.getText().toString();
        String ubicacion = mMaps.getText().toString();

        if (titulo.equals("")) {
            mTitulo.setError("Requerido");
        } else if (fecha.equals("")) {
            mDate.setError("Requerido");
        } else if (hora.equals("")) {
            mTime.setError("Requerido");
        } else if (ubicacion.equals("")) {
            mMaps.setError("Requerido");
        } else if (descripcion.equals("")) {
            mDescripcion.setError("Requerido");
        }
    }

    //Funcion que verifica en el TimePicker si es de un digito
    public String undigito(int tiempo){
        String temp;
        if (tiempo<10){
            temp= "0"+ String.valueOf(tiempo);
            return temp;
        }
        return String.valueOf(tiempo);
    }
    //limpia casillas
    public void clear(){
        mTitulo.setText("");
        mTime.setText("");
        mDate.setText("");
        mDescripcion.setText("");
        mMaps.setText("");
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
    //Revisa si llego informacion (lat,longi) de la clase
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1){
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("lati");
                String result2 = data.getStringExtra("longi");

                mMaps.setText("Mi ubicacion");

                latidud=result;
                longitud=result2;
            }
            if(resultCode == RESULT_CANCELED){
                mMaps.setText("nada seleccionado");
            }
        }
    }
}
