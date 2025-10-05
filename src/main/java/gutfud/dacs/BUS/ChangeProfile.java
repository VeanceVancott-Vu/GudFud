package gutfud.dacs.BUS;

import java.io.IOException;
import java.sql.Date;

public class ChangeProfile {
    private String username;

    private String title;
    private String phone;
    private String email;
    private Date dob;
    private DataStreamManager manager;

    public ChangeProfile(String username, String title, String phone, String email, Date dob,DataStreamManager manager) {

        this.username = username;
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.manager= manager;
    }
    public boolean ChangeProfile() throws IOException {
        manager.sendAction("Change-profile");
        return manager.sendChangeProfile(username,title,phone,email,dob);
    }
}
