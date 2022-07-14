/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SendMail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class MailUtil {

    public static boolean sendMail(String receiver, String code) throws Exception {
        System.out.println("Preparing to send email!");
        Properties pro = new Properties();
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.starttls.enable", "true");
        pro.put("mail.smtp.host", "smtp.gmail.com");
        pro.put("mail.smtp.port", "587");

        String sender = "duyvdhe163866@fpt.edu.vn";
        String pass = "duy30112002";
        Session session = Session.getInstance(pro, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, pass);
            }
        });
        Message message = prepareMessage(session, sender, receiver, code);
        if (message != null) {
            Transport.send(message);
            System.out.println("Send mail successful!");
            return true;
        } else {
            System.out.println("Send mail failed!");
            return false;
        }
    }

    private static Message prepareMessage(Session session, String email, String receiver, String code) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject("Validation code");
            message.setText("Your validation code is " + code + ". Enter code to change password");
            return message;
        } catch (Exception e) {
            System.out.println("Create mail failed! Error: " + e.getMessage());
        }
        return null;
    }

}
