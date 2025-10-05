package gutfud.dacs.BUS;

import java.io.IOException;

public class GetSavedRecipeDetails {
    private String UserName;
    private String RecipName;

    private String Recipe_UserName;
    private DataStreamManager manager;

    public GetSavedRecipeDetails(String userName, String recipName, String recipe_UserName,DataStreamManager manager) {
        UserName = userName;
        RecipName = recipName;
        Recipe_UserName = recipe_UserName;
        this.manager =manager;
    }
    public boolean GetSavedRecipeDetails() throws IOException {
        manager.sendAction("Get-savedrecipedetails");

        return manager.GetSavedRecipeDetails(UserName,RecipName,Recipe_UserName);
    }



}
