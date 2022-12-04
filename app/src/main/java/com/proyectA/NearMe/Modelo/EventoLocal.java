package com.proyectA.NearMe.Modelo;

import android.provider.BaseColumns;

//ESTA CLASE ES UN ESQUEMA DE LA TABLA EN LA BASE DE DATOS LOCAL
public class EventoLocal {
    //private Long Ubicacion;
    public static abstract class EventosLocales implements BaseColumns {

        public static final String TABLE_NAME ="Eventos";

        public static final String ID = "id";
        public static final String TITULO = "Titulo";
        public static final String FECHA = "Fecha";
        public static final String HORA = "Hora";
        public static final String UBICACION = "Ubicacion";
        public static final String DESCRIPCION = "Descripcion";
    }
}
