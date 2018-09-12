package ru.javasch.metro.model;

import ru.javasch.metro.utils.Utils;

import java.io.IOException;


public class Message {

    private String addressee;
    private String text;
    private String subject;
    private String context;

    public String getAddressee() { return addressee; }
    public void setAddressee(String addressee) { this.addressee = addressee; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getContext() { return context; }
    public void setContext(String context) { this.context = context; }

    public static Message createWelcomeMessage(String addressee) throws IOException {
        Message message = new Message();
        message.setContext(Utils.getHelloContext());
        message.setSubject("IT'S TIME TO GO DOWN");
        message.setAddressee(addressee);
        return message;
    }

    public static Message createInvalidateMessage(String addressee) throws IOException {
        Message message = new Message();
        message.setContext(Utils.getInvalidContext());
        message.setSubject("YOUR TICKET WAS INVALIDATE");
        message.setAddressee(addressee);
        return message;
    }


}
