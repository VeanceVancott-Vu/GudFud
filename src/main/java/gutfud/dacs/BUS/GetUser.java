package gutfud.dacs.BUS;

import gutfud.dacs.DTO.User;

import java.io.IOException;

public class GetUser {
    private DataStreamManager manager;
    private String username;

    public GetUser(DataStreamManager manager, String username) {
        this.manager = manager;
        this.username = username;
    }
    public User GetUser() throws IOException {

        manager.sendAction("Get-user");
        User user = manager.getUser(username);
        System.out.println(user);
        return user;
    }
}
