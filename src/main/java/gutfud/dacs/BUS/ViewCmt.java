package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Cmt;
import gutfud.dacs.DTO.Step;

import java.io.IOException;
import java.util.ArrayList;

public class ViewCmt {
    private DataStreamManager manager;
    public ViewCmt(DataStreamManager manager)
    {
        this.manager = manager;
    }
    public ArrayList<Cmt> ViewCmt(String RecipeName, String UserName) throws IOException {

        ArrayList<Cmt> list = new ArrayList<>();
        manager.sendAction("View-cmt");
        list = manager.ViewCmt(RecipeName,UserName);

        return list;
    }
}
