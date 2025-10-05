package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class SvDelRecipe {



    public boolean SvDelRecipe(String username, String recipename) {
        if (username == null || recipename == null) {
            System.err.println("Username or RecipeName is null");
            return false;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Delete Step
            Query query6 = session.createQuery("DELETE FROM Step WHERE UserName = :username AND RecipeName = :recipename");
            query6.setParameter("username", username);
            query6.setParameter("recipename", recipename);
            int result6 = query6.executeUpdate();
            System.out.println("Deleted " + result6 + " Step(s)");

            // Delete SavedRecipeDetails
            Query query5 = session.createQuery("DELETE FROM Savedrecipedetails WHERE Recipe_UserName = :Recipe_UserName AND RecipeName = :recipename");
            query5.setParameter("Recipe_UserName", username);
            query5.setParameter("recipename", recipename);
            int result5 = query5.executeUpdate();
            System.out.println("Deleted " + result5 + " SavedRecipeDetails(s)");

            // Delete Ingredient
            Query query4 = session.createQuery("DELETE FROM Ingredient WHERE UserName = :username AND RecipeName = :recipename");
            query4.setParameter("username", username);
            query4.setParameter("recipename", recipename);
            int result4 = query4.executeUpdate();
            System.out.println("Deleted " + result4 + " Ingredient(s)");

            // Delete Image
            Query query3 = session.createQuery("DELETE FROM Image WHERE UserName = :username AND RecipeName = :recipename");
            query3.setParameter("username", username);
            query3.setParameter("recipename", recipename);
            int result3 = query3.executeUpdate();
            System.out.println("Deleted " + result3 + " Image(s)");

            // Delete Rate
            Query query2 = session.createQuery("DELETE FROM Rate WHERE UserName = :username AND RecipeName = :recipename");
            query2.setParameter("username", username);
            query2.setParameter("recipename", recipename);
            int result2 = query2.executeUpdate();
            System.out.println("Deleted " + result2 + " Rate(s)");

            // Delete Comment
            Query query1 = session.createQuery("DELETE FROM Cmt WHERE UserName = :username AND RecipeName = :recipename");
            query1.setParameter("username", username);
            query1.setParameter("recipename", recipename);
            int result1 = query1.executeUpdate();
            System.out.println("Deleted " + result1 + " Comment(s)");

            // Delete Recipe
            Query query = session.createQuery("DELETE FROM Recipe WHERE UserName = :username AND RecipeName = :recipename");
            query.setParameter("username", username);
            query.setParameter("recipename", recipename);
            int result = query.executeUpdate();
            System.out.println("Deleted " + result + " Recipe(s)");

            // Update User
            User user = session.get(User.class, username);
            if (user != null) {
                user.setNumOfRecipe(user.getNumOfRecipe() - 1);
                session.update(user);
                System.out.println("Updated user: " + user.getUserName() + " with new recipe count: " + user.getNumOfRecipe());
            } else {
                System.err.println("User not found: " + username);
            }

            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
