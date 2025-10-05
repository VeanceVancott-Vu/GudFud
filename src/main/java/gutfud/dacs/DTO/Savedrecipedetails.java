package gutfud.dacs.DTO;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@IdClass(gutfud.dacs.DTO.SavedrecipedetailsPK.class)
public class Savedrecipedetails implements Serializable {
    @ManyToOne
    @JoinColumn(name = "Recipe_UserName" , referencedColumnName = "UserName")
    @JoinColumn(name = "RecipeName")
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @ManyToOne
    @JoinColumn(name = "UserName")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Savedrecipedetails(SavedrecipedetailsPK savedrecipedetailsPK)
    {
        this.UserName = savedrecipedetailsPK.getUserName();
        this.RecipeName = savedrecipedetailsPK.getRecipeName();
        this.Recipe_UserName = savedrecipedetailsPK.getRecipe_UserName();
    }
    @Id
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "RecipeName", column=@Column(name = "RecipeName")),
                    @AttributeOverride(name = "UserName", column=@Column(name = "UserName")),
                    @AttributeOverride(name = "Recipe_UserName" , column = @Column(name = "Recipe_UserName"))
            }
    )
    private String RecipeName;
    private String UserName;
    private String Recipe_UserName;

    public Savedrecipedetails() {

    }

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

    @Basic
    @Column(name = "DateSaved", nullable = true)
    private Date dateSaved;

    public Date getDateSaved() {
        return dateSaved;
    }

    public void setDateSaved(Date dateSaved) {
        this.dateSaved = dateSaved;
    }

    @Override
    public String toString() {
        return "Savedrecipedetails{" +
                "recipe=" + recipe +
                ", user=" + user +
                ", RecipeName='" + RecipeName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Recipe_UserName='" + Recipe_UserName + '\'' +
                ", dateSaved=" + dateSaved +
                '}';
    }
}
