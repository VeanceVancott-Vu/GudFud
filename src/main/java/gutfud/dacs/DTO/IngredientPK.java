package gutfud.dacs.DTO;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class IngredientPK implements Serializable {
    private String RecipeName;
    private String UserName;
    private String IngreName;

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

    public String getIngredientName() {
        return IngreName;
    }

    public void setIngredientName(String ingredientName) {
        IngreName = ingredientName;
    }

    @Override
    public String toString() {
        return "IngredientPK{" +
                "RecipeName='" + RecipeName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", IngredientName='" + IngreName + '\'' +
                '}';
    }
}
