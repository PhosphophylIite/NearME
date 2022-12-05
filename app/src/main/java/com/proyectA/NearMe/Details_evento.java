package com.proyectA.NearMe;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.proyectA.NearMe.Controlador.EventoDBHelper;
import com.proyectA.NearMe.Modelo.EventoLocal;

import org.w3c.dom.Text;

public class Details_evento extends AppCompatActivity {

    public Details_evento(){
    }

    String Titulos = "Titulo no encontrado";
    String Fechas = "Fecha no encontrada";
    String Horas = "Hora no encontrada";
    String Descripciones = "Descripcion no encontrada";
    String Ubicaciones = "Ubicacion no encontrada";

    Button btnUnirse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmento_evento);
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

        btnUnirse=(Button)findViewById(R.id.btn_unirse);
        btnUnirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UnirseEvento();
            }
        });
        //ABRIR EL MAPA CUANDO SE PRESIONA LA UBICACION
        /*
            Falta implementar que recupere el marcador del evento clickeado.
         */
        tv_ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details_evento.this,All_Maps.class);
                startActivity(intent);
            }
        });

    }

    public void UnirseEvento(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Titulos = extras.getString("Titulos");
            Fechas = extras.getString("Fechas");
            Horas = extras.getString("Horas");
            Descripciones = extras.getString("Descripciones");
            Ubicaciones = extras.getString("Ubicaciones");
        }

        try {
            EventoDBHelper dbHelper = new EventoDBHelper(Details_evento.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            // Contenedor de valores
            ContentValues values = new ContentValues();
            // Pares clave-valor
            values.put(EventoLocal.EventosLocales.ID, "001");
            values.put(EventoLocal.EventosLocales.TITULO, Titulos);
            values.put(EventoLocal.EventosLocales.FECHA, Fechas);
            values.put(EventoLocal.EventosLocales.HORA, Horas);
            values.put(EventoLocal.EventosLocales.UBICACION, Ubicaciones);
            values.put(EventoLocal.EventosLocales.DESCRIPCION, Descripciones);

            // Insertar...
            db.insert(EventoLocal.EventosLocales.TABLE_NAME, null, values);
            Toast toast2 = Toast.makeText(Details_evento.this, "Te has unido al evento", Toast.LENGTH_SHORT);
            toast2.show();
        }catch (Exception ex){
            ex.toString();
        }

    }


}
