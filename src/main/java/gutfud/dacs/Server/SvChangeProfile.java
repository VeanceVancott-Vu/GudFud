package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.User;
import org.hibernate.Session;

import java.sql.Date;

public class SvChangeProfile {
    private String username;
    private String title;
    private String phone;
    private String email;
    private String dob;

    public SvChangeProfile(String username, String title, String phone, String email, String dob) {
       this.username=username;
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
    }

    public boolean ChangeProfile()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class,username);
            user.setTitle(title);
            user.setEmail(email);
            user.setPhone(phone);
            user.setDateOfBirth(Date.valueOf(dob));
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
