package in.dhirajrajput.schedulers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import in.dhirajrajput.entity.JournalEntry;
import in.dhirajrajput.entity.User;
import in.dhirajrajput.enums.Sentiment;
import in.dhirajrajput.repository.UserRepoImpl;
import in.dhirajrajput.response_request.Mail;
import in.dhirajrajput.service.MailService;

@Component
public class UserScheduler {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepoImpl userRepoImpl;

    // @Scheduled(cron = "0 0/2 * 1/1 * ? *")
    public void fetchUserAndSendMail() {
        List<User> users = userRepoImpl.getUserForSA();
        for (User usr : users) {
            List<Sentiment> entrylList = usr.getJournalEntries().stream()
                    .filter(entry -> entry.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(JournalEntry::getSentiment).collect(Collectors.toList());

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();

            for (Sentiment sentiment : entrylList) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;

            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {

                Mail mail = new Mail();
                mail.setMailTo(usr.getEmail());
                mail.setMailSubject("Journal Entry of Last 7 Day");

                String htmlContent = "<html>" +
                        "<body>" +
                        "<h1>Welcome to Journal Test Mail</h1>" +
                        "sentiments " + mostFrequentSentiment.toString() +
                        "</body>" +
                        "</html>";

                mail.setMailContent(htmlContent);
                mailService.sendEmail(mail);
            }

        }
    }
}
