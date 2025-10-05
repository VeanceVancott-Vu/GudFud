package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;

import gutfud.dacs.DTO.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SvForgotPass {

    String pass;
    String email;



    public SvForgotPass( String pass, String email) {
        this.pass = pass;
        this.email = email;
        System.out.println( "email "+email);
        System.out.println("pass"+pass);
    }
    public boolean SvForgotPass()
    {
        boolean complete = false;
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            String HQL = "";
            String hqlQuery = "FROM User WHERE email = :email";
            Query<User> query = session.createQuery(hqlQuery, User.class);
            query.setParameter("email", email );
            User user = query.getSingleResult();
            session.getTransaction().commit();
               if(user == null)
               {
                   session.close();
                   return complete;
               }
               else
               {
                   MD5Hashing md5Hashing = new MD5Hashing();
                   session.beginTransaction();
                   System.out.println(email+"   "+user.getEmail());
                   System.out.println(user);
                   user.setPass(md5Hashing.getMD5Hash(pass));
                   session.update(user);
                   session.getTransaction().commit();
                   complete = true;
                   session.close();
                   return complete;

               }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
