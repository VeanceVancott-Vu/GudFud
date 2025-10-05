package gutfud.dacs.DTO;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@jakarta.persistence.IdClass(gutfud.dacs.DTO.RatePK.class)
public class Rate implements Serializable {
    public Rate(RatePK ratePK)
    {
        this.RecipeName = ratePK.getRecipeName();
        this.UserName = ratePK.getUserName();
        this.RatedUserName = ratePK.getRatedUserName();
    }
    @ManyToOne
    @JoinColumn(name = "RatedUserName",referencedColumnName = "UserName")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "Rate", nullable = false)
    private int rate;

    public Rate() {

    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @jakarta.persistence.Basic
    @jakarta.persistence.Column(name = "DateRated", nullable = false)
    private Date dateRated;

    public Date getDateRated() {
        return dateRated;
    }

    public void setDateRated(Date dateRated) {
        this.dateRated = dateRated;
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
                    @AttributeOverride(name = "RatedUserName" , column = @Column(name = "RatedUserName"))
            }
    )
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

    @Override
    public String toString() {
        return "Rate{" +
              ", rate=" + rate +
                ", dateRated=" + dateRated +
                ", RecipeName='" + RecipeName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", RatedUserName='" + RatedUserName + '\'' +
                '}';
    }
}
