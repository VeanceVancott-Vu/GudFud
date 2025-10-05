package gutfud.dacs.DTO;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Arrays;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UserName", nullable = false, length = 100)
    private String userName;
    @Basic
    @Column(name = "NumOfRecipe", nullable = true)
    private Integer numOfRecipe;
    @Basic
    @Column(name = "Pass", nullable = true, length = 50)
    private String pass;
    @Basic
    @Column(name = "UserAvatarURL", nullable = true)
    private byte[] userAvatarUrl;
    @Basic
    @Column(name = "Phone", nullable = true, length = 20)
    private String phone;
    @Basic
    @Column(name = "DateOfBirth", nullable = true)
    private Date dateOfBirth;
    @Basic
    @Column(name = "Email", nullable = true, length = 100)
    private String email;
    @Basic
    @Column(name = "Title", nullable = true, length = 50)
    private String title;

    @Basic
    @Column(name = "BackGround", nullable = true)
    private byte[] background;

    public byte[] getBackground() {
        return background;
    }

    public void setBackground(byte[] background) {
        this.background = background;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getNumOfRecipe() {
        return numOfRecipe;
    }

    public void setNumOfRecipe(Integer numOfRecipe) {
        this.numOfRecipe = numOfRecipe;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public byte[] getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(byte[] userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", numOfRecipe=" + numOfRecipe +
                ", pass='" + pass + '\'' +
                ", userAvatarUrl=" + userAvatarUrl +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", background=" + background +
                '}';
    }
}
