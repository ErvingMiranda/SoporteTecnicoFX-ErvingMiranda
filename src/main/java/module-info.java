module org.ezone.pae.soportetecnicofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.ezone.pae.soportetecnicofx.Controllers to javafx.fxml;

    exports org.ezone.pae.soportetecnicofx;
    exports org.ezone.pae.soportetecnicofx.Applications;
    exports org.ezone.pae.soportetecnicofx.Controllers;
}