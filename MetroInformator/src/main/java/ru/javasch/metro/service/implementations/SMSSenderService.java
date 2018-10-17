package ru.javasch.metro.service.implementations;

import lombok.extern.log4j.Log4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import ru.javasch.metro.configuration.SmsSender;

@Service
@Log4j
public class SMSSenderService {
    public void sendSMS (String message, String phone) {
        try {
            SmsSender sms = new SmsSender("MetroInformatorSMS", "fc1572df7c303", false);
            //Отправка смс сообщения
            JSONObject resultJson = sms.MessageSend(message, phone, "sendertest");
            log.info(resultJson.get("status"));
            if (resultJson.get("status").equals("success"))
            {
                log.info("SMS send. Price - "+resultJson.get("price")+" rubles");
            }
            else
            {
                log.info("Error "+resultJson.get("message"));
            }
        } catch (Exception ex) {log.error("SMSSender Error", ex);}
    }
}
