package gutfud.dacs.DTO;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class CmtPK implements Serializable {
    private String RecipeName;
    private String UserName;
    private String Content;
    private String CmtUserName;


    public String getCmtUserName() {
        return CmtUserName;
    }

    public void setCmtUserName(String cmtUserName) {
        CmtUserName = cmtUserName;
    }

    @Override
    public String toString() {
        return "CmtPK{" +
                "RecipeName='" + RecipeName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Content='" + Content + '\'' +
                '}';
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

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
