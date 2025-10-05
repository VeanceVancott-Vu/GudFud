package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Cmt;
import gutfud.dacs.DTO.Savedrecipedetails;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SvGetSavedRecipeDetails {
    public boolean GetSavedRecipeDetails(String username,String recipename,String recipeusername)
    {

        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            String hqlQuery = "FROM Savedrecipedetails WHERE UserName = :userName AND RecipeName = :recipeName AND Recipe_UserName = :recipeusername";
            Query<Savedrecipedetails> query = session.createQuery(hqlQuery, Savedrecipedetails.class);
            query.setParameter("userName", username );
            query.setParameter("recipeName", recipename);
            query.setParameter("recipeusername",recipeusername);
            List<Savedrecipedetails> list = query.list();
            if(list.isEmpty())
            {
                return false;
            }
            session.getTransaction().commit();
            session.close();
        }
        return true;
    }


}
