package nl.ecliptic.bep.friendspammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    private EmailSender() {
        throw new IllegalStateException("This is a utility class");
    }

    public static void sendEmail(String subject, String to, String messageBody, boolean asHtml) throws MessagingException{

        Session session = EmailConfig.getSession();

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("spammer@spammer.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);

            if (asHtml) message.setContent(messageBody, "text/html; charset=utf-8");
            else message.setText(messageBody);
            Transport.send(message);

            MongoSaver.saveEmail(to, "spammer@spamer.com", subject, messageBody, asHtml);

        }
        catch (MessagingException e) {
            logger.error("MessagingException", e);
            throw new MessagingException("MessagingException thrown");
        }
    }

    public static void sendEmail(String subject, String[] toList, String messageBody, boolean asHtml) throws MessagingException {

        Session session = EmailConfig.getSession();

        try {
            for (String aToList : toList) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("spammer@spammer.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(aToList));
                message.setSubject(subject);

                if (asHtml) message.setContent(messageBody, "text/html; charset=utf-8");
                else message.setText(messageBody);
                Transport.send(message);

                logger.info("Done");
            }

        }
        catch (MessagingException e) {
            logger.error("MessagingException", e);
            throw new MessagingException("MessagingException thrown");
        }
    }

}
