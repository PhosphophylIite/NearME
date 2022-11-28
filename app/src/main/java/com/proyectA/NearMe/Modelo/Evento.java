package com.proyectA.NearMe.Modelo;

public class Evento {
    /*
    private String Nombre, Ubicacion, Asistentes;

    public Evento() {
    }

    public Evento(String nombre, String ubicacion, String asistentes) {
        Nombre = nombre;
        Ubicacion = ubicacion;
        Asistentes = asistentes;
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
    */
    private Long Nombre, Ubicacion;
    private String Asistentes;

    public Long getNombre() {
        return Nombre;
    }

    public void setNombre(Long nombre) {
        Nombre = nombre;
    }

    public Long getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(Long ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getAsistentes() {
        return Asistentes;
    }

    public void setAsistentes(String asistentes) {
        Asistentes = asistentes;
    }

    public Evento() {
    }

    public Evento(Long nombre, Long ubicacion, String asistentes) {
        Nombre = nombre;
        Ubicacion = ubicacion;
        Asistentes = asistentes;
    }
}
