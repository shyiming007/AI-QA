package com.example.chatgpt.entity;



import lombok.Data;

import java.util.List;

/**
 * GPT-3 返回对象
 * @author zhengwen
 */
@Data
public class GPTResponse {

    private String id;

    private String object;

    private String created;

    private String model;

    private List<GPTChoice> choices;
}
