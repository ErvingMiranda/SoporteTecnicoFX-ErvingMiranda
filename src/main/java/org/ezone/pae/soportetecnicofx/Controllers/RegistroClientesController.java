package org.ezone.pae.soportetecnicofx.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.ezone.pae.soportetecnicofx.Models.Cliente;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class RegistroClientesController {

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TextField txtCorreoCliente;

    @FXML
    private TextField txtTelefonoCliente;

    @FXML
    private ComboBox<String> cmbTipoCliente;

    @FXML
    private TextField txtDocumentoIdentidad;

    @FXML
    private TextField txtDirectorioCliente;

    @FXML
    private Button btnSeleccionarDocumento;

    @FXML
    private Button btnSeleccionarDirectorio;

    @FXML
    private Button btnCerrar;

    @FXML
    private void initialize() {
        cmbTipoCliente.setItems(FXCollections.observableArrayList(
                "Individual",
                "Empresa",
                "Institucional"
        ));
    }

    @FXML
    private void seleccionarArchivo() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar Documento");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        File f = fc.showOpenDialog(btnSeleccionarDocumento.getScene().getWindow());

        if (f != null) {
            txtDocumentoIdentidad.setText(f.getAbsolutePath());
        }
    }

    @FXML
    private void seleccionarDirectorio() {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccionar Directorio");
        dc.setInitialDirectory(new File("C:\\"));

        File f = dc.showDialog(btnSeleccionarDirectorio.getScene().getWindow());

        if (f != null) {
            txtDirectorioCliente.setText(f.getAbsolutePath());
        }
    }

    @FXML
    private void guardarCliente() {
        if (!validarFormulario()) {
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación");
        confirmacion.setHeaderText("¿Seguro que desea guardar el cliente?");
        confirmacion.setContentText("Cliente: " + txtNombreCliente.getText());

        Optional<ButtonType> respuesta = confirmacion.showAndWait();

        if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Cliente guardado",
                    "Cliente guardado correctamente."
            );

            limpiarFormulario();
        }
    }

    @FXML
    private void abrirSolicitudServicio() {
        if (!validarFormulario()) {
            return;
        }

        Cliente cliente = construirCliente();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("/fxml/SolicitudServicios.fxml"));

            Parent root = loader.load();
            SolicitudServiciosController controller = loader.getController();
            controller.recibirCliente(cliente);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "No fue posible abrir el formulario. \n" + e.getMessage()
            );
        }
    }

    @FXML
    private void limpiarContenido() {
        limpiarFormulario();
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    private boolean validarFormulario() {
        if (txtNombreCliente.getText().isEmpty() || txtCorreoCliente.getText().isEmpty() || txtTelefonoCliente.getText().isEmpty() || cmbTipoCliente.getValue() == null || txtDocumentoIdentidad.getText().isEmpty() || txtDirectorioCliente.getText().isEmpty()) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Advertencia",
                    "Ningún campo debe quedar vacío."
            );

            return false;
        }

        return true;
    }

    private Cliente construirCliente() {
        return new Cliente(
                txtNombreCliente.getText().trim(),
                txtCorreoCliente.getText().trim(),
                txtTelefonoCliente.getText().trim(),
                cmbTipoCliente.getValue().trim(),
                txtDocumentoIdentidad.getText().trim(),
                txtDirectorioCliente.getText().trim()
        );
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarFormulario() {
        txtNombreCliente.clear();
        txtCorreoCliente.clear();
        txtTelefonoCliente.clear();
        cmbTipoCliente.setValue(null);
        txtDocumentoIdentidad.clear();
        txtDirectorioCliente.clear();
    }
}