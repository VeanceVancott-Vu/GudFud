package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.BUS.ViewRate;
import gutfud.dacs.DTO.Image;
import gutfud.dacs.DTO.Rate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SvViewRate {
    private String RecipeName;
    private String UserName;

    public SvViewRate(String recipeName, String userName) {
        RecipeName = recipeName;
        UserName = userName;

    }
    public ArrayList<Rate> ViewRate()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            String hqlQuery = "FROM Rate WHERE UserName = :userName AND RecipeName = :recipeName";
            Query<Rate> query = session.createQuery(hqlQuery, Rate.class);
            query.setParameter("userName", UserName );
            query.setParameter("recipeName", RecipeName);
            List<Rate> list = query.list();
            ArrayList<Rate> rateList = new ArrayList<>(list);
            session.getTransaction().commit();
            session.close();
            return rateList;
        }
    }

}
