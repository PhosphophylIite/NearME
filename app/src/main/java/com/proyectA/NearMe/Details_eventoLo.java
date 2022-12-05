package com.proyectA.NearMe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.proyectA.NearMe.Controlador.EventoDBHelper;
import com.proyectA.NearMe.Modelo.EventoLocal;

public class Details_eventoLo extends AppCompatActivity {

    public Details_eventoLo(){
    }

    String Titulos = "Titulo no encontrado";
    String Fechas = "Fecha no encontrada";
    String Horas = "Hora no encontrada";
    String Descripciones = "Descripcion no encontrada";
    String Ubicaciones = "Ubicacion no encontrada";
    String IDs = "ID no encontrada";

    Button btnSalirse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_evento_lo);
        TextView tv_titulo = findViewById(R.id.tv_tit);
        TextView tv_hora = findViewById(R.id.tv_time_select);
        TextView tv_fecha = findViewById(R.id.tv_date_select);
        TextView tv_ubicacion = findViewById(R.id.tv_ubi);
        TextView tv_descripcion = findViewById(R.id.tv_decripcion);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Titulos = extras.getString("Titulos");
            Fechas = extras.getString("Fechas");
            Horas = extras.getString("Horas");
            Descripciones = extras.getString("Descripciones");
            Ubicaciones = extras.getString("Ubicaciones");
        }

        tv_titulo.setText(Titulos);
        tv_fecha.setText(Fechas);
        tv_hora.setText(Horas);
        tv_ubicacion.setText(Ubicaciones);
        tv_descripcion.setText(Descripciones);

        btnSalirse=(Button)findViewById(R.id.btn_unirse);
        btnSalirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalirseEvento();
            }
        });
        //ABRIR EL MAPA CUANDO SE PRESIONA LA UBICACION
        /*
            Falta implementar que recupere el marcador del evento clickeado.
         */
        tv_ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details_eventoLo.this,All_Maps.class);
                startActivity(intent);
            }
        });

    }
    //Salirse evento
    public void SalirseEvento(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Titulos = extras.getString("Titulos");
            Fechas = extras.getString("Fechas");
            Horas = extras.getString("Horas");
            Descripciones = extras.getString("Descripciones");
            Ubicaciones = extras.getString("Ubicaciones");
            IDs = extras.getString("ID");
        }

        try {
            EventoDBHelper dbHelper = new EventoDBHelper(Details_eventoLo.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("DELETE FROM " + EventoLocal.EventosLocales.TABLE_NAME + " WHERE id = '" + IDs + "'");
            db.close();
            Toast.makeText(this,"Ya no estas en este evento",Toast.LENGTH_SHORT).show();

            //cerrar actividad
            int time_out =1000;
            new Handler().postDelayed(() -> {
                finish();
            },time_out);

        }catch (Exception ex){
            ex.toString();
        }

    }
}