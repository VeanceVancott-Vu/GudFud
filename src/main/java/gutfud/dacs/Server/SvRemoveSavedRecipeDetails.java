package gutfud.dacs.Server;

import gutfud.dacs.BUS.DataStreamManager;
import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Savedrecipedetails;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class SvRemoveSavedRecipeDetails {
    private String UserName;
    private String RecipName;

    private String Recipe_UserName;
    private DataStreamManager manager;

    public boolean RemoveSavedRecipeDetails(String username,String recipename,String recipeusername)
    {

        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            String hqlQuery = "DELETE from Savedrecipedetails WHERE UserName = :userName AND RecipeName = :recipeName AND Recipe_UserName = :recipeusername";
            Query query = session.createQuery(hqlQuery); // No result class needed
            query.setParameter("userName", username);
            query.setParameter("recipeName", recipename);
            query.setParameter("recipeusername", recipeusername);
            int deletedRecords = query.executeUpdate();

            if (deletedRecords == 0) {
                return false;
            }

            session.getTransaction().commit();
            session.close();

        }
        return true;
    }





}
