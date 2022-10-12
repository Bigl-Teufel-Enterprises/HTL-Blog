package at.biglmatthias.WeLift.mail;

public interface EmailSender {

    void sendEmail(String to,String from, String subject, String body);

}
