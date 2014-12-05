package com.thoughtworks.fakemail.controller;

import com.thoughtworks.fakemail.Application;
import com.thoughtworks.fakemail.domain.MailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.subethamail.wiser.WiserMessage;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fakemail")
public class FakeMailController {

    @RequestMapping(value = "/all", method = {RequestMethod.GET})
    public List<MailMessage> all(@RequestParam(required = false) String from,
                                 @RequestParam(required = false) String to) throws Exception {
        List<WiserMessage> messages = Application.wiser.getMessages();
        ArrayList<MailMessage> returnMailMessages = new ArrayList<>();
        for (WiserMessage message : messages) {
            returnMailMessages.add(
                    new MailMessage(
                            message.getEnvelopeSender(),
                            message.getEnvelopeReceiver(),
                            message.getMimeMessage().getSubject(),
                            message.getMimeMessage().getContent().toString())
            );
        }
        if(from != null) returnMailMessages.removeIf(m -> !m.from.matches(from));
        if(to != null) returnMailMessages.removeIf(m -> !m.to.matches(to));
        return returnMailMessages;
    }

    @RequestMapping(value = "/clear", method = {RequestMethod.POST, RequestMethod.GET})
    public void clear() throws Exception {
        Application.wiser.getMessages().clear();
    }

}
