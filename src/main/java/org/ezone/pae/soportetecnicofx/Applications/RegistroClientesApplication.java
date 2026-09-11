package org.ezone.pae.soportetecnicofx.Applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroClientesApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistroClientesApplication.class.getResource("/org/ezone/pae/soportetecnicofx/registro-clientes.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Registro de Clientes");
        stage.setScene(scene);
        stage.show();
    }
}