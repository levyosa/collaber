module com.levyferreira.collaber {
    requires java.desktop;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.apache.commons.io;
    requires org.apache.pdfbox;
    requires pdfbox.tools;

    opens com.levyferreira.collaber to javafx.fxml;
    exports com.levyferreira.collaber;
}