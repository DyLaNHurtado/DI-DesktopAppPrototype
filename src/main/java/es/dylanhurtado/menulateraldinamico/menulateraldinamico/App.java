package es.dylanhurtado.menulateraldinamico.menulateraldinamico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale locale;
        Properties properties = new Properties();
        properties.load(new FileInputStream("src"+File.separator+"main"+File.separator+"resources"+File.separator+"application.properties"));
        if(properties.get("language").equals("es")){
            locale = new Locale("es", "ES");
        }else{
            locale = new Locale("en", "US");
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/strings", locale);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu-lateral.fxml"), resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Desktop App Prototype");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}