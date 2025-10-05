package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Image;
import gutfud.dacs.DTO.Recipe;
import gutfud.dacs.RecipeForm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewRecipe {
    public ArrayList<Recipe> ViewRecipe(DataStreamManager manager) throws IOException {
        ArrayList<Recipe> list = new ArrayList<>();
        manager.sendAction("View-recipe");
        list = manager.ViewRecipe();

        return list;

    }
    public ArrayList<Image> ViewRecipeAva(DataStreamManager manager) throws IOException {
        ArrayList<Image> list = new ArrayList<>();
        manager.sendAction("View-recipeAva");
        list = manager.ViewRecipeAva();


        return list;

    }
}
