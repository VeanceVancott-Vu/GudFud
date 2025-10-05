package gutfud.dacs.BUS;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ForgotPass {
    public ForgotPass() {

    }

    public boolean ForgotPass( String password, String email, DataStreamManager manager) throws IOException {


        String action = "Forgot-pass";
        manager.sendAction(action);
       return  manager.ForgotPass(email,password);


    }
}
