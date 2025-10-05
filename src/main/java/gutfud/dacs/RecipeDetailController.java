package gutfud.dacs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RecipeDetailController extends Application implements Initializable
{
    String scene ="RecipeDetail.fxml";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void setScene(String scene)
    {
        this.scene = scene;
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader Loginloader = new FXMLLoader(ClientLogin.class.getResource(scene));
        Scene Loginsc = new Scene((Parent)Loginloader.load());
        stage.setScene(Loginsc);
        stage.show();
    }
}
