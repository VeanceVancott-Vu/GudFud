package gutfud.dacs.BUS;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailOTP {
    private String ToEmail;
    private String OTP;
    private static final String FROM_EMAIL = "vupm.23it@vku.udn.vn";
    private static final String PASSWORD = "Vu08112005";
    private static final String SUBJECT = "Java Example Test";
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public EmailOTP(String email) {
        this.ToEmail = email;
    }

    private void CreateOTP() {
        Random random = new Random();
        StringBuilder OTPBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            OTPBuilder.append(randomNumber);
        }
        this.OTP = OTPBuilder.toString();
    }

    public boolean SendEmail() {
        try {
            CreateOTP();
            String body = "Hello, this is your OTP: " + OTP;

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
            props.put("mail.smtp.port", "587"); // TLS Port
            props.put("mail.smtp.auth", "true"); // Enable authentication
            props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(FROM_EMAIL, "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse(FROM_EMAIL, false));
            msg.setSubject(SUBJECT, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ToEmail, false));

            executorService.submit(() -> {
                try {
                    Transport.send(msg);
                    System.out.println("Email sent successfully.");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            return true;
        } catch (UnsupportedEncodingException | MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean CheckOTP(String otp) {
        return otp.equalsIgnoreCase(OTP);
    }
}
