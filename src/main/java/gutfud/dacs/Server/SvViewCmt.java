package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Cmt;
import gutfud.dacs.DTO.CmtPK;
import gutfud.dacs.DTO.Step;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SvViewCmt {
    private String RecipeName;
    private String UserName;

    public SvViewCmt(String recipeName, String userName) {
        RecipeName = recipeName;
        UserName = userName;

    }
    public ArrayList<Cmt> ViewCmt()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            String hqlQuery = "FROM Cmt WHERE UserName = :userName AND RecipeName = :recipeName";
            Query<Cmt> query = session.createQuery(hqlQuery, Cmt.class);
            query.setParameter("userName", UserName );
            query.setParameter("recipeName", RecipeName);
            List<Cmt> list = query.list();
            ArrayList<Cmt> cmtList = new ArrayList<>(list);
            session.getTransaction().commit();
            session.close();
            return cmtList;
        }
    }
}
