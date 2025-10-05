package gutfud.dacs.DTO;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.sql.Date;

@Entity
@IdClass(RecipePK.class)
public class Recipe implements Serializable {


    public Recipe() {
    }
    public Recipe(RecipePK recipePK)
    {
        this.UserName = recipePK.getUserName();
        this.RecipeName = recipePK.getRecipeName();
    }
    @ManyToOne
    @JoinColumn(name = "UserName")
    private gutfud.dacs.DTO.User User;


    public gutfud.dacs.DTO.User getUser() {
        return User;
    }

    public void setUser(gutfud.dacs.DTO.User user) {
        User = user;
    }
    @Id
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "RecipeName", column=@Column(name = "RecipeName")),
                    @AttributeOverride(name = "UserName", column=@Column(name = "UserName"))
            }
    )
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

    @Basic
    @Column(name = "Reported", nullable = true)
    private Boolean reported;
    @Type(type = "org.hibernate.type.NumericBooleanType")

    public Boolean getReported() {
        return reported;
    }

    public void setReported(Boolean reported) {
        this.reported = reported != null ? reported : false;
    }

    @Basic
    @Column(name = "NumberOfSaved", nullable = true)
    private Integer numberOfSaved;

    public Integer getNumberOfSaved() {
        return numberOfSaved;
    }

    public void setNumberOfSaved(Integer numberOfSaved) {
        this.numberOfSaved = numberOfSaved;
    }

    @Basic
    @Column(name = "DatePublish", nullable = true)
    private Date datePublish;

    public Date getDatePublish() {
        return datePublish;
    }

    public void setDatePublish(Date datePublish) {
        this.datePublish = datePublish;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "User=" + User +
                ", RecipeName='" + RecipeName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", reported=" + reported +
                ", numberOfSaved=" + numberOfSaved +
                ", datePublish=" + datePublish +
                '}';
    }
}
