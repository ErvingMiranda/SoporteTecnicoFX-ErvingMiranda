package org.ezone.pae.soportetecnicofx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
    private TextField txtDocumentoIdentificacion;

    @FXML
    private TextField txtDirectorioCliente;

    @FXML
    private Button btnGuardarCliente;

    @FXML
    private Button btnCrearSolicitud;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnSeleccionarDocumento;

    @FXML
    private Button btnSeleccionarDirectorio;

    @FXML
    private Button btnCerrar;

    @FXML
    private void initialize() {
        cmbTipoCliente.getItems().addAll("Particular", "Empresa");
    }

    @FXML
    private void seleccionarArchivo() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar documento de identificación");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivo TXT", "*.txt"),
                new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Archivo Excel", "*.xls"),
                new FileChooser.ExtensionFilter("Archivo Word", "*.docx"),
                new FileChooser.ExtensionFilter("Archivo Markdown", "*.md")
        );

        File f = fc.showOpenDialog(btnSeleccionarDocumento.getScene().getWindow());

        if (f != null) {
            txtDocumentoIdentificacion.setText(f.getAbsolutePath());
        }
    }

    @FXML
    private void seleccionarDirectorio() {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccionar directorio del cliente");
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
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Cliente guardado");
            alerta.setHeaderText("Cliente guardado");
            alerta.setContentText("Cliente guardado correctamente.");
            alerta.showAndWait();

            limpiarFormulario();
        }
    }

    @FXML
    private void crearSolicitud() {

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
        if (txtNombreCliente.getText().isEmpty() || txtCorreoCliente.getText().isEmpty() || txtTelefonoCliente.getText().isEmpty() || cmbTipoCliente.getValue() == null || txtDocumentoIdentificacion.getText().isEmpty() || txtDirectorioCliente.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText(null);
            alerta.setContentText("Ningún campo debe quedar vacío.");
            alerta.showAndWait();

            return false;
        }

        return true;
    }

    private void limpiarFormulario() {
        txtNombreCliente.clear();
        txtCorreoCliente.clear();
        txtTelefonoCliente.clear();
        cmbTipoCliente.setValue(null);
        txtDocumentoIdentificacion.clear();
        txtDirectorioCliente.clear();
    }
}