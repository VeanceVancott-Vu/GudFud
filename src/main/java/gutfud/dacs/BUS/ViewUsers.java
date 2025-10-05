package gutfud.dacs.BUS;

import gutfud.dacs.DTO.User;

import java.io.IOException;
import java.util.ArrayList;

public class ViewUsers {
    private DataStreamManager manager;
    public ViewUsers(DataStreamManager manager)
    {this.manager = manager;}
    public ArrayList<User> ViewUsers() throws IOException {
        manager.sendAction("View-users");
        return manager.ViewUsers();
    }
}
