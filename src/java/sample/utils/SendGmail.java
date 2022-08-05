/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Utech
 */
public class SendGmail {

    public static void sendText(String target) throws AddressException, MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;

        // Step1: setup Mail Server
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // Step2: get Mail Session
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(target)); //Thay target bằng địa chỉ người nhận

        // Bạn có thể chọn CC, BCC
//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
        String title = "Ban Da Mua Hang Thanh Cong - (DO_DUY_ANH_K16)";
        String content = "Don hang cua ban da duoc xac nhan";
        mailMessage.setSubject(title);
        mailMessage.setText(content);

        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");

        // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
        String email = "shironekoyuun@gmail.com";
        String password = "shironekoclone";
        transport.connect("smtp.gmail.com", email, password);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

}
