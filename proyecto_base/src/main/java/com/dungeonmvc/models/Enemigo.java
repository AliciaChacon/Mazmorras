package com.dungeonmvc.models;

public class Enemigo extends Personaje{
    private float percepcion;

    public Enemigo(String nombre, int puntosDeVida, int fuerza, int defensa, int velocidad, float percepcion) {
        super(nombre, puntosDeVida, fuerza, defensa, velocidad);

        this.percepcion = percepcion;
    }

    public float getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(float percepcion) {
        this.percepcion = percepcion;
    }
}
