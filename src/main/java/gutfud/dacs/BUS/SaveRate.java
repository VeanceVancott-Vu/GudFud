package gutfud.dacs.BUS;

import java.io.IOException;
import java.sql.Date;

public class SaveRate {
    private DataStreamManager manager;

    public SaveRate(DataStreamManager manager) {
        this.manager = manager;
    }
    public boolean SaveRate(int rate, String recipename, String username, String ratedusername, Date daterated) throws IOException {
        manager.sendAction("Save-rate");
       return manager.SaveRate(recipename,username,rate,ratedusername,daterated);

    }
}
