package com.github.awesomechat.chat;

import com.github.awesomechat.chat.messages.JoinMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatApplicationTests {

	@Test
	public void contextLoads() {

		JoinMessage message = new JoinMessage();
		message.setName("Sergey");

		Jsonizer jsonizer = new Jsonizer();
		String jsonMessage = jsonizer.encode(message);
		System.out.println(jsonMessage);
	}

}
