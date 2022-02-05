package es.dylanhurtado.menulateraldinamico.menulateraldinamico;


import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PreferencesController {


    @FXML
    private StackPane stackPanePreferences;

    private TranslateTransition animation;
    private String lang="es";
    private Properties properties;

    public PreferencesController() {
        properties= new Properties();
    }

    @FXML
    private void saveButton() throws IOException {
        properties.load(new FileInputStream("src"+File.separator+"main"+File.separator+"resources"+File.separator+"application.properties"));
        properties.setProperty("language",lang);
        FileOutputStream os = new FileOutputStream("src"+File.separator+"main"+File.separator+"resources"+File.separator+"application.properties");
        properties.store(os,"language = "+lang);
        hidePreferences();
    }
    @FXML
    private void spanishOnClick(){
        showAlertInfoRestartToSave();
        lang = "es";
    }
    @FXML
    private void englishOnClick(){
        showAlertInfoRestartToSave();
        lang = "en";
    }

    private void showAlertInfoRestartToSave(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Changing the language");
        alert.setHeaderText("Press on save and restart to save changes");
        alert.show();
    }

    private void hidePreferences() {
        animation = new TranslateTransition(Duration.millis(250), stackPanePreferences);

        animation.setFromY(0);
        animation.setToY(600);
        animation.play();
    }

}