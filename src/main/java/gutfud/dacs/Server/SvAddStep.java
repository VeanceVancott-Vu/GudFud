package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Step;
import org.hibernate.Session;

public class SvAddStep {
    private Step step;

    public SvAddStep(Step step) {
        this.step = step;
    }
    public void AddStep()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.save(step);
            session.getTransaction().commit();
        }    }
}
