package gutfud.dacs;


import gutfud.dacs.BUS.*;
import gutfud.dacs.BUS.ForgotPass;
import gutfud.dacs.DTO.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController  {
    @FXML
    private AnchorPane ParentPane;
    @FXML
    private AnchorPane LogInPane;
    @FXML
    private AnchorPane RegisterPane;
    @FXML
    private AnchorPane ForgotPassPane;
    @FXML
    private TextField UserNameTF;
    @FXML
    private PasswordField PasswordPF ;
    @FXML
    private PasswordField RePasswordF;
    @FXML
    private TextField PassShowTF;
    @FXML
    private Label RegisterLine,OTPlbl;
    @FXML
    private Button LogInBtt,BackToLoginBtt;
    @FXML
    private Button SignInBTT,SendOTPbtt,CheckOTPBtt,ConfrimBtt;
    @FXML
    private TextField ReUserNameTF;
    @FXML
    private TextField RePassShowTF;
    @FXML
    private TextField RePhoneTF;
    @FXML
    private TextField ReEmailTF;
    @FXML
    private DatePicker ReDOB;
    @FXML
    private CheckBox ShowPassCheckBox;
    @FXML
    private CheckBox ReShowPassCheckBox;
    @FXML
    private TextField FoUsernameTF,FoEmailTF,FoPassShowF,FoNewPassShowF,OTPTF;
    @FXML
    private PasswordField FoPasswordF, FoNewPasswordF;
    @FXML
    private CheckBox FoShowPassCheckBox,FoShowNewPassCheckBox;
    @FXML
    private Label PassNotMatchLbl,EmailLbl,ReWritePassLbl,NewPassWordLbl;
    @FXML
    private  ComboBox<String> TitleComboBox;
    @FXML
    private ImageView ImageView;

    public LoginController() {
    }


    @FXML
    public void Login(ActionEvent event) {

        String username = this.UserNameTF.getText();
        String password = this.PasswordPF.getText();
        try {
            Socket socket = new Socket("localhost",7400);
            DataStreamManager manager = new DataStreamManager(socket);

            boolean valid =  new Login(manager).CheckLogin(username, password);
            System.out.println(valid);
            if(valid )
            {
                User user = new Login(manager).ReturnUser(username);
                System.out.println(user.toString());
                System.out.println("done in logincontroller");
                System.out.println(manager);
                Stage Appstage = new Stage();
                System.out.println(socket);
                ClientApp clientApp = new ClientApp();
                clientApp.setUserName(username);
                clientApp.setManager(manager);
                clientApp.setSocket(socket);
                clientApp.setUser(user);
                clientApp.start(Appstage);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Alert");
                alert.setHeaderText("Some error occur");
                alert.setContentText("Username or password wrong.");

                ButtonType closeButton = new ButtonType("Close");
                alert.getButtonTypes().setAll(closeButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == closeButton) {
                    // User clicked "Close"
                    System.out.println("Alert closed.");
                }

            }
        }
         catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void togglevisiblePassword(ActionEvent event) {
        if (this.ShowPassCheckBox.isSelected()) {
            this.PasswordPF.setManaged(false);
            this.PasswordPF.setVisible(false);
            this.PassShowTF.setManaged(true);
            this.PassShowTF.setVisible(true);
            this.PassShowTF.setText(this.PasswordPF.getText());
        } else {
            this.PassShowTF.setManaged(false);
            this.PassShowTF.setVisible(false);
            this.PasswordPF.setManaged(true);
            this.PasswordPF.setVisible(true);
            this.PasswordPF.setText(this.PasswordPF.getText());
        }

    }
    @FXML
    public void RetogglevisiblePassword(ActionEvent event) {
        if (this.ReShowPassCheckBox.isSelected()) {
            this.RePasswordF.setManaged(false);
            this.RePasswordF.setVisible(false);
            this.RePassShowTF.setManaged(true);
            this.RePassShowTF.setVisible(true);
            this.RePassShowTF.setText(this.RePasswordF.getText());
        }
        else
        {
            this.RePassShowTF.setManaged(false);
            this.RePassShowTF.setVisible(false);
            this.RePasswordF.setManaged(true);
            this.RePasswordF.setVisible(true);
            this.RePasswordF.setText(this.RePassShowTF.getText());
        }

    }
    @FXML
    public void FotogglevisiblePassword(ActionEvent event) {
        if (this.FoShowPassCheckBox.isSelected()) {
            this.FoPasswordF.setManaged(false);
            this.FoPasswordF.setVisible(false);
            this.FoPassShowF.setManaged(true);
            this.FoPassShowF.setVisible(true);
            this.FoPassShowF.setText(this.FoPasswordF.getText());
        }
        else
        {
            this.FoPassShowF.setManaged(false);
            this.FoPassShowF.setVisible(false);
            this.FoPasswordF.setManaged(true);
            this.FoPasswordF.setVisible(true);
            this.FoPasswordF.setText(this.FoPassShowF.getText());
        }

    }
    @FXML
    public void FotogglevisibleNewPassword(ActionEvent event) {
        if (this.FoShowNewPassCheckBox.isSelected()) {
            this.FoNewPasswordF.setManaged(false);
            this.FoNewPasswordF.setVisible(false);
            this.FoNewPassShowF.setManaged(true);
            this.FoNewPassShowF.setVisible(true);
            this.FoNewPassShowF.setText(this.FoNewPasswordF.getText());
        }
        else
        {
            this.FoNewPassShowF.setManaged(false);
            this.FoNewPassShowF.setVisible(false);
            this.FoNewPasswordF.setManaged(true);
            this.FoNewPasswordF.setVisible(true);
            this.FoNewPasswordF.setText(this.FoNewPassShowF.getText());
        }

    }
    @FXML
    public void OpenLoginPane() {
        this.LogInPane.setManaged(true);
        this.LogInPane.setVisible(true);
        this.RegisterPane.setManaged(false);
        this.RegisterPane.setVisible(false);
        this.ForgotPassPane.setManaged(false);
        this.ForgotPassPane.setVisible(false);

    }

    @FXML
    public void OpenRegisterPane(MouseEvent event) {
        this.LogInPane.setManaged(false);
        this.LogInPane.setVisible(false);
        this.ForgotPassPane.setManaged(false);
        this.ForgotPassPane.setVisible(false);
        this.RegisterPane.setManaged(true);
        this.RegisterPane.setVisible(true);
        String[] title = {"Newbie","Homecook","Seasoned Cook","Master Chef","Executive Chef"};
        this.TitleComboBox.getItems().addAll(title);
    }
    @FXML
    public void OpenForgotPassPane(MouseEvent event)
    {
        this.LogInPane.setManaged(false);
        this.LogInPane.setVisible(false);
        this.ForgotPassPane.setManaged(true);
        this.ForgotPassPane.setVisible(true);
        this.RegisterPane.setManaged(false);
        this.RegisterPane.setVisible(false);
    }

    @FXML
    public void Register(ActionEvent event) throws Exception {
        LocalDate dob = (LocalDate)this.ReDOB.getValue();
        String username = this.ReUserNameTF.getText();
        String pass = this.RePasswordF.getText();
        String phone = this.RePhoneTF.getText();
        String email = this.ReEmailTF.getText();
        String title = this.TitleComboBox.getSelectionModel().getSelectedItem();
        try {
            Socket  socket = new Socket("localhost",7400);
            boolean valid=(new Register()).Register(username, pass, phone, dob, email,socket,title);
            if(valid)
            {
                this.RegisterPane.setManaged(false);
                this.RegisterPane.setVisible(false);
                this.LogInPane.setManaged(true);
                this.LogInPane.setVisible(true);
                socket.close();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Alert");
                alert.setHeaderText("Some error occur");
                alert.setContentText("There something wrong happen try again later.");

                ButtonType closeButton = new ButtonType("Close");
                alert.getButtonTypes().setAll(closeButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == closeButton) {
                    // User clicked "Close"
                    System.out.println("Alert closed.");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Some error occur");
            alert.setContentText("There something wrong happen try again later.");

            ButtonType closeButton = new ButtonType("Close");
            alert.getButtonTypes().setAll(closeButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == closeButton) {
                // User clicked "Close"
                System.out.println("Alert closed.");
            }
        }

    }
    @FXML
    public void SendEmail()
    {

        String email = FoEmailTF.getText();
        System.out.println(email);
        EmailOTP emailOTP = new EmailOTP(email);

        if(emailOTP.SendEmail())
        {
            BackToLoginBtt.setVisible(false);
            OTPlbl.setVisible(true);
            OTPTF.setVisible(true);
            EmailLbl.setVisible(false);
            SendOTPbtt.setVisible(false);
            CheckOTPBtt.setVisible(true);

            CheckOTPBtt.setOnAction(event -> {

                String enterOTP = OTPTF.getText();
                if(emailOTP.CheckOTP(enterOTP))
                {
                    System.out.println("Same OTP");
                    OTPlbl.setVisible(false);
                    OTPTF.setVisible(false);
                    CheckOTPBtt.setVisible(false);
                    FoEmailTF.setVisible(false);
                    BackToLoginBtt.setVisible(false);

                    FoShowPassCheckBox.setVisible(true);
                    FoShowNewPassCheckBox.setVisible(true);
                    ConfrimBtt.setVisible(true);
                    NewPassWordLbl.setVisible(true);
                    ReWritePassLbl.setVisible(true);
                    FoPasswordF.setVisible(true);
                    FoNewPasswordF.setVisible(true);

                    ConfrimBtt.setOnAction(event1 -> {
                        String pass = this.FoPasswordF.getText();
                        String rewritepass = this.FoNewPasswordF.getText();
                        Socket socket = null;
                        try {
                            socket = new Socket("localhost",7400);

                            boolean passwordsMatch = pass.equals(rewritepass);

                            if(passwordsMatch)
                            {
                                DataStreamManager manager = new DataStreamManager(socket);
                                this.PassNotMatchLbl.setVisible(false);
                                boolean done = (new ForgotPass()).ForgotPass(pass,email,manager);
                                Alert alert;
                                if(done)
                                {
                                    alert = new Alert(Alert.AlertType.INFORMATION, "Changed password", ButtonType.YES);
                                    this.OpenLoginPane();
                                }
                                else
                                {
                                    alert = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.YES);
                                }
                                alert.showAndWait();

                            }
                            else {
                                this.PassNotMatchLbl.setVisible(true);
                            }

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });


                }
                else
                {
                    System.out.println("Wrong OTP");
                }

            });
        }
    }


}
