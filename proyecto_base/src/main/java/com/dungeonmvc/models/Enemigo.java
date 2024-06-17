package com.dungeonmvc.models;

import com.dungeonmvc.utils.Vector2;

public class Enemigo extends Personaje{
    private float percepcion;

    public Enemigo(String nombre, int puntosDeVida, int fuerza, int defensa, int velocidad, float percepcion,  String image, Vector2 position) {
        super(nombre, puntosDeVida, fuerza, defensa, velocidad,image, position);

        this.percepcion = percepcion;
    }

    public float getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(float percepcion) {
        this.percepcion = percepcion;
    }
}
