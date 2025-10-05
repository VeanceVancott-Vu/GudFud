package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;

public class SvChangeBackGround {
    private String username;
    private byte[] bytearray;

    public SvChangeBackGround(String username, byte[] bytearray) {
        this.username = username;
        this.bytearray = bytearray;
    }
    public boolean ChangeBackGround()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            User user = session.get(User.class,username);
            user.setBackground(bytearray);
            session.update(user);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }
}
