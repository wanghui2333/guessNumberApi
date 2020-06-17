package com.twschool.practice.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class GuessNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String basePath = "/guess-number";


    public GuessNumberControllerTest() {
    }

    @Test
    public void should_return_guess_result() throws Exception {
        JSONObject dataJson = new JSONObject();
        dataJson.put("output","4A0B");
        dataJson.put("instruction","success");

        mockMvc.perform(MockMvcRequestBuilders.get(basePath + "/check")
        .contentType(MediaType.APPLICATION_JSON)
        .param("guess", "1 2 3 4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(dataJson));
    }

}