package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class Fokozat {
    public Button btKonyu;
    public Button btKozepes;
    public Button btNehez;

    private KeszulesControl keszulesControl = new KeszulesControl();

    public void btKonnyu(ActionEvent actionEvent) throws IOException {
        KeszulesControl.penz=1300;
        App.setRoot("Keszules");
    }

    public void btKozepes(ActionEvent actionEvent) throws IOException {
        KeszulesControl.penz=1000;
        App.setRoot("Keszules");
    }

    public void btNehez(ActionEvent actionEvent) throws IOException {
        KeszulesControl.penz=700;
        App.setRoot("Keszules");
    }
}
