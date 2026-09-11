package org.ezone.pae.soportetecnicofx.Applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SolicitudServiciosApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SolicitudServiciosApplication.class.getResource("/org/ezone/pae/soportetecnicofx/solicitud-servicios.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Solicitud de Servicio");
        stage.setScene(scene);
        stage.show();
    }
}