fake-rest-smtp-mail-server
==========================

A stub SMTP mail server for tests purposes based on subethamail. Exposes an SMTP endpoint and allows inspection through a REST interface.

Build:
```
> ./gradlew build                                                                                                                                         {22:49}[1.9.3]~ ➭ telnet localhost 2525                       
```

Run executable:
```
> ./gradlew build                                                                                                                                         {22:49}[1.9.3]~ ➭ telnet localhost 2525                       
```

Example
-------

Querying the HTTP interface, mail queue is empty on startup
```
> curl http://127.0.0.1:2526/fakemail/all
[]%                                                                                                                                         {22:49}[1.9.3]~ ➭ telnet localhost 2525                       
```

Sending emails via SMTP
```
> telnet localhost 2525             
Trying 127.0.0.1...
Connected to tw-ilias.
Escape character is '^]'.
220 tw-ilias ESMTP SubEthaSMTP 3.1.7
EHLO example.com
250-tw-ilias
250-8BITMIME
250 Ok
MAIL FROM:ilias.bartolini@example.com
250 Ok
RCPT TO:mahatma.gandhi@example.com
250 Ok
DATA
354 End data with <CR><LF>.<CR><LF>
Subject:An important advice

Whatever you do will be insignificant, but it is very important that you do it
.
250 Ok
QUIT
221 Bye
Connection closed by foreign host.```


Now the mail queues shows the message received
```
>curl http://127.0.0.1:2526/fakemail/all
[
  {
    "from":"ilias.bartolini@example.com",
    "to":"mahatma.gandhi@example.com",
    "subject":"An important advice",
    "content":"Whatever you do will be insignificant, but it is very important that you do it\r\n"}]%
  }
]
```

The mail queue can be cleared via HTTP POST
```
>curl -d "" http://127.0.0.1:2526/fakemail/clear
```
