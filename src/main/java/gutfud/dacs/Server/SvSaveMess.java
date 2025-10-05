package gutfud.dacs.Server;

import gutfud.dacs.BUS.HibernateUtil;
import gutfud.dacs.DTO.Messages;
import javafx.scene.image.Image;
import org.hibernate.Session;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Instant;

public class SvSaveMess {
    private String sender;
    private String reciver;
    private String message;

    public SvSaveMess(String sender, String reciver, String mess) {
        this.sender = sender;
        this.reciver = reciver;
        this.message = mess;
    }
    public boolean SaveMess() throws IOException {
        Timestamp currentTimestamp = Timestamp.from(Instant.now());


        Messages mess = new Messages();
        mess.setSenderId(sender);
        mess.setMessageText(message);
        mess.setConversationId(1L);
        mess.setTimeStamp(currentTimestamp);
        mess.setIsRead(false);
       File file = new File("src/main/java/Images/Error.jpg");
        byte[] fileBytes = new byte[(int) file.length()];
        fileBytes  = Files.readAllBytes(Paths.get(file.getPath()));
        mess.setImage(fileBytes);
        System.out.println(mess);

        try(Session session = HibernateUtil.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.save(mess);
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
