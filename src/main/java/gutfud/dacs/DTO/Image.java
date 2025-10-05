package gutfud.dacs.DTO;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;

@Entity
@IdClass(gutfud.dacs.DTO.ImagePK.class)
public class Image implements Serializable {
    public Image(ImagePK imagePK)
    {
        this.RecipeName = imagePK.getRecipeName();
        this.UserName = imagePK.getUserName();
        this.ImgNumber = imagePK.getImgNumber();
    }
    @Basic
    @jakarta.persistence.Column(name = "ImgURL", nullable = true)
    private byte[] imgUrl;

    public Image() {

    }

    public byte[] getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(byte[] imgUrl) {
        this.imgUrl = imgUrl;
    }




    @ManyToOne
    @JoinColumn(name = "UserName")
    @JoinColumn(name = "RecipeName")
    private gutfud.dacs.DTO.Recipe recipe;

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
                    @AttributeOverride(name = "ImgNumber",column = @Column(name = "ImgNumber"))
            }
    )
    private String RecipeName;
    private String UserName;
    private int ImgNumber;

    public int getImgNumber() {
        return ImgNumber;
    }

    public void setImgNumber(int imgNumber) {
        this.ImgNumber = imgNumber;
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
        return "Image{" +
                ", recipe=" + recipe +
                ", RecipeName='" + RecipeName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", ImgNumber=" + ImgNumber +
                '}';
    }
}
