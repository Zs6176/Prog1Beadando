package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ModeValaszto {

    @FXML
    private Button btwTwoPlayer;

    @FXML
    private Button btwGep;


    
    public void btgepclick(ActionEvent actionEvent)  throws IOException{
        KeszulesControl.playerszam=1;
        KeszulesControl.gepElen=1;
        Palya.gepElen=true;
        App.setRoot("Fokozat");

    }

    public void btwTwoPlayerClick(ActionEvent actionEvent) throws IOException {
        KeszulesControl.penz=1000;
        KeszulesControl.playerszam=0;
        Palya.gepElen=false;
        App.setRoot("KeszulesPlayerOne");

    }
}
