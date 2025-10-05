package gutfud.dacs.BUS;

import gutfud.dacs.ClientApp;
import gutfud.dacs.ClientLogin;
import gutfud.dacs.DTO.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Login {
    private DataStreamManager manager;

    public Login(DataStreamManager manager) {
        this.manager = manager;
    }
    public Login() {
    }

    public boolean CheckLogin(String username, String pass) throws IOException {
        manager.sendAction("Login");
        return manager.sendLogin(username,pass);
    }
    public User ReturnUser(String username) throws IOException {
    manager.sendAction("Get-user");

    return manager.getUser(username);
    }
}
