package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Cmt;

import java.io.IOException;
import java.sql.Date;

public class AddCmt {
    private DataStreamManager manager;

    public AddCmt(DataStreamManager manager) {
        this.manager = manager;
    }
    public boolean AddCmt(Cmt cmt) throws IOException {
        manager.sendAction("Save-cmt");
        return manager.SaveCmt(cmt);

    }
}
