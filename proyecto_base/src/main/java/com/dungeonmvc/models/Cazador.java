package com.dungeonmvc.models;

import java.util.Random;

import com.dungeonmvc.GameManager;
import com.dungeonmvc.utils.Vector2;

public class Cazador extends Personaje {
    private float percepcion;

    public Cazador(String nombre, int puntosDeVida, int fuerza, int defensa, int velocidad, float percepcion,  String image, Vector2 position) {
        super(nombre, puntosDeVida, fuerza, defensa, velocidad,image, position);

        this.percepcion = percepcion;
    }

    public float getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(float percepcion) {
        this.percepcion = percepcion;
    }

    public void move(Board board) {
        Enemigo enemigo = GameManager.getInstance().getEnemy2(); // Obtener enemigo
        Vector2 posicionEnemigo = enemigo.getPosition(); // Posición del enemigo
        Vector2 posicionCazador = super.getPosition(); // Posición del cazador

        double distancia = posicionCazador.distance(posicionEnemigo); // Distancia del cazador al enemigo

        if (distancia <= percepcion) {
            // Mover hacia el enemigo
            Vector2 miPosicion = posicionCazador;
            double distanciaMin = distancia;

            for (Board.Direction dir : Board.Direction.values()) {
                Vector2 nuevaPosicion = board.getDestination(posicionCazador, dir);
                double nuevaDistancia = nuevaPosicion.distance(posicionEnemigo);

                // Comprobar si esta fuera de rango
                if (nuevaPosicion.getX() >= 0 && nuevaPosicion.getX() < board.getSize() && nuevaPosicion.getY() >= 0 && nuevaPosicion.getY() < board.getSize()) {
                    if (nuevaDistancia < distanciaMin && board.isFloor(nuevaPosicion)) {
                        miPosicion = nuevaPosicion;
                        distanciaMin = nuevaDistancia;
                    }
                }
            }
            
            super.setPosition(miPosicion);
        } else {
            // Mover aleatorio
            Random numRandom = new Random();
            Board.Direction[] directions = Board.Direction.values();
            Board.Direction direccionRandom = directions[numRandom.nextInt(directions.length)];
            Vector2 nuevaPosicion = board.getDestination(posicionEnemigo, direccionRandom);

            if (nuevaPosicion.getX() >= 0 && nuevaPosicion.getX() < board.getSize() && nuevaPosicion.getY() >= 0 && nuevaPosicion.getY() < board.getSize()) {
                if (board.isFloor(nuevaPosicion)) {
                    super.setPosition(nuevaPosicion);
                }
            }
        }

        board.notifyObservers();
    }
}