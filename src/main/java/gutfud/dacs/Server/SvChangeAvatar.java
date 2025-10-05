package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;

public class SvChangeAvatar {
    private String username;
    private byte[] bytearray;

    public SvChangeAvatar(String username, byte[] bytearray) {
        this.username = username;
        this.bytearray = bytearray;
    }

    public boolean ChangeAvatar()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            User user = session.get(User.class,username);
            user.setUserAvatarUrl(bytearray);
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
