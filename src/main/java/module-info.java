module gutfud.dacs {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires javax.mail;

    opens gutfud.dacs.DTO to org.hibernate.orm.core;
    opens gutfud.dacs to javafx.fxml;
    exports gutfud.dacs;
}