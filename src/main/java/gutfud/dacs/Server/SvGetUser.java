package gutfud.dacs.Server;
import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SvGetUser {
    private String username;
    public SvGetUser() {

    }


    public SvGetUser(String username) {
        this.username = username;
    }
    public User getuser()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            User user = session.get(User.class, username);
            session.getTransaction().commit();
            if (user != null) {
                session.close();
                return user;
            }
        }
        return null;
    }
    public ArrayList<User> getuserlist()
    {
        ArrayList<User> Arrlist = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            String HQL = "FRom User";
            Query q = session.createQuery(HQL);
            List<User> list = q.list();
            for(User user : list)
            {
                if(user!= null)
                Arrlist.add(user);
            }
            return Arrlist;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Arrlist;
    }
}
