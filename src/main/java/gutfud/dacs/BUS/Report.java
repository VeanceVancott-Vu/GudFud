package gutfud.dacs.BUS;

import java.io.IOException;

public class Report {
    private String username;
    private String recipename;
    private DataStreamManager manager;

    public Report(String username, String recipename,DataStreamManager manager) {
        this.username = username;
        this.recipename = recipename;
        this.manager = manager;
    }
    public boolean report() throws IOException {
        manager.sendAction("Report");
        return manager.sendReport(username,recipename);
    }
}
