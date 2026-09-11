package org.ezone.pae.soportetecnicofx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class SolicitudServiciosController {

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtCorreoCliente;

    @FXML
    private ComboBox<String> cmbTipoCliente;

    @FXML
    private TextField txtAsunto;

    @FXML
    private ComboBox<String> cmbTipoServicio;

    @FXML
    private RadioButton rbtnPrioridadBaja;

    @FXML
    private RadioButton rbtnPrioridadMedia;

    @FXML
    private RadioButton rbtnPrioridadAlta;

    @FXML
    private TextArea txtAreaDescripcionProblema;

    @FXML
    private TextField txtArchivoAdjunto;

    @FXML
    private TextField txtCarpetaEvidencias;

    @FXML
    private Button btnGuardarSolicitud;

    @FXML
    private Button btnCrearSolicitud;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnSeleccionarArchivo;

    @FXML
    private Button btnSeleccionarDirectorio;

    @FXML
    private Button btnCerrar;

    private final ToggleGroup tgPrioridad = new ToggleGroup();

    @FXML
    private void initialize() {
        cmbTipoCliente.getItems().addAll("Particular", "Empresa");
        cmbTipoServicio.getItems().addAll("Hardware", "Software", "Redes", "Mantenimiento");

        rbtnPrioridadBaja.setToggleGroup(tgPrioridad);
        rbtnPrioridadMedia.setToggleGroup(tgPrioridad);
        rbtnPrioridadAlta.setToggleGroup(tgPrioridad);
    }

    @FXML
    private void seleccionarArchivo() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar archivo adjunto");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivo TXT", "*.txt"),
                new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Archivo Excel", "*.xls"),
                new FileChooser.ExtensionFilter("Archivo Word", "*.docx"),
                new FileChooser.ExtensionFilter("Archivo Markdown", "*.md")
        );

        File f = fc.showOpenDialog(btnSeleccionarArchivo.getScene().getWindow());

        if (f != null) {
            txtArchivoAdjunto.setText(f.getAbsolutePath());
        }
    }

    @FXML
    private void seleccionarDirectorio() {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccionar carpeta de evidencias");
        dc.setInitialDirectory(new File("C:\\"));

        File f = dc.showDialog(btnSeleccionarDirectorio.getScene().getWindow());

        if (f != null) {
            txtCarpetaEvidencias.setText(f.getAbsolutePath());
        }
    }

    @FXML
    private void guardarSolicitud() {
        if (!validarFormulario()) {
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación");
        confirmacion.setHeaderText("¿Seguro que desea guardar la solicitud?");
        confirmacion.setContentText("Asunto: " + txtAsunto.getText());

        Optional<ButtonType> respuesta = confirmacion.showAndWait();

        if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Solicitud guardada");
            alerta.setHeaderText("Solicitud guardada");
            alerta.setContentText("Solicitud guardada correctamente.");
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
        if (txtCliente.getText().isEmpty() || txtCorreoCliente.getText().isEmpty() || cmbTipoCliente.getValue() == null || txtAsunto.getText().isEmpty() || cmbTipoServicio.getValue() == null || tgPrioridad.getSelectedToggle() == null || txtAreaDescripcionProblema.getText().isEmpty() || txtArchivoAdjunto.getText().isEmpty() || txtCarpetaEvidencias.getText().isEmpty()) {
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
        txtCliente.clear();
        txtCorreoCliente.clear();
        cmbTipoCliente.setValue(null);
        txtAsunto.clear();
        cmbTipoServicio.setValue(null);
        tgPrioridad.selectToggle(null);
        txtAreaDescripcionProblema.clear();
        txtArchivoAdjunto.clear();
        txtCarpetaEvidencias.clear();
    }
}