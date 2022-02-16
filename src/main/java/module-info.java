module es.dylanhurtado.menulateraldinamico.menulateraldinamico {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;


    opens es.dylanhurtado.menulateraldinamico.menulateraldinamico to javafx.fxml;
    exports es.dylanhurtado.menulateraldinamico.menulateraldinamico;
}