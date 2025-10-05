package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SvViewRecipe {
    public SvViewRecipe()
    {}
    public ArrayList<Recipe> ViewRecipe() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query q = session.createQuery("from Recipe ");

            List<Recipe> list = q.list();

            ArrayList<Recipe> RecipeList = new ArrayList<>(list);
            session.getTransaction().commit();
            session.close();

            return RecipeList;
        }
    }
    public ArrayList<Image> ViewRecipeAva()
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query q = session.createQuery("from Image where  ImgNumber = 0");
            List<Image> list = q.list();
            ArrayList<Image> imgList = new ArrayList<>(list);
            session.getTransaction().commit();
            session.close();
            return imgList;
        }
    }
    public ArrayList<Recipe> ViewSavedRecipe(String username)
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            ArrayList<Recipe> RecipeList = new ArrayList<>();
            String hqlQuery = "FROM Savedrecipedetails WHERE UserName = :userName ";
            Query<Savedrecipedetails> query = session.createQuery(hqlQuery, Savedrecipedetails.class);
            query.setParameter("userName", username );

            List<Savedrecipedetails> savedlist = query.list();

            for(Savedrecipedetails savedrecipedetails : savedlist)
            {
                System.out.println(savedrecipedetails);
                String hqlQuery2 = "FROM Recipe WHERE UserName = :userName AND RecipeName = :recipeName";
                Query<Recipe> query2 = session.createQuery(hqlQuery2, Recipe.class);
                query2.setParameter("userName", savedrecipedetails.getRecipe_UserName() );
                query2.setParameter("recipeName", savedrecipedetails.getRecipeName());
                Recipe recipe = query2.getSingleResult();
                RecipeList.add(recipe);
            }

            session.getTransaction().commit();
            session.close();

            return RecipeList;
        }

    }
}
