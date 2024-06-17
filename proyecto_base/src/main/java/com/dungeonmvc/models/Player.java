package com.dungeonmvc.models;

import java.util.ArrayList;

import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.utils.Vector2;

public class Player extends Personaje{
    private static Player instancia;

    private ArrayList<Observer> observers;

    private String portrait;
    private String image;
    private String leftHand;
    private String rightHand;
    public Vector2 position;
    private Inventory inventory;

    private Player(String nombre,
                   int puntosDeVida,
                   int fuerza,
                   int defensa,
                   int velocidad,
                   String portrait,
                   String image,
                   String leftHand,
                   String rightHand,
                   Vector2 start) {
        super(nombre, puntosDeVida, fuerza, defensa, velocidad);
        observers = new ArrayList<>();

        this.portrait = portrait;
        this.image = image;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.position = start;
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
                                      Vector2 start) {
        if (instancia == null) {
            instancia = new Player(nombre, puntosDeVida, fuerza, defensa, velocidad, portrait, image, leftHand, rightHand, start);
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

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return this.position;
    }

    public int getX(){
        return this.position.getX();
    }

    public int getY(){
        return this.position.getY();
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        notifyObservers();
    }
}
