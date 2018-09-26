package ru.javasch.metro.service.implementations;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ru.javasch.metro.model.Message;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Log4j
@Component
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMimeMessage(Message message) throws MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom("apocalypticmetro@mail.ru");
        mimeMailMessage.setContent(message.getContext(), "text/html");
        mimeMessageHelper.setTo(message.getAddressee());
        mimeMessageHelper.setSubject(message.getSubject());
        if (message.getText() != null)
            mimeMessageHelper.setText(message.getText());

        javaMailSender.send(mimeMailMessage);

    }
}

