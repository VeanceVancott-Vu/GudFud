package gutfud.dacs.DTO;

import jakarta.persistence.*;

import javax.naming.Name;
import javax.xml.namespace.QName;
import java.io.Serializable;

@Entity
@IdClass(IngredientPK.class)
public class Ingredient implements Serializable {
    public Ingredient(IngredientPK ingredientPK)
    {
        this.RecipeName = ingredientPK.getRecipeName();
        this.UserName = ingredientPK.getUserName();
        this.IngreName = ingredientPK.getIngredientName();
    }

    @Basic
    @Column(name = "IngreQuantity", nullable = true, length = 100)
    private String ingreQuantity;

    public String getIngreQuantity() {
        return ingreQuantity;
    }

    public void setIngreQuantity(String ingreQuantity) {
        this.ingreQuantity = ingreQuantity;
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

    @Id
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "RecipeName", column=@Column(name = "RecipeName")),
                    @AttributeOverride(name = "UserName", column=@Column(name = "UserName")),
                    @AttributeOverride(name = "IngreName" , column = @Column(name = "IngreName"))
            }
    )
    private String RecipeName;
    private String UserName;
    private String IngreName;

    public String getIngreName() {
        return IngreName;
    }

    public void setIngreName(String ingreName) {
        IngreName = ingreName;
    }

    public String getIngredientName() {
        return IngreName;
    }

    public void setIngredientName(String ingredientName) {
        IngreName = ingredientName;
    }

    public Ingredient() {

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

}
