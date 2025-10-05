package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Image;

import java.io.IOException;
import java.util.ArrayList;

public class ViewImage {
    private DataStreamManager manager;

    public ViewImage(DataStreamManager manager)
    {
        this.manager = manager;
    }
    public ArrayList<Image> ViewImage(String RecipeName,String UserName) throws IOException {
        ArrayList<Image> list = new ArrayList<>();
        manager.sendAction("View-image");
        list = manager.ViewImage(RecipeName,UserName);

        return list;
    }
}
