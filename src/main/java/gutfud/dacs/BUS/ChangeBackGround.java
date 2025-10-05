package gutfud.dacs.BUS;

import java.io.IOException;

public class ChangeBackGround {
    private DataStreamManager manager;
    private String username;
    private byte[] bytearray;

    public ChangeBackGround(DataStreamManager manager, String username, byte[] bytearray) {
        this.manager = manager;
        this.username = username;
        this.bytearray = bytearray;
    }
    public boolean ChangeBackGround() throws IOException {
        manager.sendAction("Change-background");
        return   manager.sendBytes(bytearray,username);
    }
}
