package gutfud.dacs;

import gutfud.dacs.DTO.Image;
import gutfud.dacs.DTO.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;

public class RecipeForm {
    @FXML
    private Label Recipelbl,Usernamelbl,Datelbl;
    @FXML
    private ImageView ImageView;

    public void setData(Recipe recipe, Image image,String title )
    {
            Usernamelbl.setText(title+": "+recipe.getUserName());
            Recipelbl.setText(recipe.getRecipeName());
             byte[] imgdata = image.getImgUrl();
             javafx.scene.image.Image img = new javafx.scene.image.Image(new ByteArrayInputStream(imgdata));

             ImageView.setImage(img);
            Datelbl.setText(String.valueOf(recipe.getDatePublish()));

    }




}
