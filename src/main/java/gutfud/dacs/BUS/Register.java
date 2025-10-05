package gutfud.dacs.BUS;

import gutfud.dacs.DTO.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;

public class Register {
    public Register() {
    }

    public boolean Register(String username, String password, String phone, LocalDate dob, String email,Socket socket,String title) throws Exception {

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        String action = "Register";

        dos.writeUTF(action);
        dos.writeUTF(username);
        dos.writeUTF(password);
        dos.writeUTF(phone);
        dos.writeUTF(String.valueOf(dob));
        dos.writeUTF(email);
        dos.writeUTF(title);
        dos.writeInt(0);
        dos.flush();


        return dis.readBoolean();
    }


}
