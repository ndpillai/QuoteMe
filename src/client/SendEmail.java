package client;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    static String USER_NAME = "quotemeofficial"; //"*****";  // GMail user name (just the part before "@gmail.com")
    static String PASSWORD = "sourpatchclique";  //********"; // GMail password
    static String RECIPIENT = "gouillon@usc.edu";
    
    public static void SendEmailTest() {
    	System.out.println("Empty send email test constructor");
    }
    
    //public static void sendRecoveryEmail

    public static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        java.util.Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
	public static void sendRecoveryEmail(String emailAddress, String firstName, String userName, String password) {
		System.out.println("Recovery Email to send to: " + emailAddress);
		String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { emailAddress }; // list of recipient email addresses
        String subject = "QuoteMe Password Recovery";
        String body = "\nHi " + firstName + ", \n\n"
        		+ "Oops! It looks like you forgot your password. But don't sweat it, that just means that you were never"
        		+ " meant to be in the QuoteMe Universe to begin with...I'm just kidding. Let's move on.\n\n"
        		+ "Here is some profile information to help you log back into the fun:\n" 
        		+ "\tUsername: " + userName + "\n"
        		+ "\tPassword: " + password + "\n\n"
        		+ "If you are still experiencing trouble logging into your QuoteMe account, feel free to "
        		+ "reach out to us at QuoteMeOfficial@gmail.com, and we will be more than happy to direct you "
        		+ "to the nearest KBBQ restaurant, because when does KBBQ not help a sour mood?\n\n" 
        		+ "Happy Quoting, " + firstName + "! \n\nBest, \nThe QuoteMe team";
	
		sendFromGMail(from, pass, to, subject, body);
	}
	
	public static void sendWelcomeEmail(String emailAddress, String firstName, String userName) {
		System.out.println("Welcome Email to send to: " + emailAddress);
		String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { emailAddress }; 
        String subject = "Welcome to QuoteMe!";
        String body = "\nHi " + firstName + ", \n\n"
        		+ "Welcome to the QuoteMe Universe! Your account has been created successfully. Your username"
        		+ " is: " + userName + "." + "\n" 
        		+ "\nIf you wish to contact the QuoteMe team, or want Physics tutoring, bad jokes, or even just a person to hug, " 
        		+ "feel free to contact us at QuoteMeOfficial@gmail.com, and we will be more than happy to ignore you.\n\n"
        		+ "Happy Quoting, " + firstName + "! \n\nBest, \nThe QuoteMe team";
		sendFromGMail(from, pass, to, subject, body);
	}
}
