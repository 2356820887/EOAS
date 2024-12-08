package com.guat.eoas.test;

import com.guat.eoas.pojo.Email;
import com.guat.eoas.service.EmailService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmailTest {

    private final EmailService service = new EmailService();

    @Test
    void emailTest() {
        List<Email> list = service.getEmails(null, null, null, null);
        for (Email email : list) {
            System.out.println(email);
        }
    }
}
