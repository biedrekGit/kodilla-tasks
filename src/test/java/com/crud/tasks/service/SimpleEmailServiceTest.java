package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mailMessage = Mail.builder()
                .mailTo("test@mail.com")
                .subject("Test Subject")
                .message("Test Message")
                .toCc("testCc@mail.com")
                .build();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailMessage.getMailTo());
        simpleMailMessage.setSubject(mailMessage.getSubject());
        simpleMailMessage.setText(mailMessage.getMessage());
        simpleMailMessage.setCc(mailMessage.getToCc());

        //When
        simpleEmailService.send(mailMessage);

        //Then
        verify(javaMailSender, times(1)).send(simpleMailMessage);

    }

}