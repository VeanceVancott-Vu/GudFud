package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.User;
import org.hibernate.Session;

import java.sql.Date;

public class SvRegister {
    public boolean Register(User user)
    {
        boolean complete = false;
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            MD5Hashing md5Hashing = new MD5Hashing();
            String pass = md5Hashing.getMD5Hash(user.getPass()) ;
            user.setPass(pass);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            complete =true;
            session.close();
            return complete;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return complete;
        }
    }
}
