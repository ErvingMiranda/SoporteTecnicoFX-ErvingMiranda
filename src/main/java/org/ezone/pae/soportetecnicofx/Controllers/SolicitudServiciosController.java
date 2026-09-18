package org.ezone.pae.soportetecnicofx.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.ezone.pae.soportetecnicofx.Models.Cliente;

import java.io.File;

public class SolicitudServiciosController {

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtCorreoCliente;

    @FXML
    private TextField txtTipoCliente;

    @FXML
    private TextField txtAsunto;

    @FXML
    private ComboBox<String> cmbTipoServicio;

    @FXML
    private ComboBox<String> cmbPrioridad;

    @FXML
    private TextArea txtAreaDescripcionProblema;

    @FXML
    private TextField txtArchivoAdjunto;

    @FXML
    private TextField txtCarpetaEvidencias;

    @FXML
    private TextArea txtAreaResultado;

    @FXML
    private Button btnSeleccionarArchivo;

    @FXML
    private Button btnSeleccionarDirectorio;

    @FXML
    private Button btnCerrar;

    private Cliente cliente;

    @FXML
    private void initialize() {
        cmbTipoServicio.setItems(FXCollections.observableArrayList(
                "Hardware",
                "Software",
                "Redes",
                "Mantenimiento"
        ));

        cmbPrioridad.getItems().addAll(
                "Baja",
                "Media",
                "Alta"
        );
    }

    public void recibirCliente(Cliente cliente) {
        if (cliente == null) {
            return;
        }

        this.cliente = cliente;
        txtCliente.setText(cliente.getNombre());
        txtCorreoCliente.setText(cliente.getCorreo());
        txtTipoCliente.setText(cliente.getTipoCliente());
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
        dc.setTitle("Seleccionar carpeta");
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

        String informacionCliente;

        if (cliente != null) {
            informacionCliente = "Datos del cliente:\n";
            informacionCliente += "Cliente: " + cliente.getNombre() + "\n";
            informacionCliente += "Correo: " + cliente.getCorreo() + "\n";
            informacionCliente += "Tipo de cliente: " + cliente.getTipoCliente() + "\n";
        }
        else {
            informacionCliente= "Datos del cliente:\n" + "Solicitud sin cliente";
        }

        String resultado = informacionCliente + "\n\n" + "Solicitud de servicio\n" +
                "Asunto: " + txtAsunto.getText() + "\n" +
                "Tipo de servicio: " + cmbTipoServicio.getValue() + "\n" +
                "Prioridad: " + cmbPrioridad.getValue() + "\n" +
                "Descripción del problema: " + txtAreaDescripcionProblema.getText() + "\n" +
                "Archivo adjunto: " + txtArchivoAdjunto.getText() + "\n" +
                "Carpeta evidencias: " + txtCarpetaEvidencias.getText();

        txtAreaResultado.setText(resultado);

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Solicitud registrada",
                "La solicitud fue registrada"
        );

        limpiarSolicitud();
    }

    @FXML
    private void limpiarContenido() {
        limpiarSolicitud();
        txtAreaResultado.clear();
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    private boolean validarFormulario() {
        if (txtAsunto.getText().isEmpty() ||
                cmbTipoServicio.getValue() == null ||
                cmbPrioridad.getValue() == null ||
                txtAreaDescripcionProblema.getText().isEmpty() ||
                txtArchivoAdjunto.getText().isEmpty() ||
                txtCarpetaEvidencias.getText().isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Advertencia",
                    "Ningún campo de la solicitud debe quedar vacío."
            );

            return false;
        }

        return true;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void limpiarSolicitud() {
        txtAsunto.clear();
        cmbTipoServicio.setValue(null);
        cmbPrioridad.setValue(null);
        txtAreaDescripcionProblema.clear();
        txtArchivoAdjunto.clear();
        txtCarpetaEvidencias.clear();
    }
}