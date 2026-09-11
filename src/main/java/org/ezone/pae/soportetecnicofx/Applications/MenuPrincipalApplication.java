package org.ezone.pae.soportetecnicofx.Applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuPrincipalApplication.class.getResource("/org/ezone/pae/soportetecnicofx/menu-principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Soporte Técnico");
        stage.setScene(scene);
        stage.show();
    }
}