package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Cmt;
import gutfud.dacs.DTO.Step;
import org.hibernate.Session;

public class SvAddCmt {
    private Cmt cmt;

    public SvAddCmt(Cmt cmt) {
        this.cmt = cmt;
    }
    public boolean AddCmt()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.save(cmt);
            session.getTransaction().commit();
            return true;
        }
    }
}
