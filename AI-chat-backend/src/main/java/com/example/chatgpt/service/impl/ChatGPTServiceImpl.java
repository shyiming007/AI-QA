package com.example.chatgpt.service.impl;


import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.chatgpt.entity.GPTResponse;
import com.example.chatgpt.service.ChatGPTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ChatGPTServiceImpl implements ChatGPTService {

    @Value("${ChatGPT.variables.apiKey}")
    private String apiKey;

    @Value("${ChatGPT.variables.maxTokens}")
    private String maxTokens;

    @Value("${ChatGPT.variables.model}")
    private String model;

    @Value("${ChatGPT.variables.temperature}")
    private String temperature;
    @Override
    public String send(String prompt) {
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("prompt", prompt);
        bodyJson.put("max_tokens", Integer.parseInt(maxTokens));
        bodyJson.put("temperature", Double.parseDouble(temperature));
        Map<String,Object> headMap = new HashMap<>();
        headMap.put("Authorization", "Bearer " + apiKey);

        HttpResponse httpResponse = HttpUtil.createPost("https://api.openai.com/v1/engines/" + model + "/completions")
                .header(Header.AUTHORIZATION, "Bearer " + apiKey)
                .body(JSONUtil.toJsonStr(bodyJson))
                .execute();
        String resStr = httpResponse.body();
        log.info("resStr: {}", resStr);

        GPTResponse gptResponse = JSONUtil.toBean(resStr, GPTResponse.class);

        return gptResponse.getChoices().get(0).getText().replaceAll("\\n","");
    }
}
