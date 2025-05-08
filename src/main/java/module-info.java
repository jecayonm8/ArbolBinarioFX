module org.proyecto.arbolbinariofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.proyecto.arbolbinariofx to javafx.fxml;
    opens org.proyecto.arbolbinariofx.controller to javafx.fxml;

    exports org.proyecto.arbolbinariofx;
    exports org.proyecto.arbolbinariofx.controller;
}