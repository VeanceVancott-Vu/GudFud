package gutfud.dacs.DTO;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class RatePK implements Serializable {
    private String RecipeName;
    private String UserName;
    private String RatedUserName;

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

    public String getRatedUserName() {
        return RatedUserName;
    }

    public void setRatedUserName(String ratedUserName) {
        RatedUserName = ratedUserName;
    }
}
