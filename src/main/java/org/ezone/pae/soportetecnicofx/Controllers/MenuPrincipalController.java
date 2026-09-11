package org.ezone.pae.soportetecnicofx.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalController {

    @FXML
    private void abrirRegistroClientes() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuPrincipalController.class.getResource("/org/ezone/pae/soportetecnicofx/registro-clientes.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("Registro de Clientes");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void abrirSolicitudServicio() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuPrincipalController.class.getResource("/org/ezone/pae/soportetecnicofx/solicitud-servicios.fxml"));

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("Solicitud de Servicio");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void salir() {
        Platform.exit();
    }
}