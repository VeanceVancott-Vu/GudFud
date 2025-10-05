package gutfud.dacs.BUS;

import java.io.IOException;

public class RemoveSavedRecipDetail {
    private String UserName;
    private String RecipName;

    private String Recipe_UserName;
    private DataStreamManager manager;

    public RemoveSavedRecipDetail(String userName, String recipName, String recipe_UserName, DataStreamManager manager) {
        UserName = userName;
        RecipName = recipName;
        Recipe_UserName = recipe_UserName;
        this.manager = manager;
    }
    public  boolean RemoveSavedRecipDetail() throws IOException {
        manager.sendAction("Remove-savedrecipdetail");
        return manager.RemoveSavedRecipDetail(UserName,RecipName,Recipe_UserName);
    }
}
