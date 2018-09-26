package ru.javasch.metro.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ComponentScan({"ru.javasch.metro"})
public class MailConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public JavaMailSender getMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.yandex.ru");
        mailSender.setPort(465);

        mailSender.setUsername("dummymailmetro@yandex.ru");
        mailSender.setPassword("Kotelnikov13");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
