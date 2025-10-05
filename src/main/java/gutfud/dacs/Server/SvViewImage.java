package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Image;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SvViewImage {
    private String RecipeName;
    private String UserName;

    public SvViewImage(String recipeName, String userName) {
        RecipeName = recipeName;
        UserName = userName;
    }

    public ArrayList<Image>  GetImage()
    {
    try (Session session = HibernateUtil.getSessionFactory().openSession())
    {
        session.beginTransaction();
        String hqlQuery = "FROM Image WHERE UserName = :userName AND RecipeName = :recipeName";
        Query<Image> query = session.createQuery(hqlQuery, Image.class);
        query.setParameter("userName", UserName );
        query.setParameter("recipeName", RecipeName);
        List<Image> list = query.list();
        ArrayList<Image> imgList = new ArrayList<>(list);
        session.getTransaction().commit();
        session.close();

        return imgList;
    }
    }

}
