package es.dylanhurtado.menulateraldinamico.menulateraldinamico;

import es.dylanhurtado.menulateraldinamico.menulateraldinamico.model.Person;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;
import java.util.UUID;

public class Controller implements Initializable {
    @FXML
    private StackPane appPane;

    @FXML
    private PreviewController previewController;

    @FXML
    private StackPane preview;

    @FXML
    private PieChart pieChartGender;

    @FXML
    private StackPane preferences;

    @FXML
    private VBox vtoolbar;

    @FXML
    private ListView<Person> listView;

    private boolean menuIsVisible;
    private TranslateTransition menuAnimation, preferencesAnimation, previewAnimation;
    private ObservableList<Person> people;
    private ObservableList<PieChart.Data> datosGraficoCircular;
    private int contFemales;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        people = FXCollections.observableArrayList();
        vtoolbar.setTranslateX(-200);
        preferences.setTranslateY(600);
        preview.setTranslateX(2000);
        menuIsVisible = false;
        loadData();
    }

    @FXML
    private void playAnimationMenu() {
        menuAnimation = new TranslateTransition(Duration.millis(250), vtoolbar);
        preferencesAnimation = new TranslateTransition(Duration.millis(250), preferences);
        previewAnimation = new TranslateTransition(Duration.millis(250), preview);

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

    @FXML
    private void loadData() {
        contFemales = 0;
        Runnable task = () -> {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://randomuser.me/api/?results=50"))
                    .build();

            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                Platform.runLater(() -> listView.getItems().removeAll(people));
                //Tendria que mapear el results pero con el substring me lo ahorro
                JSONArray dataArray = new JSONArray(response.body().substring(11, response.body().length() - 1));
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject row = dataArray.getJSONObject(i);
                    if (row.getString("gender").equalsIgnoreCase("female")) {
                        contFemales++;
                    }
                    Platform.runLater(() -> people.add(new Person(UUID.randomUUID(),
                            row.getJSONObject("picture").getString("large"),
                            row.getJSONObject("name").getString("first") + " " + row.getJSONObject("name").getString("last"),
                            row.getString("email"),
                            row.getString("gender"))));
                }
                Platform.runLater(() -> {
                    setListView();
                    initGraphic();
                });
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(task).start();

    }

    private void setListView() {
        listView.setItems(people);
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                playPreview();
                previewController.setPerson(listView.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void initGraphic() {
        datosGraficoCircular = FXCollections.observableArrayList(
                new PieChart.Data("Female", contFemales * 100 / 50),
                new PieChart.Data("Male", (50f - contFemales) * 100 / 50));
        pieChartGender.setData(datosGraficoCircular);
        pieChartGender.setClockwise(false);
    }

    private void playAnimationPreferences() {
        preferencesAnimation = new TranslateTransition(Duration.millis(250), preferences);

        preferencesAnimation.setFromY(600);
        preferencesAnimation.setToY(0);

        menuAnimation.play();
        preferencesAnimation.play();
    }

    @FXML
    private void playPreview() {
        previewAnimation = new TranslateTransition(Duration.millis(350), preview);

        previewAnimation.setFromX(2000);
        previewAnimation.setToX(0);

        previewAnimation.play();
    }


    @FXML
    protected void shop() {
        appPane.setBackground(new Background(
                new BackgroundFill(
                        Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
        playAnimationMenu();
    }

    @FXML
    protected void games() {
        appPane.setBackground(new Background(
                new BackgroundFill(
                        Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        playAnimationMenu();
    }

    @FXML
    protected void photos() {
        appPane.setBackground(new Background(
                new BackgroundFill(
                        Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
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