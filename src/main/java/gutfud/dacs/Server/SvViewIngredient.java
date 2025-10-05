package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Ingredient;
import gutfud.dacs.DTO.Rate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SvViewIngredient {
    private String RecipeName;
    private String UserName;

    public SvViewIngredient(String recipeName, String userName) {
        RecipeName = recipeName;
        UserName = userName;
    }
    public ArrayList<Ingredient> ViewIngre()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            String hqlQuery = "FROM Ingredient WHERE UserName = :userName AND RecipeName = :recipeName";
            Query<Ingredient> query = session.createQuery(hqlQuery, Ingredient.class);
            query.setParameter("userName", UserName );
            query.setParameter("recipeName", RecipeName);
            List<Ingredient> list = query.list();
            ArrayList<Ingredient> IngreList = new ArrayList<>(list);
            session.getTransaction().commit();
            session.close();
            return IngreList;
        }

    }

}
