package gutfud.dacs.BUS;

import java.io.IOException;

public class AddRecipe {
    private String RecipeName;
    private String UserName;
    private DataStreamManager manager;

    public AddRecipe(String recipeName,String userName,DataStreamManager manager) {
        RecipeName = recipeName;
        this.UserName = userName;
        this.manager = manager;
    }
    public boolean AddRecipe() throws IOException {
        manager.sendAction("Add-recipe");
      return   manager.sendRecipe(RecipeName,UserName);
    }
}
