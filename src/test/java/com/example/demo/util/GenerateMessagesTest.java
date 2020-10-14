package com.example.demo.util;

import com.example.demo.model.MessageModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class GenerateMessagesTest {

    @Test
    public void testGenerateMessages() {
        GenerateMessages generateMessages = new GenerateMessages();
        MessageModel message = generateMessages.getMessages("1").get(0);

        assertEquals(message.getAdId(), "Lk0lYfJU");
        assertEquals(message.getMessage(), "Help Takondwa Thurstan to clean their turnips");
        assertEquals(message.getReward(), 10L);
        assertEquals(message.getExpiresIn(), 6L);
        assertNull(message.getEncrypted());
        assertEquals(message.getProbability(), "Piece of cake");
    }

}
