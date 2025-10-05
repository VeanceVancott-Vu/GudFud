package gutfud.dacs;

import gutfud.dacs.DTO.Image;
import gutfud.dacs.DTO.Messages;
import gutfud.dacs.DTO.Recipe;
import gutfud.dacs.DTO.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;

public class UserForm  {
    @FXML
    private Label UsernameLBL;
    @FXML
    private javafx.scene.image.ImageView AvatarIV;

    public void setData(User user)
    {
        byte[] imgdata = user.getUserAvatarUrl();
        javafx.scene.image.Image img = new javafx.scene.image.Image(new ByteArrayInputStream(imgdata));
        AvatarIV.setImage(img);
        UsernameLBL.setText(user.getUserName());

    }


}
