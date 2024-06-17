package com.dungeonmvc.models;

import com.dungeonmvc.utils.Vector2;

public class Personaje {
    protected String nombre;
    protected int puntosDeVida;
    protected int fuerza;
    protected int defensa;
    protected int velocidad;
    
    private String image;
    protected Vector2 position;

    public Personaje(String nombre, int puntosDeVida, int fuerza, int defensa, int velocidad, String image, Vector2 position) {
        this.nombre = nombre;
        this.puntosDeVida = puntosDeVida;
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.image = image;
        this.position = position;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosDeVida() {
        return puntosDeVida;
    }

    public void setPuntosDeVida(int puntosDeVida) {
        this.puntosDeVida = puntosDeVida;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    
}
