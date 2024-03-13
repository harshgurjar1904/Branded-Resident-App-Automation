package org.digivalet.Modules.LoginModule;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import javax.mail.search.SubjectTerm;
import java.util.Properties;

public class OTPReader {
    public String readOTPFromEmail() throws MessagingException {
        // Email configuration
        String host = "pop.gmail.com";
        String username = "harsh.automation.testing@gmail.com";
        String password = "Hgurjar731@";
        String fromAddress = "verify@digivaletliving.com";
        String subjectContains = "Login OTP"; // Change this to match your email subject
        Folder emailFolder = null;
        Store store = null;
        try {
            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3s.host", "pop.gmail.com");
            properties.put("mail.pop3s.port", Integer.toString(995));
            properties.put("mail.pop3s.starttls.enable", "true");

            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server

            store = emailSession.getStore("pop3s");

            store.connect("pop.gmail.com", "harsh.automation.testing@gmail.com", "Hgurjar731@");

            //create the folder object and open it

            emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message foundMessage = null;
            Message[] messages = emailFolder.getMessages();
            for (final Message msg : messages) {
                final String subject = msg.getSubject();
            }
        } finally {
            if (emailFolder != null) {
                emailFolder.close(false);
            }
            if (store != null) {
                store.close();
            }
        }

        return "";
    }

//    private static String getTextFromMessage(Message message) throws Exception {
//        if (message.getContent() instanceof Multipart) {
//            StringBuilder result = new StringBuilder();
//            Multipart multipart = (Multipart) message.getContent();
//            for (int i = 0; i < multipart.getCount(); i++) {
//                BodyPart bodyPart = multipart.getBodyPart(i);
//                if (bodyPart.getContentType().startsWith("text/plain")) {
//                    result.append(bodyPart.getContent());
//                }
//            }
//            return result.toString().trim();
//        }
//        return "";
//    }
}