module com.cimba.lightsout {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.cimba.lightsout to javafx.fxml;
    exports com.cimba.lightsout;
    exports com.cimba.lightsout.observer;
    opens com.cimba.lightsout.observer to javafx.fxml;
    exports com.cimba.lightsout.factory;
    opens com.cimba.lightsout.factory to javafx.fxml;
}