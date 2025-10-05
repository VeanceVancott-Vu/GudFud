package gutfud.dacs.BUS;

import java.io.IOException;

public class ChangeAvatar {
    private DataStreamManager manager;
    private String username;
    private byte[] bytearray;

    public ChangeAvatar(DataStreamManager manager, String username, byte[] bytearray) {
        this.manager = manager;
        this.username = username;
        this.bytearray = bytearray;
    }
    public boolean ChangeAvatar() throws IOException {
        manager.sendAction("Change-avatar");
      return   manager.sendBytes(bytearray,username);
    }
}
