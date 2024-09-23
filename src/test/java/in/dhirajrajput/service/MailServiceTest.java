package in.dhirajrajput.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.dhirajrajput.response_request.Mail;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;


    @Test
    public void sendEmailTest(){
         Mail mail = new Mail();
        mail.setMailTo("santoshkumawat@eupheus.in");
        mail.setMailSubject("Journal App Test Mail");

        String htmlContent = "<html>" +
                "<body>" +
                "<h1>Welcome to Journal Test Mail</h1>" +
                "</body>" +
                "</html>";

        mail.setMailContent(htmlContent);
        mailService.sendEmail(mail);
    }
    
}
