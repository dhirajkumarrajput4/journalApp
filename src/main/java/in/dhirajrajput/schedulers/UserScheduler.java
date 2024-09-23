package in.dhirajrajput.schedulers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import in.dhirajrajput.entity.JournalEntry;
import in.dhirajrajput.entity.User;
import in.dhirajrajput.repository.UserRepoImpl;
import in.dhirajrajput.response_request.Mail;
import in.dhirajrajput.service.MailService;
import in.dhirajrajput.service.SentimentAnalysisService;

@Component
public class UserScheduler {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Scheduled(cron = "0 0/2 * 1/1 * ? *")
    public void fetchUserAndSendMail() {
        List<User> users = userRepoImpl.getUserForSA();
        for (User usr : users) {
            List<String> entrylList = usr.getJournalEntries().stream()
                    .filter(entry -> entry.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(JournalEntry::getTitle).collect(Collectors.toList());

            String entryString = String.join(" ", entrylList);

            String sentiment = sentimentAnalysisService.getSentiment(entryString);

            Mail mail = new Mail();
            mail.setMailTo(usr.getEmail());
            mail.setMailSubject("Journal Entry of Last 7 Day");

            String htmlContent = "<html>" +
                    "<body>" +
                    "<h1>Welcome to Journal Test Mail</h1>" +
                    "sentiments " + sentiment +
                    "Entries " + entryString +
                    "</body>" +
                    "</html>";

            mail.setMailContent(htmlContent);
            mailService.sendEmail(mail);

        }
    }
}
