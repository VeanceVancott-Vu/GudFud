package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Ingredient;
import gutfud.dacs.DTO.Rate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ViewIngre {
    private DataStreamManager manager;
    public ViewIngre(DataStreamManager manager)
    {
        this.manager = manager;
    }
    public ArrayList<Ingredient> ViewIngre(String RecipeName, String UserName) throws IOException {

        ArrayList<Ingredient> list = new ArrayList<>();
        manager.sendAction("View-ingredient");
        list = manager.ViewIngre(RecipeName,UserName);

        return list;
    }
}
