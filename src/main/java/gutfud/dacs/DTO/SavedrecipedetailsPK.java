package gutfud.dacs.DTO;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class SavedrecipedetailsPK implements Serializable {
    private String RecipeName;
    private String UserName;
    private String Recipe_UserName;

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getRecipe_UserName() {
        return Recipe_UserName;
    }

    public void setRecipe_UserName(String recipe_UserName) {
        Recipe_UserName = recipe_UserName;
    }
}
