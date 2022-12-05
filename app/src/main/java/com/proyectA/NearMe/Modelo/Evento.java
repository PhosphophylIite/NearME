package com.proyectA.NearMe.Modelo;

public class Evento {

    //private Long Ubicacion;
    private String ID, Nombre, Asistentes, Ubicacion, Hora, Fecha, Descripcion, Latitud, Longitud;

    public Evento(String ID, String nombre, String asistentes, String ubicacion, String hora, String fecha, String descripcion, String latitud, String longitud) {
        this.ID = ID;
        Nombre = nombre;
        Asistentes = asistentes;
        Ubicacion = ubicacion;
        Hora = hora;
        Fecha = fecha;
        Descripcion = descripcion;
        Latitud = latitud;
        Longitud = longitud;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public Evento() {
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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
