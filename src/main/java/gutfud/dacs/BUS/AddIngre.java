package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Ingredient;

import java.io.IOException;
import java.util.ArrayList;

public class AddIngre {
    private ArrayList<String> IngreList;
    private ArrayList<String> QuantityList;
    private ArrayList<Ingredient> ingreList;
    private DataStreamManager manager;

    public AddIngre(ArrayList<String> ingreList, ArrayList<String> quantityList,DataStreamManager manager) {
        IngreList = ingreList;
        QuantityList = quantityList;
    }
    public AddIngre(ArrayList<Ingredient> ingreList, DataStreamManager manager)
    {
        this.ingreList = ingreList;
        this.manager = manager;
    }



    public void AddIngre() throws IOException {
       for(Ingredient ingredient : ingreList)
       {
           manager.sendAction("Add-ingredient");
           manager.sendIngre(ingredient);
       }
    }

}
