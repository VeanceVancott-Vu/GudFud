package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Conversations;
import gutfud.dacs.DTO.Messages;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class SvViewMess {
    private String CurrUserName;
    private String WantToTextUsername;

    public SvViewMess(String currUserName, String wantToTextUsername) {
        CurrUserName = currUserName;
        WantToTextUsername = wantToTextUsername;
    }
    public ArrayList<Messages> ViewMess()
    {
        System.out.println("Sender: "+CurrUserName);
        System.out.println("Reciver: "+WantToTextUsername);

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQLConver = "From Conversations WHERE ( user1Id = :CurrUsername and user2Id = :WantToTextUsername) or ( user2Id = :CurrUsername and user1Id = :WantToTextUsername) ";
            Query q = session.createQuery(HQLConver);
            q.setParameter("CurrUsername", CurrUserName);
            q.setParameter("WantToTextUsername", WantToTextUsername);
            Conversations conver = (Conversations) q.getSingleResult();
            String HQLMess = "FROM Messages WHERE conversationId = : ConverID ";
            Query q1 = session.createQuery(HQLMess);
            q1.setParameter("ConverID",conver.getId());
            ArrayList<Messages> arrmess = (ArrayList<Messages>) q1.list();
            return arrmess;
        }

    }
}
