package com.dungeonmvc.models;

import com.dungeonmvc.GameManager;
import com.dungeonmvc.utils.Vector2;

import java.util.Random;

public class Enemigo extends Personaje{
    private float percepcion; // Rango de visión del enemigo

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

    public void move(Board board) {
        Player player = GameManager.getInstance().getPlayer(); // Obtener jugador
        Vector2 posicionJugador = player.getPosition(); // Posición del jugador
        Vector2 posicionEnemigo = super.getPosition(); // Posición del enemigo

        double distancia = posicionEnemigo.distance(posicionJugador); // Distancia del enemigo al jugador

        if (distancia <= percepcion) {
            // Mover hacia el jugador
            Vector2 miPosicion = posicionEnemigo;
            double distanciaMin = distancia;

            for (Board.Direction dir : Board.Direction.values()) {
                Vector2 nuevaPosicion = board.getDestination(posicionEnemigo, dir);
                double nuevaDistancia = nuevaPosicion.distance(posicionJugador);

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
