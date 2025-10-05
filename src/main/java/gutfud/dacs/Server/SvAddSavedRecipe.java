package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Recipe;
import gutfud.dacs.DTO.Savedrecipedetails;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.ServerSocket;

public class SvAddSavedRecipe {
    Savedrecipedetails savedrecipedetails;

    public SvAddSavedRecipe(Savedrecipedetails savedrecipedetails) {
        this.savedrecipedetails = savedrecipedetails;
    }
    public boolean SavedRecipe()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.save(savedrecipedetails);
            String HQL = "From Recipe WHERE UserName = :username and RecipeName = :recipename";
            Query<Recipe> query = session.createQuery(HQL);
            query.setParameter("username",savedrecipedetails.getUserName());
            query.setParameter("recipename",savedrecipedetails.getRecipeName());
            Recipe recipe = query.getSingleResult();
            recipe.setNumberOfSaved(recipe.getNumberOfSaved()+1);

            session.getTransaction().commit();

            return true;
        }

    }
}
