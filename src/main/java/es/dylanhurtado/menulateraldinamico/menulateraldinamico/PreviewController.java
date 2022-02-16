package es.dylanhurtado.menulateraldinamico.menulateraldinamico;

import es.dylanhurtado.menulateraldinamico.menulateraldinamico.model.Person;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class PreviewController implements Initializable {
    @FXML
    public VBox vboxPreview;

    @FXML
    private ImageView avatar;


    @FXML
    private Label email;

    @FXML
    private Label gender;

    @FXML
    private Label name;


    @FXML
    private StackPane preview;

    @FXML
    private Button saveButton;


    private Person person;

    TranslateTransition animation;


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void hidePreview() {
        animation = new TranslateTransition(Duration.millis(350), preview);
        animation.setFromX(0);
        animation.setToX(2000);
        animation.play();
    }

    public void setPerson(Person person) {
        this.person = person;
        this.avatar.setImage(new Image(person.getAvatar()));
        this.name.setText(person.getName());
        this.email.setText(person.getEmail());
        this.gender.setText(person.getGender());
    }
}
