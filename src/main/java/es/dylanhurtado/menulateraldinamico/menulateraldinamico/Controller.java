package es.dylanhurtado.menulateraldinamico.menulateraldinamico;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private StackPane appPane;

    @FXML
    private StackPane previewView;

    @FXML
    private StackPane preferencesView;


    @FXML
    private VBox vtoolbar;

    private boolean menuIsVisible;
    private TranslateTransition menuAnimation, preferencesAnimation, previewAnimation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vtoolbar.setTranslateX(-200);
        preferencesView.setTranslateY(600);
        previewView.setTranslateX(2000);
        menuIsVisible = false;
    }

    @FXML
    private void playAnimationMenu() {
        menuAnimation = new TranslateTransition(Duration.millis(250), vtoolbar);
        preferencesAnimation = new TranslateTransition(Duration.millis(250), preferencesView);
        previewAnimation = new TranslateTransition(Duration.millis(250), previewView);

        if (!menuIsVisible) {
            menuAnimation.setFromX(-vtoolbar.getWidth());
            menuAnimation.setToX(0);
            menuIsVisible = true;

        } else {
            menuAnimation.setFromX(0);
            menuAnimation.setToX(-vtoolbar.getWidth());
            menuIsVisible = false;
        }
        menuAnimation.play();
        preferencesAnimation.play();
    }

    private void playAnimationPreferences() {
        preferencesAnimation = new TranslateTransition(Duration.millis(250), preferencesView);

        preferencesAnimation.setFromY(600);
        preferencesAnimation.setToY(0);

        menuAnimation.play();
        preferencesAnimation.play();
    }

    @FXML
    private void playPreview() {
        previewAnimation = new TranslateTransition(Duration.millis(350), previewView);

        previewAnimation.setFromX(2000);
        previewAnimation.setToX(0);

        previewAnimation.play();
    }


    @FXML
    protected void shop() {
        appPane.setBackground(new Background(
                new BackgroundFill(
                        Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        playAnimationMenu();
    }

    @FXML
    protected void games() {
        appPane.setBackground(new Background(
                new BackgroundFill(
                        Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        playAnimationMenu();
    }

    @FXML
    protected void photos() {
        appPane.setBackground(new Background(
                new BackgroundFill(
                        Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        playAnimationMenu();
    }

    @FXML
    protected void preferences() {
        playAnimationPreferences();
        //Para cerrar el menu cuando abres preferences
        playAnimationMenu();
    }

    @FXML
    protected void exit() {
        playAnimationMenu();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText("This app will miss you");
        alert.setContentText("Are you sure?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            System.exit(0);
        }

    }

}