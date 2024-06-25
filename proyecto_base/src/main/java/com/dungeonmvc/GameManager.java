package com.dungeonmvc;

import java.util.ArrayList;
import java.util.Collections;

// import java.util.function.IntFunction;

import com.dungeonmvc.models.*;
import com.dungeonmvc.models.Board.Direction;
import com.dungeonmvc.utils.Vector2;

public class GameManager {
    private static GameManager instance;

    Player player;
    Board board;
    ArrayList<Personaje> personajes = new ArrayList<>();
    Enemigo enemy,enemy2;
    Cazador cazador;
    private GameManager(){

    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Board getBoard() {
        return this.board;
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public Enemigo getEnemy() {
        return enemy;
    }
    public void setEnemy(Enemigo enemy) {
        this.enemy = enemy;
    }

    public Enemigo getEnemy2() {
        return enemy2;
    }
    public void setEnemy2(Enemigo enemy2) {
        this.enemy2 = enemy2;
    }

    public Cazador getCazador(){
        return cazador;
    }
    public void setCazador(Cazador cazador){
        this.cazador=cazador;
    }
    

    public void newTurn(Board.Direction direction) {
        player.move(board,direction);
        Collections.sort(personajes); // Ordenar personajes por velocidad

        for (Personaje personaje : personajes) {
            if (personaje instanceof Enemigo) {
                ((Enemigo) personaje).move(board);
            }
        }
        board.notifyObservers();
    }


    public void testGame(){
        player = Player.getInstancia("Paladin1",33,12,20,15, "portrait", "player", "item7", "item6", new Vector2(0, 0));
        player.getInventory().addItem("item1");
        player.getInventory().addItem("item2");
        player.getInventory().addItem("item3");
        player.getInventory().addItem("item4");
        player.getInventory().addItem("item5");

        enemy = new Enemigo("Enemigo1", 10, 10, 10, 12, 10, "enemigo", new Vector2(0, 10));

        personajes.add(player);
        personajes.add(enemy);

        enemy2 = new Enemigo("Enemigo2", 10, 10, 10, 10, 10, "enemigo", new Vector2(15, 0));

        personajes.add(enemy2);

        boolean[][] boardMatrix = {
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, true, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, false, true, false, true, true, true, true, true, true, false, true, true},
            {true, false, true, false, true, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, false, true, false, true, true, true, true, true, true, false, true, true},
            {true, false, false, false, true, false, false, false, false, false, false, false, false, false, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, true, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, false, true, false, true, true, true, true, true, true, false, true, true},
            {true, false, true, false, true, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, false, true, false, true, true, true, true, true, true, false, true, true},
            {true, false, false, false, true, false, false, false, false, false, false, false, false, false, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}
        };
        board = new Board(boardMatrix.length,"floor","wall");
        for (int i = 0; i < boardMatrix.length; i++) {
            for (int j=0;j < boardMatrix[0].length;j++){
                board.newCell(new Vector2(i, j), boardMatrix[j][i]);
            }
        }
    }
}
