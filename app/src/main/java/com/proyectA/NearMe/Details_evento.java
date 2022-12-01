package com.proyectA.NearMe;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Details_evento extends AppCompatActivity {

    public Details_evento(){
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmento_evento);
        TextView tv_titulo = findViewById(R.id.tv_tit);
        TextView tv_hora = findViewById(R.id.tv_time_select);
        TextView tv_fecha = findViewById(R.id.tv_date_select);


        String Titulos = "Titulo no encontrado";
        String Fechas = "Fecha no encontrada";
        String Horas = "Hora no encontrada";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Titulos = extras.getString("Titulos");
            Fechas = extras.getString("Fechas");
            Horas = extras.getString("Horas");

        }

        tv_titulo.setText(Titulos);
        tv_fecha.setText(Fechas);
        tv_hora.setText(Horas);


    }
}
