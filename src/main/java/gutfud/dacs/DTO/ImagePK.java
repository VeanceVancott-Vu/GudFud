package gutfud.dacs.DTO;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class ImagePK implements Serializable {
    private String RecipeName;
    private String UserName;
    private int ImgNumber;

    public int getImgNumber() {
        return ImgNumber;
    }

    public void setImgNumber(int imgNumber) {
        ImgNumber = imgNumber;
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

    @Override
    public String toString() {
        return "RecipePK{" +
                "RecipeName='" + RecipeName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Image number='" + ImgNumber +'\''+
                '}';
    }
}