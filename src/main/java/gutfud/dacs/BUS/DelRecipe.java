package gutfud.dacs.BUS;

import java.io.IOException;

public class DelRecipe {
    private String username;
    private String recipename;
    private DataStreamManager manager;

    public DelRecipe(String username, String recipename,DataStreamManager manager) {
        this.username = username;
        this.recipename = recipename;
        this.manager = manager;
    }
    public boolean DelRecipe() throws IOException {
        manager.sendAction("Delete-recipe");
        return  manager.DelRecipe(username,recipename);



    }
}
