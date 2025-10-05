package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Recipe;
import gutfud.dacs.DTO.RecipePK;
import gutfud.dacs.DTO.User;
import org.hibernate.Session;

import java.sql.Date;
import java.time.LocalDate;

public class SvAddRecipe {
    String RecipeName;
    String UserName;

    public SvAddRecipe(String recipeName, String userName) {
        RecipeName = recipeName;
        UserName = userName;
    }
    public boolean AddRecipe()
    {
        RecipePK recipePK = new RecipePK();
        recipePK.setRecipeName(RecipeName);
        recipePK.setUserName(UserName);
        Recipe recipe = new Recipe(recipePK);
        recipe.setReported(false);
        recipe.setDatePublish(Date.valueOf(LocalDate.now()));
        recipe.setNumberOfSaved(0);

        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.save(recipe);
            User user = session.get(User.class,UserName);
            user.setNumOfRecipe(user.getNumOfRecipe()+1);
            session.update(user);
            session.getTransaction().commit();
            return true;
        }

    }
}
