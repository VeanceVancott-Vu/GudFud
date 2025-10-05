package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Messages;

import java.io.IOException;
import java.util.ArrayList;

public class ViewMess {
    private DataStreamManager manager;
    private String CurrUserName;
    private String WantToTextUserName;

    public ViewMess(DataStreamManager manager, String currUserName, String wantToTextUserName) {
        this.manager = manager;
        CurrUserName = currUserName;
        WantToTextUserName = wantToTextUserName;
    }
    public ArrayList<Messages> ViewMess()  {
        ArrayList<Messages> messagesArrayList = new ArrayList<>();
        try {
            manager.sendAction("View-mess");
            return manager.ViewMess(CurrUserName,WantToTextUserName);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
