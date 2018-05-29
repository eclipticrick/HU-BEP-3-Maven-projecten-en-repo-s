package nl.ecliptic.bep.friendspammer;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

class EmailConfig {
    static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");
        props.put("mail.smtp.auth", "true");

        String username = "95f3553a6be4f6";
        String password = "98af038075b6b1";

        return Session.getInstance(props, new javax.mail.Authenticator() {
            @Override protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}
