package com.isdcm.streamingapp.models;

import java.util.Date;

public class Video {

    private String title;
    private String autor;
    private Date fechaCreacion;
    private int duracion;
    private int numReproducciones;
    private String descripcion;
    private String formato;
    private String url;


    public Video(String title, String autor, Date fechaCreacion, int duracion, int numReproducciones, String descripcion, String formato, String url) {
        this.title = title;
        this.autor = autor;
        this.fechaCreacion = fechaCreacion;
        this.duracion = duracion;
        this.numReproducciones = numReproducciones;
        this.descripcion = descripcion;
        this.formato = formato;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getNumReproducciones() {
        return numReproducciones;
    }

    public void setNumReproducciones(int numReproducciones) {
        this.numReproducciones = numReproducciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
