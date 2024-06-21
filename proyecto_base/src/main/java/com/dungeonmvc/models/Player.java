package com.dungeonmvc.models;

import java.util.ArrayList;

import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.utils.Vector2;

public class Player extends Personaje {
    private static Player instancia;

    private ArrayList<Observer> observers;

    private String portrait;
    private String leftHand;
    private String rightHand;
    private Inventory inventory;

    private Player(String nombre,
                   int vida,
                   int fuerza,
                   int defensa,
                   int velocidad,
                   String portrait,
                   String image,
                   String leftHand,
                   String rightHand,
                   Vector2 start) {
        super(nombre, vida, fuerza, defensa, velocidad, image, start);
        observers = new ArrayList<>();

        this.portrait = portrait;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.inventory = new Inventory();
    }

    public static Player getInstancia(String nombre,
                                      int puntosDeVida,
                                      int fuerza,
                                      int defensa,
                                      int velocidad,
                                      String portrait,
                                      String image,
                                      String leftHand,
                                      String rightHand,
                                      Vector2 juanito) {
        if (instancia == null) {
            instancia = new Player(nombre, puntosDeVida, fuerza, defensa, velocidad, portrait, image, leftHand, rightHand, juanito);
        }
        return instancia;
    }

    public void suscribe(Observer observer){
        observers.add(observer);
    }

    public void unsuscribe(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        observers.forEach(x -> x.onChange());
    }

    public String getPortrait() {
        return this.portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
        notifyObservers();
    }


    public String getLeftHand() {
        return this.leftHand;
    }

    public void setLeftHand(String leftHand) {
        this.leftHand = leftHand;
        notifyObservers();
    }

    public String getRightHand() {
        return this.rightHand;
    }

    public void setRightHand(String rightHand) {
        this.rightHand = rightHand;
        notifyObservers();
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public Vector2 getPosition() {
        return super.getPosition();
    }

    public int getX(){
        return super.getPosition().getX();
    }

    public int getY(){
        return super.getPosition().getY();
    }

    public void setPosition(Vector2 position) {
        super.setPosition(position);
        notifyObservers();
    }

    public void move(Board board, Board.Direction direction) {
        Vector2 destino = board.getDestination(this.getPosition(), direction);
        if (destino.getX() >= 0 && destino.getX() < board.getSize() && destino.getY() >= 0 && destino.getY() < board.getSize()) {
            if (board.isFloor(destino)) {
                this.setPosition(destino);
                board.notifyObservers();
            }
        }
    }
}
