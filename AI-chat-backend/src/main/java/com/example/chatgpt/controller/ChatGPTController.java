package com.example.chatgpt.controller;


import cn.hutool.json.JSONUtil;
import com.example.chatgpt.entity.GPTRequest;
import com.example.chatgpt.service.ChatGPTService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * GPT-3接口
 *
 * @author zhengwen
 */

@Slf4j
@RestController
@RequestMapping("/chatGpt")
@CrossOrigin(origins = "*")
public class ChatGPTController {

    @Resource
    private ChatGPTService chatGPTService;

    /**
     * openAI GPT-3
     *
     * @param gptRequest 条件对象
     * @return 出参对象
     */
    @PostMapping("/askAi")
    public String askAi(@RequestBody GPTRequest gptRequest) {

        String replyStr = chatGPTService.send(gptRequest.getAskStr());

        gptRequest.setReplyStr(replyStr);

        return JSONUtil.toJsonStr(gptRequest);
    }
}
