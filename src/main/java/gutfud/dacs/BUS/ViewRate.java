package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Image;
import gutfud.dacs.DTO.Rate;

import java.io.IOException;
import java.util.ArrayList;

public class ViewRate {
    private DataStreamManager manager ;
    public ViewRate(DataStreamManager manager)
    {
        this.manager = manager;
    }
    public ArrayList<Rate> ViewRate(String RecipeName,String UserName) throws IOException {

        ArrayList<Rate> list = new ArrayList<>();
        manager.sendAction("View-rate");
        list = manager.ViewRate(RecipeName,UserName);

        return list;
    }
}
