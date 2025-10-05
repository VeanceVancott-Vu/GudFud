package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Rate;
import gutfud.dacs.DTO.RatePK;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class SvSaveRate {
    private String recipename;
    private String username;
    private int rate;
    private String rateusername;
    private String daterate;

    public SvSaveRate(String recipename, String username,int rate,String rateusername,String daterate) {
        this.recipename = recipename;
        this.username = username;
        this.rate    = rate ;
        this.rateusername = rateusername;
        this.daterate = daterate;
    }

    public boolean  SaveRate() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();


            String hqlQuery = "FROM Rate WHERE UserName = :userName AND RecipeName = :recipeName AND RatedUserName =: ratedUserName ";
            Query<Rate> query = session.createQuery(hqlQuery, Rate.class);
            query.setParameter("userName", username);
            query.setParameter("recipeName", recipename);
            query.setParameter("ratedUserName", rateusername);
            List<Rate> list = query.list();
                if (!list.isEmpty()) {
                    Rate rate1 = query.getSingleResult();
                    rate1.setDateRated(Date.valueOf(daterate));
                    rate1.setRate(rate);
                    System.out.println(rate1.toString());
                    session.update(rate1);
                    session.getTransaction().commit();
                    System.out.println("Update rate");
                    return true;
                } else {

                    RatePK ratePK = new RatePK();
                    ratePK.setRatedUserName(rateusername);
                    ratePK.setRecipeName(recipename);
                    ratePK.setUserName(username);
                    Rate Rate = new Rate(ratePK);
                    Rate.setRate(rate);
                    Rate.setDateRated(Date.valueOf(daterate));

                    session.save(Rate);
                    System.out.println("Add new rate");
                    session.getTransaction().commit();
                    return true;
                }



        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }


}
