package com.dungeonmvc;

import java.util.ArrayList;

// import java.util.function.IntFunction;

import com.dungeonmvc.models.*;
import com.dungeonmvc.models.Board.Direction;
import com.dungeonmvc.utils.Vector2;

public class GameManager {
    private static GameManager instance;

    Player player;
    Board board;
    ArrayList<Personaje> personajes = new ArrayList<>(); 

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

    public void newTurn(Direction direction){
        board.move(player, direction);
    }

    public void testGame(){
        //player = new Player("portrait", "player", "Paladin", "item7", "item6", new Vector2(0, 0));
        player = Player.getInstancia(
                "Paladin",33,12,20,15,
                "portrait", "player", "item7", "item6", new Vector2(0, 0));
        player.getInventory().addItem("item1");
        player.getInventory().addItem("item2");
        player.getInventory().addItem("item3");
        player.getInventory().addItem("item4");
        player.getInventory().addItem("item5");
        
        Enemigo enemy = new Enemigo("Enemigo1", 10, 10, 10, 10, 10);
        Enemigo enemy2 = new Enemigo("Enemigo2", 10, 10, 10, 10, 10);
        
        personajes.add(player);
        personajes.add(enemy);
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