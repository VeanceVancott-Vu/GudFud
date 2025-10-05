package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SvLogin {
    String username;
    String password;
public SvLogin (String username, String password)
{
    this.password=password;
    this.username = username;
}

public boolean CheckLogin()
{
    boolean valid = false;
    try(Session session = HibernateUtil.getSessionFactory().openSession())
    {
        session.beginTransaction();
        MD5Hashing md5Hashing = new MD5Hashing();
        Query q = session.createQuery("from User");
        List<User> userList = q.list();
        for(User user : userList)
        {

            if(user.getUserName().equals(username) && user.getPass().equals(md5Hashing.getMD5Hash(password)))
            {
                valid = true;
            }
        }
        session.getTransaction().commit();
        session.close();
    }
    catch (Exception e)
    {

        e.printStackTrace();
        return false;
    }

    return valid;
}

}

