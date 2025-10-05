package gutfud.dacs;

import gutfud.dacs.BUS.DataStreamManager;
import gutfud.dacs.DTO.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.Socket;
import java.util.Optional;

public class ClientApp extends Application {
    String UserName;
    String UserTitle;
    String UserAvatar;
    private Socket socket;
    private DataStreamManager manager;
    private User user;
    public ClientApp() {

    }
    public void setUser(User user) {
        this.user = user;
    }

    public DataStreamManager getManager() {
        return manager;
    }

    public void setManager(DataStreamManager manager) {
        this.manager = manager;
    }
    public void setUserName(String userName)
    {
        this.UserName = userName;
    }

    String scene = "App.fxml";
    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }
    public Socket getSocket()
    {
        return socket;
    }

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(scene));
        loader.setControllerFactory(new CustomControllerFactory(manager,user));

        Parent root = loader.load();

        AppController controller = loader.getController();

        String css = "D:\\IdeaProjects\\GutFud\\src\\main\\resources\\App.css";

        controller.setSocket(socket);
        Scene Loginsc = new Scene(root);
        Loginsc.getStylesheets().add(css);
        stage.setScene(Loginsc);
        stage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }



}
