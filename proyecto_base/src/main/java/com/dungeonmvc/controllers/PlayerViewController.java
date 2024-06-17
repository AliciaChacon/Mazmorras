package com.dungeonmvc.controllers;

import com.dungeonmvc.App;
import com.dungeonmvc.GameManager;
import com.dungeonmvc.interfaces.Observer;
import com.dungeonmvc.models.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerViewController implements Observer{
    @FXML
    ImageView portrait;
    // @FXML
    // ImageView portrait1;
    @FXML
    Label nameTag;
    // @FXML
    // Label nameTag1;
    @FXML
    Label maxHealthTag;
    @FXML
    Label currentHealthTag;
    @FXML
    Label strenghtTag;
    @FXML
    Label defenseTag;
    @FXML
    ImageView leftWeaponImg;
    @FXML
    ImageView rightWeaponImg;

    Player player;
    // Player player2;


    @FXML
    private void initialize() {
        System.out.println("Main Character controller loaded");

        player = GameManager.getInstance().getPlayer();
        player.suscribe(this);
        portrait.setImage(new Image(App.class.getResource("images/"+player.getPortrait()+".png").toExternalForm(),portrait.getFitWidth(),portrait.getFitHeight(),true,false));
        leftWeaponImg.setImage(new Image(App.class.getResource("images/"+player.getLeftHand()+".png").toExternalForm(),leftWeaponImg.getFitWidth(),leftWeaponImg.getFitHeight(),true,false));
        rightWeaponImg.setImage(new Image(App.class.getResource("images/"+player.getRightHand()+".png").toExternalForm(),rightWeaponImg.getFitWidth(),rightWeaponImg.getFitHeight(),true,false));

        onChange();
    }


    @Override
    public void onChange() {
        nameTag.setText(player.getNombre());
        // leftWeapongImg.setImage(new Image(App.class.getResource("images/"+player.getLeftHand()+".png").toExternalForm()));
        // rightWeaponImg.setImage(new Image(App.class.getResource("images/"+player.getRightHand()+".png").toExternalForm()));
    }


    @Override
    public void onChange(String... args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onChange'");
    }
}
