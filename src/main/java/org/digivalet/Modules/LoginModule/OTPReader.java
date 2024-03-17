package org.digivalet.Modules.LoginModule;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTPReader {
    public String readOTPFromEmail(String username, String password) throws MessagingException, IOException, GeneralSecurityException {


        Properties props = new Properties();
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.ssl.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.imap.ssl.protocols", "TLSv1.2");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.imap.ssl.trust", "*");
        props.put("mail.imap.ssl.socketFactory", sf);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
            Store store = session.getStore("imap");
            store.connect();

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Fetching messages
            Message[] messages = inbox.getMessages();
            Message lastMessage =messages[messages.length - 1];
            MimeMessage mimeMessage = (MimeMessage) lastMessage;
//        mimeMessage.setFlag(Flags.Flag.SEEN, true);
            Object bodyStructure = mimeMessage.getContent();
            System.out.println(bodyStructure instanceof MimeMultipart);
            String otp =readEmailContent(bodyStructure);

            inbox.close(false);
            store.close();
            return otp;
    }


 public String readEmailContent(Object content) throws MessagingException, IOException {
     String emailContent = null;

        if (content instanceof Multipart) {
            StringBuilder textBuilder = new StringBuilder();
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart part = multipart.getBodyPart(i);
                if (part.isMimeType("text/plain") || part.isMimeType("text/html")) {
                    textBuilder.append(part.getContent().toString());
                }
            }
           emailContent = textBuilder.toString();

        } else if (content instanceof String) {
            emailContent = (String) content;
        }
        return extractOTP(emailContent);
}
    public String extractOTP(String htmlContent ) {

        String regex = "Here is your one-time use password: (\\d{6})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(htmlContent);

        while (matcher.find()) {
            String otp = matcher.group();
            System.out.println("Found OTP: " + otp);
            return otp.substring(36,42);
        }
        return "";
    }
}