package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Ingredient;
import gutfud.dacs.DTO.Step;

import java.io.IOException;
import java.util.ArrayList;

public class ViewStep {
    private DataStreamManager manager;
    public ViewStep(DataStreamManager manager)
    {
        this.manager = manager;
    }
    public ArrayList<Step> ViewStep(String RecipeName, String UserName) throws IOException {

        ArrayList<Step> list = new ArrayList<>();
        manager.sendAction("View-step");
        list = manager.ViewStep(RecipeName,UserName);

        return list;
    }
}
