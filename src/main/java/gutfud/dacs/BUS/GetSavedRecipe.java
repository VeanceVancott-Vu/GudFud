package gutfud.dacs.BUS;


import gutfud.dacs.DTO.Recipe;

import java.io.IOException;
import java.util.ArrayList;

public class GetSavedRecipe {
    private String username;
    private DataStreamManager manager;

    public GetSavedRecipe(String username, DataStreamManager manager) {
        this.username = username;
        this.manager = manager;
    }
    public ArrayList<Recipe> ViewSavedRecipe() throws IOException {
        ArrayList<Recipe> list = new ArrayList<>();
        manager.sendAction("View-savedrecipe");
        list = manager.ViewSavedRecipe(username);
        return list;
    }
}
