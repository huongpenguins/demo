module com.ticketclient {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ticketclient to javafx.fxml;
    exports com.ticketclient;
}
