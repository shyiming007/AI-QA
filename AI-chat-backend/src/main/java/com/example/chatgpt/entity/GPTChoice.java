package com.example.chatgpt.entity;


import lombok.Data;

/**
 * GPT-3 返回choice对象
 * @author zhengwen
 */
@Data
public class GPTChoice {

    private String text;

    private Integer index;
}
