package com.proyectA.NearMe.Modelo;

public class Evento {

    //private Long Ubicacion;
    private String ID, Nombre, Asistentes, Ubicacion, Hora, Fecha;

    public Evento(String ID, String nombre, String asistentes, String ubicacion) {
        this.ID = ID;
        Nombre = nombre;
        Asistentes = asistentes;
        Ubicacion = ubicacion;
    }

    public Evento() {
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getAsistentes() {
        return Asistentes;
    }

    public void setAsistentes(String asistentes) {
        Asistentes = asistentes;
    }

}
