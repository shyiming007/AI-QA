package com.example;

import io.github.asleepyfish.util.OpenAiUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@SpringBootTest
class AiChatBackendApplicationTests {

	@PostMapping("/chatTest")
	public List<String> chatTest(String content) {
		return OpenAiUtils.createChatCompletion("Java序列化的方式", "testUser");
	}

}
