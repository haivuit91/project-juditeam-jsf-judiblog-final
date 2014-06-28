package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {

    /**
     * Send email by SSL
     *
     * @param toEmail Email of user
     * @param idActive id active authentication
     */
    public static void sendMail(String toEmail, String idActive) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(util.Constants.FROM_EMAIL, util.Constants.PASSWORD_EMAIL);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(util.Constants.FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Authentication registration your account.");
            if (!idActive.isEmpty()) {
                message.setText("Dear Mail Crawler,"
                        + "\n Click to link to complete the registered , please!"
                        + "\n http://localhost:8084/JudiBlog/active?id="+idActive+"");
            }else{
                message.setText("OK, thank you!"
                        + "\n You have registed successfully!");
            }
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
