package gutfud.dacs.DTO;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(StepPK.class)
public class Step implements Serializable {
    public Step(StepPK stepPK)
    {
        this.RecipeName=stepPK.getRecipeName();
        this.UserName = stepPK.getUserName();
        this.StepOrder = stepPK.getStepOrder();
    }
    @ManyToOne
    @JoinColumn(name = "UserName")
    @JoinColumn(name = "RecipeName")
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    private String RecipeName;

    private String UserName;
    private int StepOrder;
    @Id
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "RecipeName", column=@Column(name = "RecipeName")),
                    @AttributeOverride(name = "UserName", column=@Column(name = "UserName")),
                    @AttributeOverride(name = "StepOrder" , column = @Column(name = "StepOrder"))
            }
    )

    @Column(name = "Content", nullable = true, length = 255)
    private String content;

    public Step() {

    }

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        this.RecipeName = recipeName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public int getStepOrder() {
        return StepOrder;
    }

    public void setStepOrder(int stepOrder) {
        this.StepOrder = stepOrder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Step{" +
                "recipe=" + recipe +
                ", RecipeName='" + RecipeName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", StepOrder=" + StepOrder +
                ", content='" + content + '\'' +
                '}';
    }
}
