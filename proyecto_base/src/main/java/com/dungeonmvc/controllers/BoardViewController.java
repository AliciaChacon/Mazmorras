package com.dungeonmvc.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.dungeonmvc.App;
import com.dungeonmvc.GameManager;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Board;
import com.dungeonmvc.models.Personaje;
import com.dungeonmvc.utils.Vector2;
import com.dungeonmvc.utils.Vector2Double;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BoardViewController implements Observer{
    @FXML
    Pane pane;
    @FXML
    GridPane grid;

    private Board board;
    private double cellSize;
    private double boardSize;
 
    private ImageView playerImg;
    HashMap<Personaje, ImageView> mapaJugadorImagen;

    @FXML
    private void initialize() {
        System.out.println("Board controller loaded");
        board = GameManager.getInstance().getBoard();
        
        board.suscribe(this);
    }

    public void setUp(){
        int cellNumber = board.getSize();
        cellSize=boardSize/cellNumber;
        System.out.println(cellSize);
        for (int i = 0; i < cellNumber; i++) {
            grid.addRow(i);
            grid.addColumn(i);
        }

        for (int row = 0; row < cellNumber; row++) {
            for (int col = 0; col < cellNumber; col++) {
                ImageView boardImg = new ImageView();
                boardImg.setFitWidth(cellSize);
                boardImg.setFitHeight(cellSize);
                boardImg.setSmooth(false);
                if (board.isFloor(row, col)) {
                    boardImg.setImage(new Image(App.class.getResource("images/"+board.getFloorImage()+".png").toExternalForm(),cellSize,cellSize,true,false));
                } else {
                    boardImg.setImage(new Image(App.class.getResource("images/"+board.getWallImage()+".png").toExternalForm(),cellSize,cellSize,true,false));
                }

                grid.add(boardImg, col, row);
            }
        }


        playerImg = new ImageView();
        playerImg.setFitWidth(cellSize);
        playerImg.setFitHeight(cellSize);
        playerImg.setImage(new Image(App.class.getResource("images/"+board.getPlayer().getImage()+".png").toExternalForm(),cellSize,cellSize,true,false));
        playerImg.setSmooth(false);
        pane.getChildren().add(playerImg);
        
        
        ArrayList<ImageView> imagenesPersonajes = new ArrayList<ImageView>();
        imagenesPersonajes.add(playerImg);
        
        
        ArrayList<Personaje> personajes = GameManager.getInstance().getPersonajes();
        mapaJugadorImagen = new HashMap<>();
        ImageView enemyImg;
        
        mapaJugadorImagen.put(personajes.get(0), playerImg);
        
        for (int i = 1; i < personajes.size(); i++) {   
            enemyImg = new ImageView();
            
            enemyImg.setFitWidth(cellSize);
            enemyImg.setFitHeight(cellSize);
            enemyImg.setImage(new Image(App.class.getResource("images/"+GameManager.getInstance().getEnemy().getImage()+".png").toExternalForm(),cellSize,cellSize,true,false));
            enemyImg.setSmooth(false);
            
            enemyImg.setLayoutX(personajes.get(i).getPosition().getX());
            enemyImg.setLayoutY(personajes.get(i).getPosition().getY());
            
            pane.getChildren().add(enemyImg);
            
            mapaJugadorImagen.put(personajes.get(i), enemyImg);
        }
        onChange();
    }

    @Override
    public void onChange() {
        Vector2Double newPos = matrixToInterface(board.getPlayer().getPosition());
        System.out.println(newPos);
        playerImg.setLayoutX(newPos.getX());
        playerImg.setLayoutY(newPos.getY());

        // ArrayList<Personaje> personajes = GameManager.getInstance().getPersonajes();
        // Vector2Double newPos2;
        // for (int i = 1; i < personajes.size(); i++) {
        //     ImageView imagenAux = mapaJugadorImagen.get(personajes.get(i));
        //     if (i == 1) {
        //         newPos2 = matrixToInterface(personajes.get(i).getPosition());
        //     } else{
        //         newPos2 = matrixToInterface(personajes.get(i).getPosition());
        //     }
        //     System.out.println("aaaaaaaaaaa");
        //     System.out.println(newPos2.getX());
        //     imagenAux.setLayoutX(newPos2.getX());
        //     imagenAux.setLayoutY(newPos2.getY());
        // }
    }

    @Override
    public void onChange(String... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onChange'");
    }
    
    private Vector2Double matrixToInterface(Vector2 vector2){
        return new Vector2Double(cellSize*vector2.getX(),cellSize*vector2.getY());
    }

    public void setBoardSize(double boardSize){
        this.boardSize=boardSize;
    }
}