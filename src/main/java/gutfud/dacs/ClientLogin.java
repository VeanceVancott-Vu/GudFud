package gutfud.dacs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class ClientLogin extends Application {
    String source = "Login.fxml";
    private static Stage stage;

    public ClientLogin() {

    }

    public void start(Stage stage) throws Exception {
        ClientLogin.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(source));
       // URL cssUrl = getClass().getResource("gutfud/dacs/CSS/Login.css");
//        if (cssUrl == null) {
//            System.err.println("CSS file not found!");
//            return;
//        }

        String css = "D:\\IdeaProjects\\GutFud\\src\\main\\resources\\Login.css";

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
