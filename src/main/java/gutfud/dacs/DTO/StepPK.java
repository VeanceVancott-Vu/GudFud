package gutfud.dacs.DTO;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
@Embeddable

public class StepPK implements Serializable {
    private String RecipeName;
    private String UserName;
    private int StepOrder;

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

    public int getStepOrder() {
        return StepOrder;
    }

    public void setStepOrder(int stepOrder) {
        StepOrder = stepOrder;
    }
}
