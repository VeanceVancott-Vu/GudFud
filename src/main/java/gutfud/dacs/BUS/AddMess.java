package gutfud.dacs.BUS;

import java.io.IOException;
import java.sql.Timestamp;

public class AddMess {
    private DataStreamManager manager;
    private String MessText;
    private String Sendername;
    private String Recivername;

    public AddMess(DataStreamManager manager, String messText, String sendername,String recivername) {
        this.manager = manager;
        MessText = messText;
        Sendername = sendername;
        Recivername = recivername;

    }
    public boolean AddMess() throws IOException {
        manager.sendAction("Save-mess");
        return   manager.SaveMess(Sendername,Recivername,MessText);
    }
    public String SendMess() throws IOException {
        manager.sendAction("Send-mess");
        manager.SendMess(MessText );
        return "";
    }
}
