package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Savedrecipedetails;
import gutfud.dacs.DTO.SavedrecipedetailsPK;

import java.io.IOException;

public class AddSavedRecipe {
   Savedrecipedetails savedrecipedetails;
    DataStreamManager manager;

    public AddSavedRecipe(Savedrecipedetails savedrecipedetails, DataStreamManager manager) {
        this.savedrecipedetails = savedrecipedetails;
        this.manager = manager;
    }
    public boolean AddSavedRecipe() throws IOException {
        manager.sendAction("Add-SavedRecipe");
        return   manager.AddSavedRecipe(savedrecipedetails);
    }
}
