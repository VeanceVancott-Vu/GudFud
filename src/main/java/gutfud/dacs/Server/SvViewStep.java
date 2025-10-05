package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Rate;
import gutfud.dacs.DTO.Step;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SvViewStep {
    private String RecipeName;
    private String UserName;

    public SvViewStep(String recipeName, String userName) {
        RecipeName = recipeName;
        UserName = userName;

    }
    public ArrayList<Step> ViewStep()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            String hqlQuery = "FROM Step WHERE UserName = :userName AND RecipeName = :recipeName";
            Query<Step> query = session.createQuery(hqlQuery, Step.class);
            query.setParameter("userName", UserName );
            query.setParameter("recipeName", RecipeName);
            List<Step> list = query.list();
            ArrayList<Step> stepList = new ArrayList<>(list);
            session.getTransaction().commit();
            session.close();
            return stepList;
        }
    }
}
