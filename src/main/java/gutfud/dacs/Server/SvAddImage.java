package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Image;
import gutfud.dacs.DTO.ImagePK;
import org.hibernate.Session;

public class SvAddImage {
    Image image;
    public SvAddImage(Image img)
    {
        this.image = img;
    }
    public void SvAddImage()
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.save(image);
            session.getTransaction().commit();
        }
    }
}
