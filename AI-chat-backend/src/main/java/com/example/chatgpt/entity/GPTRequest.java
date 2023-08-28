package com.example.chatgpt.entity;


import lombok.Data;

@Data
public class GPTRequest {
    /**
     * 问题
     */
    private String askStr;

    /**
     * 回答
     */
    private String replyStr;
}
