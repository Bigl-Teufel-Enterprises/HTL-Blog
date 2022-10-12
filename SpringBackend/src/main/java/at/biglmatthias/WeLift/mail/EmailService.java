package at.biglmatthias.WeLift.mail;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER= LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;


    @Override
    @Async
    public void sendEmail(String to,String from, String subject, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setText(email,true);
            helper.setSubject(to);
            helper.setSubject(subject);
            helper.setFrom("Bigls-Blog@no-reply."+from+".app");
            helper.setTo(to);
            mimeMessage.setSubject(subject);
            mailSender.send(mimeMessage);

        }catch (MessagingException e){
            LOGGER.error("Error sending email",e);
            throw new IllegalStateException("Error sending email");
        }

    }

}
