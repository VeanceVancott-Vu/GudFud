package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Cmt;
import gutfud.dacs.DTO.Recipe;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SvReport {
    String usernname;
    String recipename;

    public SvReport(String usernname, String recipename) {
        this.usernname = usernname;
        this.recipename = recipename;
    }
    public boolean report()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hqlQuery = "FROM Recipe WHERE UserName = :userName AND RecipeName = :recipeName";
            Query<Recipe> query = session.createQuery(hqlQuery, Recipe.class);
            query.setParameter("userName", usernname );
            query.setParameter("recipeName", recipename);
            Recipe recipe = query.getSingleResult();
            System.out.println(recipe);
            recipe.setReported(true);

            System.out.println(recipe);

            session.update(recipe);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch (Exception e )
        {
            e.printStackTrace();
            return false;
        }
    }
}
