package com.thoughtworks.fakemail.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailMessage {

    public String from;

    public String to;

    public String subject;

    public String content;

}
