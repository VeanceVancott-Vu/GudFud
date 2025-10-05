package gutfud.dacs.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class RecipePK implements Serializable {

    private String RecipeName;
    private String UserName;

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

    @Override
    public String toString() {
        return "RecipePK{" +
                "RecipeName='" + RecipeName + '\'' +
                ", UserName='" + UserName + '\'' +
                '}';
    }
}
