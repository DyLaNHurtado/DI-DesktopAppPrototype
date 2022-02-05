package es.dylanhurtado.menulateraldinamico.menulateraldinamico;

import es.dylanhurtado.menulateraldinamico.menulateraldinamico.model.Person;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class PreviewController implements Initializable {
    @FXML
    private ImageView avatar;

    @FXML
    private Label email;

    @FXML
    private Label gender;

    @FXML
    private Label name;


    @FXML
    private StackPane previewPane;


    private Person person;

    TranslateTransition animation;


    @FXML
    private void hidePreview() {
        animation = new TranslateTransition(Duration.millis(350), previewPane);
        animation.setFromX(0);
        animation.setToX(2000);
        animation.play();
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        person = new Person(1L,"Dylan","dylancorreo@correo.com","Male");
        this.name.setText(person.getName());
        this.email.setText(person.getEmail());
        this.gender.setText(person.getGender());
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public void updatePersonLabels(){
        name.setText(person.getName());
        email.setText(person.getEmail());
        gender.setText(person.getGender());
    }

    public Person getPerson() {
        return person;
    }
}
