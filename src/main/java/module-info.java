module org.ezone.pae.soportetecnicofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.ezone.pae.soportetecnicofx to javafx.fxml;
    exports org.ezone.pae.soportetecnicofx;
}