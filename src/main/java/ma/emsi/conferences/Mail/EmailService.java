package ma.emsi.conferences.Mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import ma.emsi.conferences.EnvLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender,SpringTemplateEngine template) {
        this.mailSender = mailSender;
        this.templateEngine = template;
    }

    public String SendMail(String code, String mail) throws MessagingException {

        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());
        Map<String,Object> properties=new HashMap<>();
        properties.put("username","username");
        properties.put("confirmationUrl", EnvLinks.CLIENT.URL()+"/api");
        properties.put("activation_code",code);
        Context context =new Context();
        context.setVariables(properties);
        mimeMessageHelper.setFrom("Conference@emsi-edu.ma");
        mimeMessageHelper.setTo(mail);
        mimeMessageHelper.setSubject("Email Verification");
        String template=templateEngine.process("email_check",context);
        mimeMessageHelper.setText(template,true);
        mailSender.send(mimeMessage);
        return "true";
    }
}
