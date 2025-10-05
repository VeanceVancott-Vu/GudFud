package gutfud.dacs.DTO;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@IdClass(gutfud.dacs.DTO.CmtPK.class)
public class Cmt implements Serializable {
    public Cmt(CmtPK cmtPK) {
        RecipeName = cmtPK.getRecipeName();
        UserName = cmtPK.getUserName();
        Content = cmtPK.getContent();
        CmtUserName = cmtPK.getCmtUserName();
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

    @ManyToOne
    @JoinColumn(name = "CmtUserName", referencedColumnName = "UserName")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Id
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "RecipeName", column=@Column(name = "RecipeName")),
                    @AttributeOverride(name = "UserName", column=@Column(name = "UserName")),
                    @AttributeOverride(name = "Content",column = @Column(name = "Content")),
                    @AttributeOverride(name = "CmtUserName",column = @Column(name = "CmtUserName"))

            }
    )
    private String RecipeName;
    private String UserName;
    private String Content;

    public Cmt() {

    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
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
    @Basic
    @Column(name = "DateCmt")
    private Date DateCmt;

    public Date getDateCmt() {
        return DateCmt;
    }

    public void setDateCmt(Date dateCmt) {
        DateCmt = dateCmt;
    }
    @Basic
    @Column(name = "CmtUserName")
    private String CmtUserName;

    public String getCmtUserName() {
        return CmtUserName;
    }

    public void setCmtUserName(String cmtUserName) {
        CmtUserName = cmtUserName;
    }

    @Override
    public String toString() {
        return "Cmt{" +
                ", RecipeName='" + RecipeName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Content='" + Content + '\'' +
                ", DateCmt=" + DateCmt +
                ", CmtUserName='" + CmtUserName + '\'' +
                '}';
    }
}
