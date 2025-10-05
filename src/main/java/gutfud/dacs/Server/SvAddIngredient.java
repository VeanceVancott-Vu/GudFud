package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Ingredient;
import org.hibernate.Session;

public class SvAddIngredient {
    private Ingredient ingredient;

    public SvAddIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    public void AddIngre()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.save(ingredient);
            session.getTransaction().commit();
        }
    }
}
