package org.digivalet.Modules.LoginModule;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTPReader {
    public String readOTPFromEmail() throws MessagingException, IOException {


        final String username = "harsh.automation.testing@gmail.com";
        final String password = "jnft lavs byoq tsse";

        // Mail properties
        Properties props = new Properties();
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.ssl.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.imap.ssl.protocols", "TLSv1.2");


        // Establishing the session
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

            // Connecting to the inbox folder
            Store store = session.getStore("imap");
            store.connect();

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Fetching messages
            Message[] messages = inbox.getMessages();
            Message message1 =messages[messages.length - 1];
            MimeMessage mimeMessage = (MimeMessage) message1;
            Object bodyStructure = mimeMessage.getContent();
            System.out.println(bodyStructure instanceof MimeMultipart);
            String otp =readEmailContent(bodyStructure);

            inbox.close(false);
            store.close();
            return otp;
    }


 public String readEmailContent(Object content) throws MessagingException, IOException {
        // Get the content of the message
//        Object content = message.getContent();
     String emailContent = null;
        // Check if the content is a multipart message
        if (content instanceof Multipart) {
            StringBuilder textBuilder = new StringBuilder();
            Multipart multipart = (Multipart) content;

            // Iterate over each part of the multipart message
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart part = multipart.getBodyPart(i);

                // Check if the part is text/plain or text/html
                if (part.isMimeType("text/plain") || part.isMimeType("text/html")) {
                    // Append the content of the part to the StringBuilder
                    textBuilder.append(part.getContent().toString());
                }
            }

            // Convert the StringBuilder to a string
           emailContent = textBuilder.toString();

        } else if (content instanceof String) {
            // If the content is already a string, simply cast it
            emailContent = (String) content;
        }
        return extractOTP(emailContent);
}
    public String extractOTP(String htmlContent ) {
        // Define the regex pattern to match the OTP
        String regex = "Here is your one-time use password: (\\d{6})"; // Matches a 6-digit OTP

        // Compile the regex pattern into a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object using the pattern and HTML content
        Matcher matcher = pattern.matcher(htmlContent);

        // Find the OTP matches
        while (matcher.find()) {
            // Extract and print the matched OTP value
            String otp = matcher.group();
            System.out.println("Found OTP: " + otp);
            return otp.substring(36,42);
        }
        return "";
    }
}